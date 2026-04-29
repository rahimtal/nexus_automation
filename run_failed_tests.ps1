#!/usr/bin/env pwsh
<#
.SYNOPSIS
Extract failed tests from TestSuite.txt and re-run them

.DESCRIPTION
This script reads the TestSuite.txt output, extracts all failed tests,
generates a testng-failed.xml file with only those tests, and runs them.

.EXAMPLE
.\run_failed_tests.ps1
.\run_failed_tests.ps1 -TestOutputFile "target/test-output/TestSuite.txt" -OutputXml "testng-failed.xml"

.NOTES
Requires: Maven, Java, TestNG
#>

param(
    [string]$TestOutputFile = "target/test-output/TestSuite.txt",
    [string]$OutputXml = "testng-failed.xml"
)

function Extract-FailedTests {
    param(
        [string]$FilePath,
        [string]$OutputFile
    )

    Write-Host "`nExtracting failed tests from: $FilePath" -ForegroundColor Cyan
    
    if (-not (Test-Path $FilePath)) {
        Write-Host "✗ File not found: $FilePath" -ForegroundColor Red
        return $false
    }

    # Extract failed test names
    $content = Get-Content $FilePath -Raw
    $failedTests = @()
    $lines = $content -split "`n"
    
    foreach ($line in $lines) {
        if ($line -match "^(com\.NexusAPI\.Tests\.[^\s]+)\s+Time elapsed:.*<<< FAILURE!") {
            $testName = $matches[1]
            if ($testName -notin $failedTests) {
                $failedTests += $testName
            }
        }
    }

    if ($failedTests.Count -eq 0) {
        Write-Host "✓ No failed tests found! All tests passed." -ForegroundColor Green
        return $false
    }

    # Extract unique test classes
    $failedClasses = @()
    foreach ($test in $failedTests) {
        $className = $test.Substring(0, $test.LastIndexOf('.'))
        if ($className -notin $failedClasses) {
            $failedClasses += $className
        }
    }

    # Generate XML
    $xml = @"
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="Failed Tests Suite" verbose="2" preserve-order="true" parallel="false" thread-count="1">
    <test name="Re-run Failed Tests">
        <classes>
"@

    foreach ($class in $failedClasses) {
        $xml += "            <class name=`"$class`"/>`n"
    }

    $xml += @"
        </classes>
    </test>
</suite>
"@

    $xml | Out-File -FilePath $OutputFile -Encoding UTF8
    
    Write-Host "✓ Generated $OutputFile" -ForegroundColor Green
    Write-Host "✓ Failed test classes: $($failedClasses.Count)" -ForegroundColor Green
    Write-Host "✓ Failed tests: $($failedTests.Count)" -ForegroundColor Green
    
    Write-Host "`n========== FAILED TESTS ==========" -ForegroundColor Yellow
    $failedTests | Sort-Object | ForEach-Object { Write-Host "  ✗ $_" }
    Write-Host "=================================`n" -ForegroundColor Yellow
    
    return $true
}

# Main execution
Write-Host "`n========================================" -ForegroundColor Cyan
Write-Host "Failed Test Runner" -ForegroundColor Cyan
Write-Host "========================================`n" -ForegroundColor Cyan

# Compile project
Write-Host "Compiling project..." -ForegroundColor Cyan
$compileResult = & mvn clean compile -q
if ($LASTEXITCODE -ne 0) {
    Write-Host "✗ Compilation failed" -ForegroundColor Red
    exit 1
}

# Extract failed tests
$extracted = Extract-FailedTests -FilePath $TestOutputFile -OutputFile $OutputXml

if (-not $extracted) {
    exit 0
}

# Run failed tests
Write-Host "`n========================================" -ForegroundColor Cyan
Write-Host "Running Failed Tests" -ForegroundColor Cyan
Write-Host "========================================`n" -ForegroundColor Cyan

& mvn clean test -Dsuite=$OutputXml

Write-Host "`n========================================" -ForegroundColor Cyan
Write-Host "Failed Test Run Complete" -ForegroundColor Cyan
Write-Host "========================================`n" -ForegroundColor Cyan

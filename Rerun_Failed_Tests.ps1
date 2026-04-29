#!/usr/bin/env pwsh
<#
.SYNOPSIS
Re-run Failed Tests - Extract failed tests from TestSuite.txt and re-run them

.DESCRIPTION
This PowerShell script:
1. Runs the full test suite (if TestSuite.txt doesn't exist)
2. Extracts all failed tests from TestSuite.txt
3. Generates a testng-failed.xml file with only those tests
4. Re-runs the failed tests

.EXAMPLE
.\Rerun_Failed_Tests.ps1

.NOTES
Requires: Maven, Java, TestNG 7.x+
#>

$ErrorActionPreference = "Stop"

function Show-Header {
    param([string]$Title)
    Write-Host "`n=======================================" -ForegroundColor Cyan
    Write-Host $Title -ForegroundColor Cyan
    Write-Host "=======================================" -ForegroundColor Cyan
}

function Show-Success {
    param([string]$Message)
    Write-Host "✓ $Message" -ForegroundColor Green
}

function Show-Error {
    param([string]$Message)
    Write-Host "✗ $Message" -ForegroundColor Red
}

function Show-Info {
    param([string]$Message)
    Write-Host "→ $Message" -ForegroundColor Yellow
}

# Step 1: Check if TestSuite.txt exists
Show-Header "Re-run Failed Tests"

$testOutputFile = "target/test-output/TestSuite.txt"
if (-not (Test-Path $testOutputFile)) {
    Show-Info "TestSuite.txt not found. Running full test suite first..."
    Write-Host ""
    
    # Run tests
    & mvn clean test -Dgroups=null
    
    if ($LASTEXITCODE -ne 0) {
        Show-Error "Test run failed"
        exit 1
    }
}

# Step 2: Extract failed tests
Show-Header "Extracting Failed Tests"

if (-not (Test-Path $testOutputFile)) {
    Show-Error "TestSuite.txt still not found"
    exit 1
}

Show-Info "Reading $testOutputFile"
$content = Get-Content $testOutputFile -Raw
$lines = $content -split "`n"

$failedTests = @()
$failedClasses = @()

foreach ($line in $lines) {
    # Pattern: com.NexusAPI.Tests.PackageName.ClassName.methodName  Time elapsed: X.XXX s  <<< FAILURE!
    if ($line -match "^(com\.NexusAPI\.Tests\.[^\s]+)\s+Time elapsed:.*<<< FAILURE!") {
        $testName = $matches[1]
        if ($testName -notin $failedTests) {
            $failedTests += $testName
            
            # Extract class name
            $className = $testName.Substring(0, $testName.LastIndexOf('.'))
            if ($className -notin $failedClasses) {
                $failedClasses += $className
            }
        }
    }
}

if ($failedTests.Count -eq 0) {
    Show-Success "No failed tests found! All tests passed."
    exit 0
}

Show-Success "Found $($failedTests.Count) failed tests in $($failedClasses.Count) classes"

# Step 3: Generate testng-failed.xml
Show-Header "Generating testng-failed.xml"

$xml = @"
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="Failed Tests Suite" verbose="2" preserve-order="true" parallel="false" thread-count="1">
    <test name="Re-run Failed Tests">
        <classes>
"@

foreach ($class in ($failedClasses | Sort-Object)) {
    $xml += "            <class name=`"$class`"/>`n"
}

$xml += @"
        </classes>
    </test>
</suite>
"@

$xml | Out-File -FilePath "testng-failed.xml" -Encoding UTF8 -Force
Show-Success "Created testng-failed.xml"

# Step 4: Display failed tests
Write-Host "`n========== FAILED TESTS ==========" -ForegroundColor Yellow
$failedTests | Sort-Object | ForEach-Object { 
    Write-Host "  ✗ $_" -ForegroundColor Red 
}
Write-Host "=================================`n" -ForegroundColor Yellow

# Step 5: Run failed tests
Show-Header "Running Failed Tests"

& mvn clean test -Dsuites="testng-failed.xml" -f testng-failed.xml

if ($LASTEXITCODE -eq 0) {
    Show-Success "All previously failed tests now pass!"
} else {
    Show-Info "Some tests still failing. Review output above."
}

Show-Header "Re-run Complete"

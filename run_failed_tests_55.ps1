# PowerShell script to run 55 failed tests
# Usage: .\run_failed_tests_55.ps1

param(
    [switch]$ShowReports = $false
)

# Change to script directory
Set-Location $PSScriptRoot

Write-Host "==========================================================" -ForegroundColor Cyan
Write-Host "Running 55 Failed Tests - testng-failed-55.xml" -ForegroundColor Cyan
Write-Host "==========================================================" -ForegroundColor Cyan
Write-Host ""

# Restore database first
Write-Host "Database Restore (optional - comment out if not needed)" -ForegroundColor Yellow
# .\Configuration\DBOnlyrestore.ps1

# Run tests
Write-Host "Executing Maven tests..." -ForegroundColor Green
$startTime = Get-Date

mvn clean test -Dsurefire.suiteXmlFiles=testng-failed-55.xml

$endTime = Get-Date
$duration = $endTime - $startTime

Write-Host ""
Write-Host "==========================================================" -ForegroundColor Cyan
Write-Host "Test Execution Completed" -ForegroundColor Cyan
Write-Host "Duration: $($duration.Minutes) min $($duration.Seconds) sec" -ForegroundColor Cyan
Write-Host "Reports available in: target\test-output\" -ForegroundColor Cyan
Write-Host "==========================================================" -ForegroundColor Cyan

if ($ShowReports) {
    Write-Host ""
    Write-Host "Opening test results..." -ForegroundColor Green
    $htmlReport = Join-Path $PSScriptRoot "target\test-output\emailable-report.html"
    if (Test-Path $htmlReport) {
        & $htmlReport
    } else {
        Write-Host "Report file not found: $htmlReport" -ForegroundColor Yellow
    }
}

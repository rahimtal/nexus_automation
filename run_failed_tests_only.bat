@echo off
REM Run Failed Tests Only (Updated - Narrowed Down)
REM This script executes only the actually failing tests from the full test suite

cd /d "d:\Nexus Automation\nexus_automation"
set PATH=%PATH%;C:\maven\bin

echo ======================================================
echo   Step 1: Restoring Database...
echo ======================================================
REM Restore database using PowerShell script
powershell -ExecutionPolicy Bypass -File "%cd%\Configuration\DBOnlyrestore.ps1"
if %ERRORLEVEL% NEQ 0 (
    echo.
    echo *** Database restore failed! Aborting test execution. ***
    pause
    exit /b 1
)
echo Database restore completed successfully.

echo.
echo ======================================================
echo   Step 2: Running Updated Failed Tests Only
echo ======================================================
echo.

REM Clean old reports
if exist "test-output-failed" rmdir /s /q "test-output-failed"
if exist "target\surefire-reports-failed" rmdir /s /q "target\surefire-reports-failed"

REM Run the updated failed tests only
mvn test -Dsurefire.suiteXmlFiles=testng-failed-updated.xml

echo.
echo ======================================================
echo   Test Execution Complete
echo ======================================================
echo.
pause

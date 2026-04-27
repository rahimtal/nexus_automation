REM filepath: c:\Users\Admin\Documents\GitHub\nexus_automation\run_All_tests.bat
@echo off
cd /d "d:\Nexus Automation\nexus_automation"
set PATH=%PATH%;C:\maven\bin

REM Step 1: Create reports folder if it doesn't exist
if not exist "reports" mkdir "reports"

REM Step 2: Restore database
echo ======================================================
echo   Step 1: Restoring Database...
echo ======================================================
REM mvn exec:java -Dexec.mainClass=com.NexustAPIAutomation.java.QuickDBRestore -DskipTests
REM if %ERRORLEVEL% NEQ 0 (
REM     echo.
REM     echo *** Database restore failed! Aborting test execution. ***
REM     pause
REM     exit /b 1
REM )
echo Database restore skipped.

REM Step 3: Clean old TestNG reports
echo.
echo ======================================================
echo   Step 2: Cleaning old reports...
echo ======================================================
if exist "test-output" rmdir /s /q "test-output"
if exist "target\surefire-reports" rmdir /s /q "target\surefire-reports"
echo Old reports cleaned.

REM Step 4: Run functional tests and email results
echo.
echo ======================================================
echo   Step 3: Running Functional Tests...
echo ======================================================
mvn exec:java -Dexec.mainClass=com.NexustAPIAutomation.java.TestAndEmailExecutor -DskipTests

REM Step 5: Run performance tests
echo.
echo ======================================================
echo   Step 4: Running Performance Tests...
echo ======================================================
mvn test -Dtest=com.NexusAPI.Tests.PerformanceTests -Dgroups=performance
if %ERRORLEVEL% NEQ 0 (
    echo.
    echo *** Some performance tests failed. Check the report for details. ***
)

REM Step 6: Open latest ExtentReport in browser
echo.
echo ======================================================
echo   Step 5: Opening Report...
echo ======================================================
set LATEST_REPORT=
for /f "delims=" %%i in ('dir /b /od "reports\NexusAPI_Report_*.html" 2^>nul') do set LATEST_REPORT=%%i
if defined LATEST_REPORT (
    echo Opening report: reports\%LATEST_REPORT%
    start "" "reports\%LATEST_REPORT%"
) else (
    echo No ExtentReport found in reports folder.
)

echo.
echo ======================================================
echo   All Steps Completed!
echo ======================================================
pause
@echo off
REM Run only the 55 failed tests for investigation
REM Usage: run_failed_tests_55.bat

cd /d "%~dp0"

echo ============================================================
echo Running 55 Failed Tests - testng-failed-55.xml
echo ============================================================
echo.

mvn clean test -Dsurefire.suiteXmlFiles=testng-failed-55.xml

echo.
echo ============================================================
echo Test Execution Completed
echo Reports available in: target\test-output\
echo ============================================================

pause

@echo off
REM Script to extract failed tests and re-run them

echo.
echo ========================================
echo Running Failed Test Extractor
echo ========================================
echo.

REM Compile the extractor class if needed
echo Compiling project...
call mvn clean compile -q

if errorlevel 1 (
    echo ✗ Compilation failed
    exit /b 1
)

REM Run the extractor
echo Extracting failed tests from TestSuite.txt...
call mvn exec:java -Dexec.mainClass="com.NexustAPIAutomation.java.FailedTestExtractor" -Dexec.args="target/test-output/TestSuite.txt testng-failed.xml" -q

if errorlevel 1 (
    echo ✗ Failed to extract tests
    exit /b 1
)

REM Check if failed tests were found
if not exist testng-failed.xml (
    echo ✓ No failed tests found!
    exit /b 0
)

echo.
echo ========================================
echo Running Failed Tests
echo ========================================
echo.

REM Run the failed tests
call mvn clean test -Dsuite=testng-failed.xml

echo.
echo ========================================
echo Failed Test Run Complete
echo ========================================
echo.

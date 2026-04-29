@echo off
REM Database Restore Only Script
REM Compiles and runs QuickDBRestore without executing tests

cd /d "%~dp0"

echo Compiling project...
call mvn clean compile dependency:build-classpath -Dmdep.outputFile=target\classpath.txt

if %ERRORLEVEL% NEQ 0 (
    echo Compilation failed!
    pause
    exit /b 1
)

echo.
echo Restoring Database...

REM Read classpath from file
for /f "delims=" %%i in (target\classpath.txt) do set CLASSPATH=%%i

REM Add compiled classes to classpath
set CLASSPATH=%CLASSPATH%;target\classes;Configuration

REM Run QuickDBRestore with full classpath
java -cp "%CLASSPATH%" com.NexustAPIAutomation.java.QuickDBRestore
set RESULT=%ERRORLEVEL%

if %RESULT% EQU 0 (
    echo.
    echo Database restored successfully!
) else (
    echo.
    echo Database restore failed!
)

pause
exit /b %RESULT%

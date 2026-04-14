@echo off
cd /d "c:\Users\Admin\Documents\GitHub\nexus_automation"
mvn exec:java -Dexec.mainClass=com.NexustAPIAutomation.java.QuickDBRestore -DskipTests
if %ERRORLEVEL% NEQ 0 (
    echo Database restore failed! Aborting test execution.
    pause
    exit /b 1
)
mvn exec:java -Dexec.mainClass=com.NexustAPIAutomation.java.TestAndEmailExecutor -DskipTests
pause
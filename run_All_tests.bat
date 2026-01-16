@echo off
cd /d "c:\Users\Admin\Documents\GitHub\nexus_automation"
mvn exec:java -Dexec.mainClass=com.NexustAPIAutomation.java.TestAndEmailExecutor -DskipTests
pause
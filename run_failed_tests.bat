@echo off
cd /d "c:\Users\Admin\Documents\GitHub\nexus_automation"
mvn test -Dsurefire.suiteXmlFiles=target/test-output/testng-failed.xml
pause
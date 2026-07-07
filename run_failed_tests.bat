@echo off
cd /d "%~dp0"
mvn test -Dsurefire.suiteXmlFiles=target/surefire-reports/testng-failed.xml
pause
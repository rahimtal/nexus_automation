@echo off
REM Batch file to create 'sa' user in PostgreSQL
setlocal enabledelayedexpansion

REM Call PowerShell to execute the database script
powershell -ExecutionPolicy Bypass -NoProfile -Command ^
    "$pgHost = 'localhost'; " ^
    "$pgPort = '5432'; " ^
    "$pgUser = 'keycloak'; " ^
    "$pgPassword = 'keycloak'; " ^
    "$pgDatabase = 'keycloak'; " ^
    "$env:PGPASSWORD = $pgPassword; " ^
    "Write-Host 'Creating sa user in PostgreSQL...'; " ^
    "$realmQuery = \"SELECT id FROM realm WHERE name = 'nexus' LIMIT 1;\"; " ^
    "$realmIdOutput = @(psql -h localhost -p 5432 -U keycloak -d keycloak -t -c $realmQuery); " ^
    "$realmId = $realmIdOutput[0].Trim(); " ^
    "Write-Host \"Realm ID: $realmId\"; " ^
    "if ([string]::IsNullOrEmpty($realmId)) { Write-Host 'ERROR: Could not find realm'; exit 1; }; " ^
    "$userId = [guid]::NewGuid().ToString(); " ^
    "$createdTimestamp = [int64]([datetime]::UtcNow - [datetime]'1970-01-01').TotalMilliseconds; " ^
    "Write-Host \"Creating user with ID: $userId\"; " ^
    "$insertUserQuery = 'INSERT INTO user_entity (id, username, created_timestamp, email_verified, enabled, federation_link, first_name, last_name, realm_id, email, service_account_client_link) VALUES (' + ''('^!''''$userId'^^^^^^^^''^!' + ', ''sa'', ' + $createdTimestamp + ', false, true, NULL, NULL, NULL, ''$realmId'', NULL, NULL);'; " ^
    "psql -h localhost -p 5432 -U keycloak -d keycloak -c $insertUserQuery; " ^
    "Write-Host 'SUCCESS: User sa created!'"

endlocal

@echo off
REM Batch file to create 'sa' user in PostgreSQL

powershell -ExecutionPolicy Bypass -Command {
    $pgHost = "localhost"
    $pgPort = "5432"
    $pgUser = "keycloak"
    $pgPassword = "keycloak"
    $pgDatabase = "keycloak"
    $realmName = "nexus"
    
    Write-Host "Creating 'sa' user in PostgreSQL directly..."
    
    # Set environment variable for psql password
    $env:PGPASSWORD = $pgPassword
    
    # Get realm ID first
    $realmQuery = "SELECT id FROM realm WHERE name = '$realmName' LIMIT 1;"
    $realmIdOutput = & psql -h $pgHost -p $pgPort -U $pgUser -d $pgDatabase -t -c $realmQuery
    $realmId = $realmIdOutput.Trim()
    
    Write-Host "Realm ID: $realmId"
    
    if ([string]::IsNullOrEmpty($realmId)) {
        Write-Host "ERROR: Could not find realm '$realmName'"
        exit 1
    }
    
    # Create the user with proper UUID and timestamp
    $userId = [guid]::NewGuid().ToString()
    $createdTimestamp = [int64]([datetime]::UtcNow - [datetime]'1970-01-01').TotalMilliseconds
    
    Write-Host "Creating user with ID: $userId"
    Write-Host "Timestamp: $createdTimestamp"
    
    # Insert user
    $createUserQuery = @"
INSERT INTO user_entity (
    id,
    username,
    created_timestamp,
    email_verified,
    enabled,
    federation_link,
    first_name,
    last_name,
    realm_id,
    email,
    service_account_client_link
) VALUES (
    '$userId',
    'sa',
    $createdTimestamp,
    false,
    true,
    NULL,
    NULL,
    NULL,
    '$realmId',
    NULL,
    NULL
);
"@
    
    Write-Host "Inserting user..."
    & psql -h $pgHost -p $pgPort -U $pgUser -d $pgDatabase -c $createUserQuery
    
    # Add password credential - using pbkdf2-sha256 format
    # For 'password' hashed with PBKDF2-SHA256 (27500 iterations)
    $credentialId = [guid]::NewGuid().ToString()
    
    $createCredentialQuery = @"
INSERT INTO credential (
    id,
    user_id,
    type,
    created_date,
    user_label,
    secret_data,
    credential_data,
    priority
) VALUES (
    '$credentialId',
    '$userId',
    'password',
    $createdTimestamp,
    NULL,
    '{"value":"password"}',
    '{"hashIterations":27500,"algorithm":"pbkdf2-sha256"}',
    10
);
"@
    
    Write-Host "Adding password credential..."
    & psql -h $pgHost -p $pgPort -U $pgUser -d $pgDatabase -c $createCredentialQuery
    
    # Add attributes
    $userNameAttrId = [guid]::NewGuid().ToString()
    $userIdAttrId = [guid]::NewGuid().ToString()
    
    $createAttributesQuery = @"
INSERT INTO user_attribute (id, user_id, name, value) VALUES 
    ('$userNameAttrId', '$userId', 'UserName', 'sa'),
    ('$userIdAttrId', '$userId', 'UserId', 'sa');
"@
    
    Write-Host "Adding attributes..."
    & psql -h $pgHost -p $pgPort -U $pgUser -d $pgDatabase -c $createAttributesQuery
    
    Write-Host "SUCCESS: User 'sa' created in PostgreSQL!"
}

# PowerShell script to create 'sa' user directly in PostgreSQL

$pgHost = "localhost"
$pgPort = "5432"
$pgUser = "keycloak"
$pgPassword = "keycloak"
$pgDatabase = "keycloak"
$realmName = "nexus"

Write-Host "Creating 'sa' user in PostgreSQL..."

# Set environment variable for password
$env:PGPASSWORD = $pgPassword

# Get realm ID
Write-Host "Getting realm ID..."
$realmQuery = "SELECT id FROM realm WHERE name = '$realmName' LIMIT 1;"
$realmIdOutput = psql -h $pgHost -p $pgPort -U $pgUser -d $pgDatabase -t -c $realmQuery 2>&1
$realmId = $realmIdOutput.Trim()

Write-Host "Realm ID: $realmId"

if ([string]::IsNullOrEmpty($realmId)) {
    Write-Host "ERROR: Could not find realm '$realmName'"
    exit 1
}

# Generate new UUID and timestamp
$userId = [guid]::NewGuid().ToString()
$createdTimestamp = [int64]([datetime]::UtcNow - [datetime]'1970-01-01').TotalMilliseconds

Write-Host "User ID: $userId"
Write-Host "Timestamp: $createdTimestamp"

# Create user
Write-Host "Creating user in database..."
$insertUserQuery = @"
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

$output = psql -h $pgHost -p $pgPort -U $pgUser -d $pgDatabase -c $insertUserQuery 2>&1
Write-Host $output

# Add password credential
Write-Host "Adding password credential..."
$credentialId = [guid]::NewGuid().ToString()
$insertCredentialQuery = @"
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

$output = psql -h $pgHost -p $pgPort -U $pgUser -d $pgDatabase -c $insertCredentialQuery 2>&1
Write-Host $output

# Add attributes
Write-Host "Adding attributes..."
$userNameAttrId = [guid]::NewGuid().ToString()
$userIdAttrId = [guid]::NewGuid().ToString()

$insertAttributesQuery = @"
INSERT INTO user_attribute (id, user_id, name, value) VALUES 
    ('$userNameAttrId', '$userId', 'UserName', 'sa'),
    ('$userIdAttrId', '$userId', 'UserId', 'sa');
"@

$output = psql -h $pgHost -p $pgPort -U $pgUser -d $pgDatabase -c $insertAttributesQuery 2>&1
Write-Host $output

Write-Host "SUCCESS: User 'sa' created in PostgreSQL!"

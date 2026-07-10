$keycloakUrl = "http://localhost:8080"
$adminUser = "admin"
$adminPass = "admin"
$realm = "nexus"
$saPassword = "password"

# Get admin token
Write-Host "Getting admin token..."
$tokenResponse = Invoke-WebRequest -Uri "$keycloakUrl/realms/master/protocol/openid-connect/token" `
  -Method Post `
  -Headers @{"Content-Type"="application/x-www-form-urlencoded"} `
  -Body "client_id=admin-cli&username=$adminUser&password=$adminPass&grant_type=password" `
  -UseBasicParsing

$token = ($tokenResponse.Content | ConvertFrom-Json).access_token
Write-Host "Token obtained successfully"

# The 'nexus' realm user profile enforces a minimum username length of 3, which
# rejects the 2-character 'sa' username with error-invalid-length. Lower the
# minimum to 2 so short service accounts like 'sa' can be created.
Write-Host "Ensuring username min length allows 'sa'..."
$profile = (Invoke-WebRequest -Uri "$keycloakUrl/admin/realms/$realm/users/profile" `
  -Headers @{"Authorization"="Bearer $token"} -UseBasicParsing).Content | ConvertFrom-Json
$usernameAttr = $profile.attributes | Where-Object { $_.name -eq "username" }
if ($usernameAttr.validations.length.min -gt 2) {
    $usernameAttr.validations.length.min = 2
    $profileBody = $profile | ConvertTo-Json -Depth 20
    Invoke-WebRequest -Uri "$keycloakUrl/admin/realms/$realm/users/profile" `
      -Method Put `
      -Headers @{"Authorization"="Bearer $token"; "Content-Type"="application/json"} `
      -Body $profileBody `
      -UseBasicParsing | Out-Null
    Write-Host "Username min length lowered to 2."
} else {
    Write-Host "Username min length already <= 2."
}

# Create user "sa"
Write-Host "Creating user 'sa'..."
$userJson = @{
    username = "sa"
    enabled = $true
    credentials = @(
        @{
            type = "password"
            value = $saPassword
            temporary = $false
        }
    )
    attributes = @{
        UserName = @("sa")
        UserId = @("sa")
    }
} | ConvertTo-Json

try {
    $createUserResponse = Invoke-WebRequest -Uri "$keycloakUrl/admin/realms/$realm/users" `
      -Method Post `
      -Headers @{"Authorization"="Bearer $token"; "Content-Type"="application/json"} `
      -Body $userJson `
      -UseBasicParsing
    Write-Host "Status: $($createUserResponse.StatusCode)"
    if ($createUserResponse.StatusCode -eq 201) {
        Write-Host "User 'sa' created successfully!"
    }
} catch {
    $status = $_.Exception.Response.StatusCode.value__
    if ($status -eq 409) {
        Write-Host "User 'sa' already exists. Nothing to do."
    } else {
        Write-Host "Failed to create user 'sa' (HTTP $status): $($_.ErrorDetails.Message)"
        throw
    }
}

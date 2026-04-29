$keycloakUrl = "http://localhost:8080"
$adminUser = "admin"
$adminPass = "admin"
$realm = "nexus"

# Get admin token
Write-Host "Getting admin token..."
$tokenResponse = Invoke-WebRequest -Uri "$keycloakUrl/realms/master/protocol/openid-connect/token" `
  -Method Post `
  -Headers @{"Content-Type"="application/x-www-form-urlencoded"} `
  -Body "client_id=admin-cli&username=$adminUser&password=$adminPass&grant_type=password" `
  -UseBasicParsing

$token = ($tokenResponse.Content | ConvertFrom-Json).access_token
Write-Host "Token obtained successfully"

# Create user "sa" 
Write-Host "Creating user 'sa'..."
$userJson = @{
    username = "sa"
    enabled = $true
    credentials = @(
        @{
            type = "password"
            value = "password"
            temporary = $false
        }
    )
    attributes = @{
        UserName = @("sa")
        UserId = @("sa")
    }
} | ConvertTo-Json

$createUserResponse = Invoke-WebRequest -Uri "$keycloakUrl/admin/realms/$realm/users" `
  -Method Post `
  -Headers @{"Authorization"="Bearer $token"; "Content-Type"="application/json"} `
  -Body $userJson `
  -UseBasicParsing

Write-Host "Status: $($createUserResponse.StatusCode)"
if ($createUserResponse.StatusCode -eq 201) {
    Write-Host "User 'sa' created successfully!"
} else {
    Write-Host "Response: $($createUserResponse.Content)"
}

$ErrorActionPreference = 'Stop'
$kc = "http://localhost:8080"
$realm = "nexus"
try {
    $tok = ((Invoke-WebRequest -Uri "$kc/realms/master/protocol/openid-connect/token" -Method Post -Headers @{ "Content-Type" = "application/x-www-form-urlencoded" } -Body "client_id=admin-cli&username=admin&password=admin&grant_type=password" -UseBasicParsing).Content | ConvertFrom-Json).access_token
    "ADMIN TOKEN OK"
}
catch {
    "KEYCLOAK ERROR: $($_.Exception.Message)"
    exit 1
}
$u = (Invoke-WebRequest -Uri "$kc/admin/realms/$realm/users?username=cogsuser&exact=true" -Headers @{ "Authorization" = "Bearer $tok" } -UseBasicParsing).Content | ConvertFrom-Json
$uid = $u[0].id
"cogsuser id: $uid"
"CURRENT GROUPS:"
(Invoke-WebRequest -Uri "$kc/admin/realms/$realm/users/$uid/groups" -Headers @{ "Authorization" = "Bearer $tok" } -UseBasicParsing).Content | ConvertFrom-Json | ForEach-Object { "  " + $_.name }
"ALL GROUPS:"
(Invoke-WebRequest -Uri "$kc/admin/realms/$realm/groups" -Headers @{ "Authorization" = "Bearer $tok" } -UseBasicParsing).Content | ConvertFrom-Json | ForEach-Object { "  " + $_.name + "  =>  " + $_.id }

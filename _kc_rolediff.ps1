$ErrorActionPreference = 'Stop'
$kc = "http://localhost:8080"
$realm = "nexus"
$tok = ((Invoke-WebRequest -Uri "$kc/realms/master/protocol/openid-connect/token" -Method Post -Headers @{ "Content-Type" = "application/x-www-form-urlencoded" } -Body "client_id=admin-cli&username=admin&password=admin&grant_type=password" -UseBasicParsing).Content | ConvertFrom-Json).access_token
$H = @{ "Authorization" = "Bearer $tok" }

# nexus-api client
$clients = (Invoke-WebRequest -Uri "$kc/admin/realms/$realm/clients?clientId=nexus-api" -Headers $H -UseBasicParsing).Content | ConvertFrom-Json
$clientId = $clients[0].id
"nexus-api client uuid: $clientId"

# all client roles
$allRoles = (Invoke-WebRequest -Uri "$kc/admin/realms/$realm/clients/$clientId/roles?max=1000" -Headers $H -UseBasicParsing).Content | ConvertFrom-Json
"TOTAL nexus-api client roles: $($allRoles.Count)"

# Nexus All Roles group
$groups = (Invoke-WebRequest -Uri "$kc/admin/realms/$realm/groups?search=Nexus%20All%20Roles" -Headers $H -UseBasicParsing).Content | ConvertFrom-Json
$grp = $groups | Where-Object { $_.name -eq "Nexus All Roles" } | Select-Object -First 1
"Nexus All Roles group id: $($grp.id)"

# group's mapped client roles for nexus-api
$mapped = (Invoke-WebRequest -Uri "$kc/admin/realms/$realm/groups/$($grp.id)/role-mappings/clients/$clientId/composite" -Headers $H -UseBasicParsing).Content | ConvertFrom-Json
"GROUP mapped nexus-api roles: $($mapped.Count)"

$allNames = $allRoles.name | Sort-Object
$mappedNames = $mapped.name | Sort-Object
$missing = $allNames | Where-Object { $mappedNames -notcontains $_ }
"---- MISSING roles (in client, NOT mapped to group) : $($missing.Count) ----"
$missing | ForEach-Object { "  $_" }

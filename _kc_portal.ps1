$ErrorActionPreference = 'Stop'
$kc = "http://localhost:8080"
$realm = "nexus"
$tok = ((Invoke-WebRequest -Uri "$kc/realms/master/protocol/openid-connect/token" -Method Post -Headers @{ "Content-Type" = "application/x-www-form-urlencoded" } -Body "client_id=admin-cli&username=admin&password=admin&grant_type=password" -UseBasicParsing).Content | ConvertFrom-Json).access_token
$H = @{ "Authorization" = "Bearer $tok" }

$portal = ((Invoke-WebRequest -Uri "$kc/admin/realms/$realm/clients?clientId=nexus-portal" -Headers $H -UseBasicParsing).Content | ConvertFrom-Json)[0]
"nexus-portal uuid: $($portal.id)"
"fullScopeAllowed: $($portal.fullScopeAllowed)"

$napi = ((Invoke-WebRequest -Uri "$kc/admin/realms/$realm/clients?clientId=nexus-api" -Headers $H -UseBasicParsing).Content | ConvertFrom-Json)[0]
$napiId = $napi.id

# portal scope mappings for nexus-api client roles (if fullScope off, these are what's allowed)
try {
    $scoped = (Invoke-WebRequest -Uri "$kc/admin/realms/$realm/clients/$($portal.id)/scope-mappings/clients/$napiId" -Headers $H -UseBasicParsing).Content | ConvertFrom-Json
    "portal scope-mapped nexus-api roles (direct): $($scoped.Count)"
    $scoped.name | Sort-Object | ForEach-Object { "  $_" }
} catch { "scope-mappings error: $($_.Exception.Message)" }

# Starts the Cornerstone Agency desktop bridge (Prima + Laya) on the tailnet IP, port 9121.
$ip = ''
for ($i=0; $i -lt 60 -and -not $ip; $i++) { try { $ip = (& tailscale ip -4 2>$null | Select-Object -First 1).Trim() } catch {}; if (-not $ip) { Start-Sleep 5 } }
$env:AGENCY_BRIDGE_HOST = $ip
Set-Location 'C:\Cornerstone\projects\cornerstone-agency\bridge'
& 'C:\Cornerstone\tools\uv\uv.exe' run --frozen agency-bridge

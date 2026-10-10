# Only useful when Tailscale is installed (the script exits at once otherwise).
$tools = "$env:USERPROFILE\.hermes\tools"
if (-not (Test-Path $tools)) { $tools = "$env:LOCALAPPDATA\hermes\tools" }
$pyDir = Get-ChildItem $tools -Directory -Filter 'python-3.*' -ErrorAction SilentlyContinue | Select-Object -First 1
if (-not $pyDir) { exit 0 }
$py = Join-Path $pyDir.FullName 'python.exe'
Start-Process -WindowStyle Hidden -FilePath $py -ArgumentList "$env:LOCALAPPDATA\hermes\scripts\agency-usb-forward.py"

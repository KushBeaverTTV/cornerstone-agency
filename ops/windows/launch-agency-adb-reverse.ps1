Start-Process powershell -WindowStyle Hidden -ArgumentList '-NoProfile','-ExecutionPolicy','Bypass','-File',"$env:LOCALAPPDATA\hermes\scripts\agency-adb-reverse.ps1"

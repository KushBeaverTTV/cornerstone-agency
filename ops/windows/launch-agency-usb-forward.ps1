$py = (Get-ChildItem "$env:LOCALAPPDATA\hermes\tools" -Directory -Filter 'python-3.14*' | Select-Object -First 1).FullName + '\python.exe'
Start-Process -WindowStyle Hidden -FilePath $py -ArgumentList "$env:LOCALAPPDATA\hermes\scripts\agency-usb-forward.py"

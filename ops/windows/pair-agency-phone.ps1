# Opens the phone pairing QR (Cornerstone Agency -> Me -> Pair with PC -> scan).
Set-Location 'C:\Cornerstone\projects\cornerstone-agency\bridge'
& 'C:\Cornerstone\tools\uv\uv.exe' run --frozen agency-bridge pair

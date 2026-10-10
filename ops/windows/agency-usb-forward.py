"""USB forwarder: phone -> adb reverse -> 127.0.0.1:9119/9121 -> the tailnet IP the services bind.

Only needed when Tailscale is installed (the services then bind the tailnet IP, not loopback).
Without Tailscale the services already listen on 127.0.0.1 and this script exits at once.
"""
import asyncio, shutil, subprocess, sys

def tailnet_ip():
    if not shutil.which("tailscale"):
        return ""
    try:
        out = subprocess.run(["tailscale", "ip", "-4"], capture_output=True, text=True, timeout=10)
        return (out.stdout or "").strip().splitlines()[0].strip()
    except Exception:
        return ""

async def pipe(r, w, ip, rewrite=False):
    try:
        while (d := await r.read(65536)):
            if rewrite:
                d = d.replace(b'Host: 127.0.0.1:9119', f'Host: {ip}:9119'.encode()).replace(b'host: 127.0.0.1:9119', f'host: {ip}:9119'.encode())
            w.write(d); await w.drain()
    except Exception:
        pass
    finally:
        w.close()

async def handle(r, w, dst):
    try:
        dr, dw = await asyncio.open_connection(*dst)
    except Exception:
        w.close(); return
    await asyncio.gather(pipe(r, dw, dst[0], True), pipe(dr, w, dst[0]))

async def main():
    ip = tailnet_ip()
    if not ip:
        sys.exit(0)
    ss = [await asyncio.start_server(lambda r, w, p=p: handle(r, w, (ip, p)), '127.0.0.1', p) for p in (9119, 9121)]
    await asyncio.gather(*(s.serve_forever() for s in ss))

asyncio.run(main())

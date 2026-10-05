import asyncio, sys
async def pipe(r,w,rewrite=False):
    try:
        while (d:=await r.read(65536)):
            if rewrite: d=d.replace(b'Host: 127.0.0.1:9119',b'Host: 100.84.54.112:9119').replace(b'host: 127.0.0.1:9119',b'host: 100.84.54.112:9119')
            w.write(d); await w.drain()
    except Exception: pass
    finally: w.close()
async def handle(r,w,dst):
    try: dr,dw=await asyncio.open_connection(*dst)
    except Exception: w.close(); return
    await asyncio.gather(pipe(r,dw,True),pipe(dr,w))
async def main():
    ip='100.84.54.112'
    ss=[await asyncio.start_server(lambda r,w,p=p:handle(r,w,(ip,p)),'127.0.0.1',p) for p in (9119,9121)]
    await asyncio.gather(*(s.serve_forever() for s in ss))
asyncio.run(main())

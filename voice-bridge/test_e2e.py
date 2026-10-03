"""E2E: synthesize a spoken question, stream it to the bridge like the phone does, print what comes back."""
import asyncio, base64, json, os, subprocess, sys, tempfile, time
import aiohttp, edge_tts
FF = r"C:\Users\antho\AppData\Local\hermes\tools\ffmpeg-9.0.1-win32-x64\bin\ffmpeg.exe"
TOKEN = [l.split("=",1)[1].strip() for l in open(r"C:\Cornerstone\Cornerstone_Secrets\agency-voice.env") if l.startswith("HERMES_VOICE_BRIDGE_TOKEN=")][0]
Q = sys.argv[1] if len(sys.argv) > 1 else "Hey Kaori, what is seven times eight?"
async def main():
    mp3 = os.path.join(tempfile.gettempdir(), "q.mp3")
    await edge_tts.Communicate(Q, "en-US-GuyNeural").save(mp3)
    pcm = subprocess.run([FF,"-v","error","-i",mp3,"-ac","1","-ar","16000","-f","s16le","pipe:1"],capture_output=True).stdout
    pcm = b"\x00"*32000 + pcm + b"\x00"*32000   # 1 s silence either side so VAD closes the turn
    async with aiohttp.ClientSession() as s:
        async with s.ws_connect("ws://127.0.0.1:8766/v1/audio", headers={"Authorization": f"Bearer {TOKEN}"}) as ws:
            await ws.send_json({"type":"auth","token":TOKEN,"caps":["control"]})
            async def feed():
                await asyncio.sleep(1)
                for i in range(0, len(pcm), 640):
                    await ws.send_json({"type":"audio","codec":"pcm16","data":base64.b64encode(pcm[i:i+640]).decode()})
                    await asyncio.sleep(0.02)
            ft = asyncio.create_task(feed()); t0 = time.time(); audio = 0
            async for m in ws:
                o = json.loads(m.data)
                if o["type"] == "audio":
                    audio += len(base64.b64decode(o["data"]))
                    if audio == len(base64.b64decode(o["data"])): print(f"[{time.time()-t0:5.1f}s] first audio chunk")
                else: print(f"[{time.time()-t0:5.1f}s]", {k:v for k,v in o.items()})
                if time.time()-t0 > 90 or (audio > 16000 and o["type"]=="status" and str(o.get("text","")).startswith("Listening") ): break
            print("audio bytes received:", audio, "≈", round(audio/32000,1), "s of speech")
asyncio.run(main())

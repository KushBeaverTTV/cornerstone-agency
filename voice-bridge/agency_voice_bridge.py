"""Hermes voice bridge: PC-side backend for the Hermes Android assistant app.

The Android app (com.nousresearch.hermes.android) opens a WebSocket to
``ws://<pc>:8766/v1/audio`` and speaks a small JSON protocol:

    phone -> bridge   {"type":"auth","token":"..."}
                      {"type":"audio","codec":"pcm16","data":"<base64 16 kHz mono s16le>"}
                      {"type":"end_of_turn"}
    bridge -> phone   {"type":"status","text":"..."}          shown on the voice plate
                      {"type":"audio","codec":"pcm16","data":"<base64 16 kHz mono s16le>"}
                      {"type":"error","message":"..."}

The app streams microphone audio continuously and only sends ``end_of_turn`` when
the plate is closed, so turn detection happens here (energy VAD). Each turn is
transcribed with the Hermes agent's own STT stack, sent to the local Hermes agent
API server (``/v1/chat/completions`` on :8642) and the reply is spoken back with
the agent's TTS stack, converted to 16 kHz PCM with ffmpeg.

Run via ``start.cmd`` (uses the hermes-agent venv, HERMES_HOME and PYTHONPATH).
"""

from __future__ import annotations

import asyncio
import base64
import json
import logging
import os
import re
import secrets
import shutil
import struct
import subprocess
import sys
import tempfile
import time
import wave
from logging.handlers import RotatingFileHandler
from pathlib import Path
from typing import Any, Dict, List, Optional

import aiohttp
from aiohttp import web

log = logging.getLogger("hermes.voice_bridge")

SAMPLE_RATE = 16000
BYTES_PER_SAMPLE = 2
FRAME_MS = 20
FRAME_BYTES = SAMPLE_RATE * BYTES_PER_SAMPLE * FRAME_MS // 1000  # 640

# --- VAD tuning (energy based, int16 RMS); all overridable via env ---
VAD_ABS_THRESHOLD = int(os.environ.get("VAD_ABS_THRESHOLD", "250"))        # absolute RMS floor (phone app applies x4 mic gain; ambient was seen peaking ~430)
VAD_NOISE_MULT = float(os.environ.get("VAD_NOISE_MULT", "3.0"))            # or N x the tracked noise floor, whichever is larger
VAD_START_FRAMES = int(os.environ.get("VAD_START_FRAMES", "3"))            # 60 ms of speech to trigger
VAD_END_SILENCE_MS = int(os.environ.get("VAD_END_SILENCE_MS", "800"))      # silence after speech that ends the turn
VAD_MIN_SPEECH_MS = int(os.environ.get("VAD_MIN_SPEECH_MS", "300"))        # shorter bursts are ignored (clicks, coughs)
VAD_MIN_SPEECH_RATIO = float(os.environ.get("VAD_MIN_SPEECH_RATIO", "0.10"))  # a turn must be at least this fraction speech
VAD_MAX_TURN_MS = int(os.environ.get("VAD_MAX_TURN_MS", "30000"))          # force a turn after this much speech
VAD_PREROLL_MS = int(os.environ.get("VAD_PREROLL_MS", "300"))              # audio kept from before speech onset
VAD_FLOOR_MAX = float(os.environ.get("VAD_FLOOR_MAX", "2000"))             # hard cap on the adaptive noise floor
VAD_AMBIENT_MS = int(os.environ.get("VAD_AMBIENT_MS", "3000"))             # steady above-threshold audio this long is re-classified as ambient

STT_VOCAB = ("Kush, Kaori, Hermes, Cornerstone HQ, Railway, Elite Builders, Maestro, Hindsight, Tailscale, "
             "Discord, Telegram, Claude, Anthropic, MCP, Next.js, Rust, discord.py, Lavalink, Steelers, Lions.")

SESSION_IDLE_RESET_S = 30 * 60   # start a fresh agent conversation after this idle time

TTS_LEAD_CHUNKS = 15             # 100 ms chunks sent instantly before realtime pacing kicks in (~1.5 s of jitter buffer)

VOICE_SYSTEM_PROMPT = (
    "You are being used through a hands-free voice assistant on a phone. "
    "Your reply will be read aloud by text-to-speech, so answer in plain spoken "
    "English: no markdown, no lists, no code blocks, no URLs unless asked. "
    "Keep replies short (one to three sentences) unless the user clearly asks for detail. "
    "Answer directly from what you know — do NOT call tools, read files, or run "
    "commands unless the request genuinely requires real-time data or an action "
    "on the user's machine. Never narrate tool calls or internal reasoning."
)


# ----------------------------------------------------------------------------
# config
# ----------------------------------------------------------------------------

def _load_env_file(path: Path) -> None:
    """Populate os.environ from a KEY=VALUE file without overriding existing values."""
    if not path.is_file():
        return
    for raw in path.read_text(encoding="utf-8", errors="ignore").splitlines():
        line = raw.strip()
        if not line or line.startswith("#") or "=" not in line:
            continue
        key, value = line.split("=", 1)
        key = key.strip()
        value = value.strip().strip('"').strip("'")
        if key and key not in os.environ:
            os.environ[key] = value


class Config:
    def __init__(self) -> None:
        for envf in (r"C:\Cornerstone\Cornerstone_Secrets\agency-dashboard.env", r"C:\Cornerstone\Cornerstone_Secrets\agency-voice.env"):
            _load_env_file(Path(envf))
        hermes_home = Path(os.environ.get("HERMES_HOME", Path.home() / ".hermes"))
        self.hermes_home = hermes_home
        self.host = os.environ.get("HERMES_VOICE_BRIDGE_HOST", "127.0.0.1")
        self.port = int(os.environ.get("HERMES_VOICE_BRIDGE_PORT", "8766"))
        self.token = os.environ.get("HERMES_VOICE_BRIDGE_TOKEN", "").strip()
        self.extra_hosts = [h.strip() for h in os.environ.get("AGENCY_VOICE_EXTRA_HOSTS", "").split(",") if h.strip()]
        self.dash_user = os.environ.get("HERMES_DASHBOARD_BASIC_AUTH_USERNAME", "").strip()
        self.dash_password = os.environ.get("HERMES_DASHBOARD_BASIC_AUTH_PASSWORD", "").strip()
        self.profile = os.environ.get("AGENCY_VOICE_PROFILE", "default")
        self.stt_model = os.environ.get("AGENCY_VOICE_STT_MODEL", "small.en")
        self.tts_voice = os.environ.get("AGENCY_VOICE_TTS_VOICE", "en-US-AriaNeural")
        self.agent_url = os.environ.get("HERMES_AGENT_URL", "http://127.0.0.1:9119").rstrip("/")
        self.agent_key = os.environ.get("API_SERVER_KEY", "").strip()
        self.agent_model = os.environ.get("HERMES_VOICE_BRIDGE_MODEL", "hermes-agent")
        # api_server honors an explicit model only when provider is given too;
        # bare "hermes-agent" (or bare model) falls back to the gateway default.
        self.agent_provider = os.environ.get("HERMES_VOICE_BRIDGE_PROVIDER", "").strip()
        self.agent_timeout = float(os.environ.get("HERMES_VOICE_BRIDGE_AGENT_TIMEOUT", "180"))
        self.session_prefix = os.environ.get("HERMES_VOICE_BRIDGE_SESSION_PREFIX", "android-voice")
        self.tts_provider = os.environ.get("HERMES_VOICE_BRIDGE_TTS", "").strip() or None
        self.tts_speed = float(os.environ.get("HERMES_VOICE_BRIDGE_TTS_SPEED", "1.1"))
        # 'local' = faster-whisper on-device (fast); 'cloud' = configured provider
        self.stt_mode = os.environ.get("HERMES_VOICE_BRIDGE_STT", "local").strip().lower()
        self.stt_timeout = float(os.environ.get("HERMES_VOICE_BRIDGE_STT_TIMEOUT", "45"))
        self.ffmpeg = os.environ.get("FFMPEG_BINARY") or shutil.which("ffmpeg") or ""

    def validate(self) -> List[str]:
        problems = []
        if not self.token:
            problems.append("HERMES_VOICE_BRIDGE_TOKEN is not set")
        if not (self.dash_user and self.dash_password):
            problems.append("HERMES_DASHBOARD_BASIC_AUTH_USERNAME/PASSWORD not set")
        if not self.ffmpeg:
            problems.append("ffmpeg not found on PATH (needed to convert TTS output to PCM)")
        return problems


# ----------------------------------------------------------------------------
# audio helpers
# ----------------------------------------------------------------------------

def frame_rms(frame: bytes) -> float:
    n = len(frame) // 2
    if n == 0:
        return 0.0
    samples = struct.unpack(f"<{n}h", frame[: n * 2])
    return (sum(s * s for s in samples) / n) ** 0.5


def write_wav(path: str, pcm: bytes) -> None:
    with wave.open(path, "wb") as w:
        w.setnchannels(1)
        w.setsampwidth(BYTES_PER_SAMPLE)
        w.setframerate(SAMPLE_RATE)
        w.writeframes(pcm)


def to_pcm16(ffmpeg: str, src_path: str) -> bytes:
    """Decode any audio file to raw 16 kHz mono s16le with ffmpeg."""
    proc = subprocess.run(
        [ffmpeg, "-v", "error", "-i", src_path, "-ac", "1", "-ar", str(SAMPLE_RATE),
         "-f", "s16le", "-acodec", "pcm_s16le", "pipe:1"],
        capture_output=True, timeout=120, check=False)
    if proc.returncode != 0:
        raise RuntimeError(f"ffmpeg failed: {proc.stderr.decode(errors='ignore')[:300]}")
    return proc.stdout


class EnergyVAD:
    """Tiny energy-based voice activity detector over 20 ms PCM frames."""

    def __init__(self) -> None:
        # start at ABS/MULT so the initial threshold equals VAD_ABS_THRESHOLD;
        # from here the floor adapts both directions (the old code clamped it to
        # ABS/MULT forever, so ambient noise above it triggered endless turns)
        self.noise_floor = VAD_ABS_THRESHOLD / VAD_NOISE_MULT
        self.in_speech = False
        self.speech_frames = 0
        self.silence_frames = 0
        self.above_run = 0
        self.preroll: List[bytes] = []
        self.turn: bytearray = bytearray()
        self._pending = bytearray()
        self.turn_min_rms = float("inf")
        self.turn_max_rms = 0.0
        # raised while a reply is playing so TTS echo can't trigger a turn;
        # a real interruption needs louder, sustained speech
        self.threshold_mult = 1.0
        self.start_frames = VAD_START_FRAMES

    def reset(self) -> None:
        self.in_speech = False
        self.speech_frames = 0
        self.silence_frames = 0
        self.above_run = 0
        self.preroll.clear()
        self.turn = bytearray()
        self._pending = bytearray()
        self.turn_min_rms = float("inf")
        self.turn_max_rms = 0.0
        self.threshold_mult = 1.0
        self.start_frames = VAD_START_FRAMES

    def _track_floor(self, rms: float) -> None:
        """Fold a sub-threshold frame into the noise floor: fast descent, slow
        ascent, capped so the floor can never ratchet itself out of reach."""
        alpha = 0.02 if rms > self.noise_floor else 0.10
        self.noise_floor += alpha * (max(rms, 1.0) - self.noise_floor)
        self.noise_floor = min(max(self.noise_floor, 1.0), VAD_FLOOR_MAX)

    def _drop_turn(self) -> None:
        self.in_speech = False
        self.turn = bytearray()
        self.preroll.clear()
        self.speech_frames = 0
        self.silence_frames = 0
        self.turn_min_rms = float("inf")
        self.turn_max_rms = 0.0

    def feed(self, pcm: bytes) -> Optional[bytes]:
        """Feed PCM; returns a completed utterance (bytes) when a turn ends, else None."""
        self._pending.extend(pcm)
        result = None
        while len(self._pending) >= FRAME_BYTES and result is None:
            frame = bytes(self._pending[:FRAME_BYTES])
            del self._pending[:FRAME_BYTES]
            result = self._frame(frame)
        return result

    def _frame(self, frame: bytes) -> Optional[bytes]:
        rms = frame_rms(frame)
        threshold = max(VAD_ABS_THRESHOLD, self.noise_floor * VAD_NOISE_MULT) * self.threshold_mult
        is_speech = rms > threshold
        if not self.in_speech:
            # every sub-threshold frame is an ambient sample — this is what lets
            # the floor rise to meet a noisy room instead of triggering on it
            if not is_speech:
                self._track_floor(rms)
            self.preroll.append(frame)
            max_preroll = VAD_PREROLL_MS // FRAME_MS
            if len(self.preroll) > max_preroll:
                self.preroll.pop(0)
            self.above_run = self.above_run + 1 if is_speech else 0
            if self.above_run >= self.start_frames:
                self.in_speech = True
                self.turn = bytearray(b"".join(self.preroll))
                self.speech_frames = self.above_run
                self.silence_frames = 0
                self.above_run = 0
            return None

        self.turn.extend(frame)
        self.turn_min_rms = min(self.turn_min_rms, rms)
        self.turn_max_rms = max(self.turn_max_rms, rms)
        if not is_speech:
            # quiet gaps inside a turn still sample the ambient level
            self._track_floor(rms)
        if is_speech:
            self.speech_frames += 1
            self.silence_frames = 0
        else:
            self.silence_frames += 1
        speech_ms = self.speech_frames * FRAME_MS
        silence_ms = self.silence_frames * FRAME_MS
        total_ms = len(self.turn) * 1000 // (SAMPLE_RATE * BYTES_PER_SAMPLE)
        # ambient ratchet: a long "turn" whose every frame sits just above the
        # trigger level with little dynamic range is a raised noise floor, not
        # speech — fold it into the floor and drop the buffer instead of
        # running out VAD_MAX_TURN_MS on phantom audio
        if (total_ms >= VAD_AMBIENT_MS
                and self.turn_min_rms > threshold
                and self.turn_min_rms <= threshold * 4.0
                and self.turn_max_rms <= self.turn_min_rms * 3.0):
            self.noise_floor = min(max(self.noise_floor, self.turn_min_rms), VAD_FLOOR_MAX)
            log.info("sustained level %.0f re-classified as ambient; noise floor -> %.0f",
                     self.turn_min_rms, self.noise_floor)
            self._drop_turn()
            return None
        if silence_ms >= VAD_END_SILENCE_MS or total_ms >= VAD_MAX_TURN_MS:
            utterance = bytes(self.turn)
            turn_frames = len(self.turn) // FRAME_BYTES
            speech_ratio = self.speech_frames / max(1, turn_frames)
            had_speech = (speech_ms >= VAD_MIN_SPEECH_MS
                          and speech_ratio >= VAD_MIN_SPEECH_RATIO)
            if not had_speech:
                log.info("dropping %.1fs turn: %dms speech, ratio %.2f",
                         total_ms / 1000, speech_ms, speech_ratio)
            self._drop_turn()
            return utterance if had_speech else None
        return None

    def flush(self) -> Optional[bytes]:
        """Return the in-progress utterance (used on end_of_turn)."""
        if self.in_speech:
            speech_ms = self.speech_frames * FRAME_MS
            turn_frames = len(self.turn) // FRAME_BYTES
            speech_ratio = self.speech_frames / max(1, turn_frames)
            if (speech_ms >= VAD_MIN_SPEECH_MS
                    and speech_ratio >= VAD_MIN_SPEECH_RATIO):
                utterance = bytes(self.turn)
                self.reset()
                return utterance
        self.reset()
        return None


# ----------------------------------------------------------------------------
# hermes-agent integration (STT / agent / TTS)
# ----------------------------------------------------------------------------

class HermesBackend:
    """Self-contained backend: faster-whisper STT, edge-tts TTS, and the Hermes
    dashboard (`hermes serve`) JSON-RPC websocket as the agent."""

    def __init__(self, cfg: Config) -> None:
        self.cfg = cfg
        self._tts_dir: Optional[str] = None
        self._stt_model = None
        self._stt_lock = asyncio.Lock()
        self._http: Optional[aiohttp.ClientSession] = None
        self._ws: Optional[aiohttp.ClientWebSocketResponse] = None
        self._reader: Optional[asyncio.Task] = None
        self._conn_lock = asyncio.Lock()
        self._rpc_id = 0
        self._pending: Dict[int, asyncio.Future] = {}
        self._queues: Dict[str, asyncio.Queue] = {}
        self._sessions: Dict[str, tuple] = {}   # key -> (runtime_sid, last_used, primed)

    async def start(self) -> None:
        jar = aiohttp.CookieJar(unsafe=True)
        self._http = aiohttp.ClientSession(cookie_jar=jar, timeout=aiohttp.ClientTimeout(total=30))

    async def close(self) -> None:
        if self._reader:
            self._reader.cancel()
        if self._ws is not None and not self._ws.closed:
            await self._ws.close()
        if self._http:
            await self._http.close()
        if self._tts_dir:
            shutil.rmtree(self._tts_dir, ignore_errors=True)
            self._tts_dir = None

    # -- dashboard connection ---------------------------------------------
    async def _connect(self) -> None:
        assert self._http
        base = self.cfg.agent_url
        async with self._http.post(f"{base}/auth/password-login", json={
                "provider": "basic", "username": self.cfg.dash_user,
                "password": self.cfg.dash_password}) as r:
            if r.status != 200:
                raise RuntimeError(f"dashboard login failed (HTTP {r.status})")
        async with self._http.post(f"{base}/api/auth/ws-ticket", json={}) as r:
            body = await r.text()
            if r.status != 200:
                raise RuntimeError(f"ws-ticket failed (HTTP {r.status})")
        try:
            el = json.loads(body)
        except json.JSONDecodeError:
            el = body.strip().strip('"')
        ticket = el if isinstance(el, str) else (el.get("ticket") or el.get("token"))
        ws_url = base.replace("http://", "ws://").replace("https://", "wss://") + f"/api/ws?ticket={ticket}"
        self._ws = await self._http.ws_connect(ws_url, heartbeat=20, max_msg_size=16 * 1024 * 1024)
        self._reader = asyncio.create_task(self._read_loop(self._ws))
        self._sessions.clear()  # runtime sessions died with the old socket

    async def _ensure_ws(self) -> None:
        async with self._conn_lock:
            if self._ws is None or self._ws.closed:
                await self._connect()

    async def _read_loop(self, ws) -> None:
        try:
            async for m in ws:
                if m.type != aiohttp.WSMsgType.TEXT:
                    continue
                try:
                    obj = json.loads(m.data)
                except json.JSONDecodeError:
                    continue
                if "id" in obj and ("result" in obj or "error" in obj):
                    fut = self._pending.pop(obj["id"], None)
                    if fut and not fut.done():
                        if "error" in obj:
                            fut.set_exception(RuntimeError(str(obj["error"].get("message") or obj["error"])))
                        else:
                            fut.set_result(obj.get("result"))
                elif obj.get("method") == "event":
                    p = obj.get("params") or {}
                    sid = p.get("session_id")
                    q = self._queues.get(sid)
                    if q is not None:
                        q.put_nowait(p)
        except asyncio.CancelledError:
            raise
        except Exception as exc:  # noqa: BLE001
            log.warning("dashboard socket reader ended: %s", exc)
        finally:
            for fut in self._pending.values():
                if not fut.done():
                    fut.set_exception(RuntimeError("dashboard socket closed"))
            self._pending.clear()

    async def _rpc(self, method: str, params: Dict[str, Any], timeout: float = 30) -> Any:
        await self._ensure_ws()
        self._rpc_id += 1
        rid = self._rpc_id
        fut = asyncio.get_running_loop().create_future()
        self._pending[rid] = fut
        await self._ws.send_str(json.dumps({"jsonrpc": "2.0", "id": rid, "method": method, "params": params}))
        return await asyncio.wait_for(fut, timeout)

    async def agent_health(self) -> Dict[str, Any]:
        try:
            assert self._http
            async with self._http.get(f"{self.cfg.agent_url}/api/health",
                                      timeout=aiohttp.ClientTimeout(total=5)) as r:
                return {"ok": r.status == 200, "status": r.status, "body": await r.json(content_type=None)}
        except Exception as exc:  # noqa: BLE001
            return {"ok": False, "error": str(exc)}

    # -- STT -------------------------------------------------------------
    def _load_stt(self):
        if self._stt_model is None:
            from faster_whisper import WhisperModel
            log.info("loading faster-whisper %s ...", self.cfg.stt_model)
            self._stt_model = WhisperModel(self.cfg.stt_model, device="cpu", compute_type="int8",
                                           cpu_threads=min(16, os.cpu_count() or 8))
        return self._stt_model

    async def transcribe(self, pcm: bytes) -> str:
        tmpdir = tempfile.mkdtemp(prefix="agency-voice-")
        wav_path = os.path.join(tmpdir, "turn.wav")
        try:
            write_wav(wav_path, pcm)

            def _run() -> str:
                m = self._load_stt()
                segs, _ = m.transcribe(wav_path, language="en", vad_filter=True,
                                       initial_prompt=STT_VOCAB, condition_on_previous_text=False)
                return " ".join(s.text.strip() for s in segs).strip()

            async with self._stt_lock:
                return await asyncio.wait_for(asyncio.to_thread(_run), self.cfg.stt_timeout)
        finally:
            shutil.rmtree(tmpdir, ignore_errors=True)

    async def warmup_stt(self) -> None:
        def _w():
            m = self._load_stt()
            tmpdir = tempfile.mkdtemp(prefix="agency-voice-warm-")
            try:
                p = os.path.join(tmpdir, "warm.wav")
                write_wav(p, b"\x00" * (SAMPLE_RATE * BYTES_PER_SAMPLE))
                list(m.transcribe(p, language="en")[0])
            finally:
                shutil.rmtree(tmpdir, ignore_errors=True)
        try:
            await asyncio.to_thread(_w)
            log.info("stt warm")
        except Exception as exc:  # noqa: BLE001
            log.warning("stt warmup failed: %s", exc)

    # -- agent -----------------------------------------------------------
    @staticmethod
    def _clean_session_key(session_key: Any) -> str:
        key = re.sub(r"[^A-Za-z0-9_.-]+", "-", str(session_key or "")).strip("-")
        return key or "default"

    async def _session_for(self, session_key: Any):
        key = self._clean_session_key(session_key)
        now = time.time()
        sid, last, primed = self._sessions.get(key, (None, 0.0, False))
        if not sid or now - last > SESSION_IDLE_RESET_S:
            res = await self._rpc("session.create", {"profile": self.cfg.profile})
            sid = res["session_id"]
            primed = False
            log.info("new agent session for %s: %s", key, sid)
        return key, sid, primed

    async def ask_agent_stream(self, text: str, session_key: Any = "default"):
        key, sid, primed = await self._session_for(session_key)
        q: asyncio.Queue = asyncio.Queue()
        self._queues[sid] = q
        try:
            prompt = text if primed else f"{VOICE_SYSTEM_PROMPT}\n\nThe user said (voice): {text}"
            self._sessions[key] = (sid, time.time(), True)
            await self._rpc("prompt.submit", {"session_id": sid, "text": prompt})
            got_delta = False
            deadline = time.time() + self.cfg.agent_timeout
            while True:
                remaining = deadline - time.time()
                if remaining <= 0:
                    raise RuntimeError("agent timed out")
                try:
                    ev = await asyncio.wait_for(q.get(), remaining)
                except asyncio.TimeoutError:
                    raise RuntimeError("agent timed out")
                t = ev.get("type")
                p = ev.get("payload") or {}
                if t == "message.delta":
                    d = p.get("delta") or p.get("text") or ""
                    if d:
                        got_delta = True
                        yield d
                elif t in ("message.complete", "message.interim"):
                    if not got_delta and p.get("text"):
                        yield p["text"]
                    if t == "message.complete":
                        break
                elif t in ("approval.request", "clarify.request", "secret.request"):
                    # voice can't answer these; refuse so the turn doesn't hang
                    log.warning("agent asked for %s during voice turn; aborting turn", t)
                    try:
                        await self._rpc("session.interrupt", {"session_id": sid}, timeout=10)
                    except Exception:  # noqa: BLE001
                        pass
                    yield "I need you to approve something on your screen for that one."
                    break
                elif t in ("error", "turn.error", "run.error"):
                    raise RuntimeError(str(p.get("message") or p.get("error") or p)[:200])
        finally:
            self._queues.pop(sid, None)
            self._sessions[key] = (sid, time.time(), True)

    async def ask_agent(self, text: str, session_key: Any = "default") -> str:
        out = ""
        async for d in self.ask_agent_stream(text, session_key):
            out += d
        return out.strip()

    # -- TTS -------------------------------------------------------------
    async def synthesize_pcm(self, text: str) -> bytes:
        import edge_tts
        if not self._tts_dir:
            self._tts_dir = tempfile.mkdtemp(prefix="agency-voice-tts-")
        out_path = os.path.join(self._tts_dir, f"reply-{secrets.token_hex(4)}.mp3")
        try:
            rate = f"{int(round((self.cfg.tts_speed - 1.0) * 100)):+d}%"
            await asyncio.wait_for(
                edge_tts.Communicate(text, self.cfg.tts_voice, rate=rate).save(out_path), 30)
            return await asyncio.wait_for(asyncio.to_thread(to_pcm16, self.cfg.ffmpeg, out_path), 30)
        finally:
            try:
                os.remove(out_path)
            except OSError:
                pass




def infer_message_type(msg: Dict[str, Any]) -> str:
    """The app serialises with kotlinx ``encodeDefaults=false``, so the ``type`` field
    (which always has a default) is usually *absent* on the wire: auth arrives as
    ``{"token": ...}``, audio as ``{"codec": ..., "data": ...}`` and end_of_turn as ``{}``."""
    mtype = msg.get("type")
    if isinstance(mtype, str) and mtype:
        return mtype
    if "token" in msg:
        return "auth"
    if "data" in msg or "codec" in msg:
        return "audio"
    if not msg:
        return "end_of_turn"
    return "unknown"


def spoken_display_text(text: str, limit: int = 240) -> str:
    text = re.sub(r"\s+", " ", text).strip()
    return text if len(text) <= limit else text[: limit - 1].rstrip() + "…"


_FILLER_ONLY = re.compile(r"^[\W_]*(?:uh|um|ah|eh|er|mm+|hm+|sh+)[\W_\s]*$", re.IGNORECASE)


def transcript_is_usable(text: str) -> bool:
    """Reject VAD false-positive transcripts ('. .', 'uh', pure punctuation)."""
    t = (text or "").strip()
    if not t or _FILLER_ONLY.match(t):
        return False
    return bool(re.search(r"[A-Za-z0-9]", t))


_SENT_BOUNDARY = re.compile(r"([^.!?\n]*[.!?]+[\"')\]]*\s+|\n+)")


def pop_sentence(buf: str) -> tuple[Optional[str], str]:
    """Pop the first complete sentence (or line) off the stream buffer.

    A boundary only counts if followed by more text — a trailing '.' mid-token
    may still be part of an abbreviation like 'e.g.'."""
    m = _SENT_BOUNDARY.match(buf)
    if m and len(buf) > m.end():
        return buf[: m.end()].strip(), buf[m.end():]
    return None, buf


def speakable_text(text: str) -> bool:
    """TTS needs at least one alphanumeric character — punctuation/whitespace-only
    input makes providers fail with 'Text is required' and must never be sent."""
    return bool(re.search(r"[A-Za-z0-9]", text or ""))


# ----------------------------------------------------------------------------
# websocket session
# ----------------------------------------------------------------------------

class PhoneSession:
    def __init__(self, ws: web.WebSocketResponse, backend: HermesBackend, peer: str) -> None:
        self.ws = ws
        self.backend = backend
        self.peer = peer
        self.vad = EnergyVAD()
        self.busy = False
        self.mute_until = 0.0
        self.turn_task: Optional[asyncio.Task] = None
        self.authed = False
        self.audio_frames = 0
        self.bad_frames = 0
        self.caps: set = set()
        self.mic_paused = False
        self.level_max = 0.0
        self.level_logged_at = time.time()
        self._audio_queue: asyncio.Queue = asyncio.Queue()
        self._sender_task: Optional[asyncio.Task] = None
        self._first_audio_at: Optional[float] = None

    async def send(self, msg: Dict[str, Any]) -> None:
        if not self.ws.closed:
            await self.ws.send_str(json.dumps(msg, ensure_ascii=False))

    async def status(self, text: str) -> None:
        await self.send({"type": "status", "text": text})

    async def error(self, message: str) -> None:
        await self.send({"type": "error", "message": message})

    async def control(self, **fields: Any) -> None:
        """Send a {"type":"control",...} message — only to clients that
        advertised the "control" capability in their auth message (older
        installed clients fail to decode unknown message types)."""
        if "control" not in self.caps or self.ws.closed:
            return
        try:
            await self.send({"type": "control", **fields})
        except Exception:  # noqa: BLE001
            pass

    async def pause_mic(self) -> None:
        """Ask the phone to pause mic capture while TTS audio is playing."""
        if not self.mic_paused:
            self.mic_paused = True
            await self.control(record=False)

    async def resume_mic(self) -> None:
        if self.mic_paused:
            self.mic_paused = False
            await self.control(record=True)

    def _set_caps(self, msg: Dict[str, Any]) -> None:
        caps = msg.get("caps")
        if isinstance(caps, str):
            caps = [caps]
        if isinstance(caps, list):
            self.caps = {str(c) for c in caps}

    async def _audio_sender(self) -> None:
        """Serial playback pump: sentences leave in order, never interleaved.

        Previously each sentence spawned its own send_pcm task and they all
        dripped chunks onto the same socket concurrently — overlapping,
        rushing, garbled playback. One pump, FIFO, exact pacing.
        """
        try:
            while True:
                pcm = await self._audio_queue.get()
                try:
                    if self._first_audio_at is None:
                        self._first_audio_at = time.time()
                    await self.send_pcm(pcm)
                finally:
                    self._audio_queue.task_done()
        except asyncio.CancelledError:
            raise

    async def send_pcm(self, pcm: bytes) -> float:
        """Send PCM as 100 ms chunks on a realtime deadline schedule: a short
        burst fills the phone's jitter buffer, then chunks drip out at exactly
        playback rate. Returns playback duration in seconds."""
        chunk = SAMPLE_RATE * BYTES_PER_SAMPLE // 10
        duration = len(pcm) / (SAMPLE_RATE * BYTES_PER_SAMPLE)
        start = time.monotonic()
        sent = 0
        for i in range(0, len(pcm), chunk):
            piece = pcm[i:i + chunk]
            await self.send({"type": "audio", "codec": "pcm16",
                             "data": base64.b64encode(piece).decode("ascii")})
            sent += 1
            if sent > TTS_LEAD_CHUNKS:
                await asyncio.sleep(
                    max(0.0, start + (sent - TTS_LEAD_CHUNKS) * 0.1 - time.monotonic()))
        return duration

    async def _bad_frame(self, reason: str) -> None:
        self.bad_frames += 1
        log.warning("[%s] bad audio frame #%d: %s", self.peer, self.bad_frames, reason)
        if self.bad_frames >= 50:
            log.warning("[%s] too many bad audio frames — disconnecting", self.peer)
            await self.ws.close(code=4400, message=b"too many bad audio frames")

    async def handle_audio(self, msg: Dict[str, Any]) -> None:
        codec = (msg.get("codec") or "pcm16").lower()
        if codec != "pcm16":
            await self.error(f"unsupported codec '{codec}' (bridge expects pcm16)")
            await self._bad_frame(f"unsupported codec '{codec}'")
            return
        try:
            pcm = base64.b64decode(msg.get("data") or "", validate=True)
        except Exception:  # noqa: BLE001
            await self._bad_frame("base64 decode failed")
            return
        self.bad_frames = 0
        self.audio_frames += 1
        if self.audio_frames == 1:
            log.info("[%s] receiving mic audio (codec=%s, %d bytes/msg)", self.peer, codec, len(pcm))
        rms = frame_rms(pcm)
        self.level_max = max(self.level_max, rms)
        now = time.time()
        if now - self.level_logged_at >= 5.0:
            log.info("[%s] mic level: peak_rms=%.0f noise_floor=%.0f threshold=%.0f speaking=%s",
                     self.peer, self.level_max, self.vad.noise_floor,
                     max(VAD_ABS_THRESHOLD, self.vad.noise_floor * VAD_NOISE_MULT)
                     * self.vad.threshold_mult, self.vad.in_speech)
            self.level_max = 0.0
            self.level_logged_at = now
        # Always feed the VAD — even while a reply is streaming — so the user
        # can barge in. During playback we don't drop frames; we just raise the
        # effective threshold (echo rejection) and require sustained speech.
        interrupting = self.busy or now < self.mute_until
        self.vad.threshold_mult = 2.0 if interrupting else 1.0
        self.vad.start_frames = (VAD_START_FRAMES * 2) if interrupting else VAD_START_FRAMES
        utterance = self.vad.feed(pcm)
        if utterance:
            if self.busy:
                log.info("[%s] barge-in: interrupting current turn", self.peer)
                await self.control(interrupt=True)
                if self.turn_task and not self.turn_task.done():
                    self.turn_task.cancel()
            log.info("[%s] turn detected: %.1fs of audio", self.peer,
                     len(utterance) / (SAMPLE_RATE * BYTES_PER_SAMPLE))
            self.turn_task = asyncio.create_task(self.run_turn(utterance))

    async def handle_end_of_turn(self) -> None:
        utterance = self.vad.flush()
        if utterance and not self.busy:
            self.turn_task = asyncio.create_task(self.run_turn(utterance))

    async def run_turn(self, pcm: bytes) -> None:
        self.busy = True
        t0 = time.time()
        total_audio = 0.0
        stt_ms = -1
        first_token_ms = -1
        try:
            await self.status("Transcribing…")
            stt_start = time.time()
            transcript = await self.backend.transcribe(pcm)
            stt_ms = int((time.time() - stt_start) * 1000)
            if not transcript or not transcript_is_usable(transcript):
                await self.status("Didn't catch that — try again")
                return
            log.debug("[%s] heard: %s", self.peer, transcript)
            await self.status(f"“{spoken_display_text(transcript, 120)}”\nThinking…")

            # Stream the reply; TTS each sentence the moment it completes so the
            # phone starts speaking while the model is still generating. The
            # paced send runs as a background task so sentence N+1 is
            # synthesized while sentence N is still playing out.
            full = ""
            pending = ""
            first_audio_at: Optional[float] = None

            async def speak(text: str) -> None:
                nonlocal total_audio, first_audio_at
                if not speakable_text(text):
                    return
                try:
                    pcm_out = await self.backend.synthesize_pcm(text)
                except Exception:  # noqa: BLE001
                    log.warning("[%s] TTS failed for one sentence; continuing stream",
                                self.peer, exc_info=True)
                    return
                if not pcm_out:
                    return
                # Mic stays live during playback — pausing it would make
                # barge-in deaf (the VAD only hears what the phone streams).
                total_audio += len(pcm_out) / (SAMPLE_RATE * BYTES_PER_SAMPLE)
                await self._audio_queue.put(pcm_out)
                if self._first_audio_at is not None and first_audio_at is None:
                    first_audio_at = self._first_audio_at
                    log.info("[%s] first audio at %.1fs", self.peer, first_audio_at - t0)

            last_status = 0.0
            self._first_audio_at = None
            if self._sender_task is None or self._sender_task.done():
                self._sender_task = asyncio.create_task(self._audio_sender())
            stream = self.backend.ask_agent_stream(transcript, self.peer)
            stream_failed = False
            first_token_at = None
            while True:
                # only failures of the stream itself fall back to a sync re-ask;
                # speak() handles its own per-sentence errors and status/send
                # problems propagate to the turn-level handler
                try:
                    delta = await stream.__anext__()
                except StopAsyncIteration:
                    break
                except Exception:
                    log.warning("[%s] streaming failed, falling back to sync turn",
                                self.peer, exc_info=True)
                    stream_failed = True
                    break
                if first_token_at is None and delta.strip():
                    first_token_at = time.time()
                    first_token_ms = int((first_token_at - t0) * 1000)
                full += delta
                pending += delta
                if time.time() - last_status > 0.7:
                    await self.status(spoken_display_text(full))
                    last_status = time.time()
                while True:
                    sent, pending = pop_sentence(pending)
                    if sent is None:
                        break
                    await speak(sent)
            if stream_failed:
                full = await self.backend.ask_agent(transcript, self.peer)
                pending = full

            if pending.strip():
                await speak(pending.strip())
            # drain the playback pump so busy stays set (echo rejection)
            # until the phone has actually received the audio
            try:
                await asyncio.wait_for(self._audio_queue.join(), timeout=120.0)
            except asyncio.TimeoutError:
                log.warning("[%s] audio drain timed out; dropping queued audio", self.peer)
                while not self._audio_queue.empty():
                    try:
                        self._audio_queue.get_nowait()
                        self._audio_queue.task_done()
                    except asyncio.QueueEmpty:
                        break
            if not full.strip():
                await self.status("The agent returned no reply")
                return
            log.debug("[%s] reply (%.1fs): %s", self.peer, time.time() - t0,
                      spoken_display_text(full, 200))
            total_ms = int((time.time() - t0) * 1000)
            first_audio_ms = int((first_audio_at - t0) * 1000) if first_audio_at else -1
            log.info("[%s] TURN stt=%dms first_token=%dms first_audio=%dms total=%dms audio=%.1fs",
                     self.peer, stt_ms, first_token_ms, first_audio_ms, total_ms, total_audio)
            log.info("[%s] reply done in %.1fs (%.1fs of audio)", self.peer,
                     time.time() - t0, total_audio)
            await self.status(spoken_display_text(full))
            # the phone keeps a ~TTS_LEAD_CHUNKS-deep buffer, so playback outlasts
            # the final send by roughly that much
            self.mute_until = time.time() + TTS_LEAD_CHUNKS * 0.1 + 0.3
            await asyncio.sleep(0.3)
            await self.resume_mic()
            await self.status("Listening…")
        except asyncio.CancelledError:
            raise
        except Exception as exc:  # noqa: BLE001
            log.exception("[%s] turn failed", self.peer)
            await self.error(str(exc)[:300])
        finally:
            if self._sender_task is not None and not self._sender_task.done():
                self._sender_task.cancel()
            self._sender_task = None
            while not self._audio_queue.empty():
                try:
                    self._audio_queue.get_nowait()
                    self._audio_queue.task_done()
                except asyncio.QueueEmpty:
                    break
            # a cancelled turn may already have been superseded by a barge-in
            # turn; only clear busy if we're still the current turn
            if self.turn_task is asyncio.current_task():
                self.busy = False
            self.vad.reset()
            await self.resume_mic()

    async def run(self, header_token: str, expected_token: str) -> None:
        async for raw in self.ws:
            if raw.type != aiohttp.WSMsgType.TEXT:
                if raw.type in (aiohttp.WSMsgType.CLOSE, aiohttp.WSMsgType.ERROR):
                    break
                continue
            try:
                msg = json.loads(raw.data)
            except json.JSONDecodeError:
                await self.error("invalid json")
                continue
            mtype = infer_message_type(msg)
            if not self.authed:
                if mtype != "auth":
                    await self.error("auth required")
                    await self.ws.close(code=4401, message=b"auth required")
                    break
                token = (msg.get("token") or header_token or "").strip()
                if not secrets.compare_digest(token, expected_token):
                    log.warning("[%s] bad token", self.peer)
                    await self.error("auth failed: bad token")
                    await self.ws.close(code=4403, message=b"auth failed")
                    break
                self.authed = True
                self._set_caps(msg)
                log.info("[%s] authed (caps=%s)", self.peer, sorted(self.caps) or "none")
                # ConnectionTester in the app waits for exactly this status text.
                await self.status("connected")
                asyncio.get_running_loop().call_later(
                    0.6, lambda: asyncio.ensure_future(self.status("Listening…")))
                continue
            if mtype == "audio":
                await self.handle_audio(msg)
            elif mtype == "end_of_turn":
                await self.handle_end_of_turn()
            elif mtype == "auth":
                self._set_caps(msg)
                await self.status("connected")
            else:
                await self.error(f"unknown message type: {mtype}")
        if self.turn_task and not self.turn_task.done():
            self.turn_task.cancel()


# ----------------------------------------------------------------------------
# aiohttp app
# ----------------------------------------------------------------------------

def build_app(cfg: Config, backend: HermesBackend) -> web.Application:
    app = web.Application()

    async def health(_: web.Request) -> web.Response:
        agent = await backend.agent_health()
        return web.json_response({
            "status": "ok", "service": "agency-voice-bridge",
            "listen": f"{cfg.host}:{cfg.port}", "ws_path": "/v1/audio",
            "agent_url": cfg.agent_url, "agent": agent,
        })

    async def audio_ws(request: web.Request) -> web.StreamResponse:
        ws = web.WebSocketResponse(heartbeat=20, max_msg_size=8 * 1024 * 1024)
        await ws.prepare(request)
        peer = request.remote or "?"
        header_token = ""
        auth = request.headers.get("Authorization", "")
        if auth.lower().startswith("bearer "):
            header_token = auth[7:].strip()
        log.info("[%s] phone connected", peer)
        session = PhoneSession(ws, backend, peer)
        try:
            await session.run(header_token, cfg.token)
        finally:
            log.info("[%s] phone disconnected", peer)
        return ws

    app.router.add_get("/health", health)
    app.router.add_get("/v1/audio", audio_ws)
    return app


def _bootstrap_hermes_imports(cfg: Config) -> None:
    import faster_whisper  # noqa: F401
    import edge_tts  # noqa: F401


async def main() -> int:
    logging.basicConfig(level=logging.INFO, format="%(asctime)s %(levelname)s %(name)s: %(message)s")
    fh = RotatingFileHandler(Path(os.environ.get("LOCALAPPDATA", ".")) / "cornerstone-agency" / "voice-bridge.log",
                             maxBytes=2_000_000, backupCount=3, encoding="utf-8")
    fh.setFormatter(logging.Formatter("%(asctime)s %(levelname)s %(name)s: %(message)s"))
    logging.getLogger().addHandler(fh)
    cfg = Config()
    problems = cfg.validate()
    if problems:
        for p in problems:
            log.error(p)
        return 2
    _bootstrap_hermes_imports(cfg)
    backend = HermesBackend(cfg)
    await backend.start()
    asyncio.create_task(backend.warmup_stt())
    app = build_app(cfg, backend)
    runner = web.AppRunner(app, access_log=None)
    await runner.setup()
    site = web.TCPSite(runner, cfg.host, cfg.port)
    await site.start()
    for xh in cfg.extra_hosts:
        await web.TCPSite(runner, xh, cfg.port).start()
    log.info("hermes voice bridge listening on ws://%s:%d/v1/audio -> agent %s", cfg.host, cfg.port, cfg.agent_url)
    log.info("agent health: %s", await backend.agent_health())
    try:
        while True:
            await asyncio.sleep(3600)
    except (KeyboardInterrupt, asyncio.CancelledError):
        pass
    finally:
        await runner.cleanup()
        await backend.close()
    return 0


if __name__ == "__main__":
    try:
        raise SystemExit(asyncio.run(main()))
    except KeyboardInterrupt:
        pass

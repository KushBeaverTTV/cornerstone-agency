package com.cornerstone.agency.voice

import android.util.Base64
import android.util.Log
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

/**
 * WebSocket client for the Hermes voice bridge (ported from
 * hermes-android-assistant, pairing manager replaced with explicit params).
 *
 * Wire protocol:
 *   phone -> bridge   {"type":"auth","token":"...","caps":["control"]}
 *                     {"type":"audio","codec":"pcm16","data":"<b64>"}
 *                     {"type":"end_of_turn"}
 *   bridge -> phone   {"type":"status","text":"..."}
 *                     {"type":"audio","codec":"pcm16","data":"<b64>"}
 *                     {"type":"error","message":"..."}
 *                     {"type":"control","record":bool,"interrupt":bool}
 */
class VoiceClient(
    private val host: String,
    private val port: Int,
    private val token: String,
) {

    sealed interface Incoming {
        data class Status(val text: String) : Incoming
        data class Audio(val codec: String, val bytes: ByteArray) : Incoming
        data class Error(val message: String) : Incoming
    }

    data class Control(val record: Boolean? = null, val interrupt: Boolean = false)

    enum class Link { IDLE, CONNECTING, OPEN, CLOSED, FAILED }

    private val _incoming = MutableSharedFlow<Incoming>(extraBufferCapacity = 64)
    val incoming: SharedFlow<Incoming> = _incoming

    private val _control = MutableSharedFlow<Control>(extraBufferCapacity = 16)
    val control: SharedFlow<Control> = _control

    private val _link = MutableStateFlow(Link.IDLE)
    val link: StateFlow<Link> = _link

    /** True once the bridge acked auth with {"type":"status","text":"connected"}. */
    private val _authed = MutableStateFlow(false)
    val authed: StateFlow<Boolean> = _authed

    private val _lastError = MutableStateFlow<String?>(null)
    val lastError: StateFlow<String?> = _lastError

    private val _reconnectAttempt = MutableStateFlow(0)
    val reconnectAttempt: StateFlow<Int> = _reconnectAttempt

    private val client = OkHttpClient.Builder()
        .pingInterval(15, TimeUnit.SECONDS)
        .build()
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val json = Json { ignoreUnknownKeys = true }

    private var webSocket: WebSocket? = null

    @Volatile private var wantConnected = false
    private var reconnectJob: Job? = null

    fun connect() {
        wantConnected = true
        reconnectJob?.cancel()
        _reconnectAttempt.value = 0
        val stale = webSocket
        webSocket = null
        stale?.close(1000, "reconnecting")
        openSocket()
    }

    private fun openSocket() {
        if (host.isBlank()) {
            wantConnected = false
            _link.value = Link.FAILED
            _lastError.value = "Voice bridge host not configured"
            scope.launch { _incoming.emit(Incoming.Error("Voice bridge host not configured")) }
            return
        }
        val scheme = if (port == 443) "wss" else "ws"
        val request = Request.Builder()
            .url("$scheme://$host:$port/v1/audio")
            .header("Authorization", "Bearer $token")
            .build()
        _link.value = Link.CONNECTING
        _authed.value = false
        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                if (webSocket !== this@VoiceClient.webSocket) {
                    webSocket.close(1000, "stale socket")
                    return
                }
                _link.value = Link.OPEN
                _reconnectAttempt.value = 0
                _lastError.value = null
                webSocket.send(
                    json.encodeToString(
                        AuthMessage.serializer(),
                        AuthMessage(token = token, caps = listOf("control")),
                    ),
                )
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                if (webSocket !== this@VoiceClient.webSocket) return
                handleMessage(text)
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                if (webSocket !== this@VoiceClient.webSocket) return
                val detail =
                    response?.let { "HTTP ${it.code}" } ?: (t.message ?: t.javaClass.simpleName)
                onLinkLost(reason = "bridge unreachable: $detail", wasFailure = true)
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                if (webSocket !== this@VoiceClient.webSocket) return
                onLinkLost(reason = "socket closed ($code) ${reason.trim()}", wasFailure = false)
            }
        })
    }

    private fun onLinkLost(reason: String, wasFailure: Boolean) {
        _authed.value = false
        _lastError.value = reason
        if (wantConnected) {
            val attempt = _reconnectAttempt.value + 1
            if (attempt <= MAX_RECONNECT_ATTEMPTS) {
                _reconnectAttempt.value = attempt
                _link.value = Link.CONNECTING
                val delayMs = minOf(
                    RECONNECT_MAX_DELAY_MS,
                    RECONNECT_BASE_DELAY_MS shl (attempt - 1),
                )
                reconnectJob = scope.launch {
                    delay(delayMs)
                    if (wantConnected) openSocket()
                }
                return
            }
            wantConnected = false
            _reconnectAttempt.value = 0
            _link.value = Link.FAILED
            scope.launch { _incoming.emit(Incoming.Error(reason)) }
            return
        }
        if (wasFailure) {
            _link.value = Link.FAILED
            scope.launch { _incoming.emit(Incoming.Error(reason)) }
        } else {
            _link.value = Link.CLOSED
        }
    }

    fun sendAudio(pcm: ByteArray) {
        if (_link.value != Link.OPEN || !_authed.value) return
        val ws = webSocket ?: return
        val data = Base64.encodeToString(pcm, Base64.NO_WRAP)
        ws.send(
            json.encodeToString(
                AudioMessage.serializer(),
                AudioMessage(codec = "pcm16", data = data),
            ),
        )
    }

    fun sendEndOfTurn() {
        webSocket?.send(
            json.encodeToString(EndOfTurnMessage.serializer(), EndOfTurnMessage()),
        )
    }

    fun disconnect() {
        wantConnected = false
        reconnectJob?.cancel()
        reconnectJob = null
        _reconnectAttempt.value = 0
        _authed.value = false
        webSocket?.close(1000, "session closed")
        webSocket = null
        _link.value = Link.CLOSED
    }

    private fun handleMessage(text: String) {
        try {
            val obj = json.parseToJsonElement(text).jsonObject
            when (val type = obj["type"]?.jsonPrimitive?.content) {
                "status" -> {
                    val status = obj["text"]?.jsonPrimitive?.content ?: ""
                    if (status.trim().equals("connected", ignoreCase = true)) {
                        _authed.value = true
                    }
                    scope.launch { _incoming.emit(Incoming.Status(status)) }
                }
                "audio" -> {
                    val msg = Incoming.Audio(
                        codec = obj["codec"]?.jsonPrimitive?.content ?: "pcm16",
                        bytes = Base64.decode(
                            obj["data"]?.jsonPrimitive?.content ?: "",
                            Base64.DEFAULT,
                        ),
                    )
                    scope.launch { _incoming.emit(msg) }
                }
                "error" -> {
                    val message = obj["message"]?.jsonPrimitive?.content ?: "unknown error"
                    _lastError.value = message
                    scope.launch { _incoming.emit(Incoming.Error(message)) }
                }
                "control" -> {
                    val ctl = Control(
                        record = obj["record"]?.jsonPrimitive?.booleanOrNull,
                        interrupt = obj["interrupt"]?.jsonPrimitive?.booleanOrNull ?: false,
                    )
                    scope.launch { _control.emit(ctl) }
                }
                else -> Log.d(TAG, "ignoring unknown bridge message type=$type")
            }
        } catch (e: Exception) {
            Log.w(TAG, "bad bridge message", e)
            scope.launch { _incoming.emit(Incoming.Error("unparseable bridge message")) }
        }
    }

    @Serializable data class AuthMessage(
        val type: String = "auth",
        val token: String,
        val caps: List<String>,
    )
    @Serializable data class AudioMessage(
        val type: String = "audio",
        val codec: String,
        val data: String,
    )
    @Serializable class EndOfTurnMessage(val type: String = "end_of_turn")

    companion object {
        private const val TAG = "VoiceClient"
        private const val MAX_RECONNECT_ATTEMPTS = 5
        private const val RECONNECT_BASE_DELAY_MS = 1_000L
        private const val RECONNECT_MAX_DELAY_MS = 15_000L
    }
}

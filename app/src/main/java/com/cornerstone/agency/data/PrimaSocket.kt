package com.cornerstone.agency.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withTimeout
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import java.io.IOException
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.math.min

/**
 * WebSocket to the agency bridge — ws://host:9121/prima/ws?token=… .
 * One socket fans out every Prima session's events; frames arrive as raw
 * JsonObject ({type:"event"|"ui_request"|"worker"|"pong", session_id, …}).
 * Same reconnect policy as GatewaySocket: 1 s → 30 s capped backoff.
 */
class PrimaSocket(
    private val bridge: BridgeRepository,
    private val scope: CoroutineScope,
) {
    private val json = Json { ignoreUnknownKeys = true }

    private val _state = MutableStateFlow(GatewayState.CLOSED)
    val state: StateFlow<GatewayState> = _state.asStateFlow()

    private val _frames = MutableSharedFlow<JsonObject>(extraBufferCapacity = 512)
    val frames: SharedFlow<JsonObject> = _frames.asSharedFlow()

    private val wsClient by lazy {
        bridge.client.newBuilder()
            .readTimeout(0, TimeUnit.MILLISECONDS)
            .pingInterval(20, TimeUnit.SECONDS)
            .build()
    }

    @Volatile
    private var socket: WebSocket? = null

    @Volatile
    private var manualClose = true

    private var connectJob: Job? = null
    private var attempts = 0

    val isOpen: Boolean
        get() = _state.value == GatewayState.OPEN

    fun connect() {
        manualClose = false
        if (_state.value == GatewayState.OPEN || connectJob?.isActive == true) return
        connectJob = scope.launch { openOnce() }
    }

    fun disconnect() {
        manualClose = true
        connectJob?.cancel()
        connectJob = null
        val s = socket
        socket = null
        s?.close(1000, null)
        _state.value = GatewayState.CLOSED
    }

    /** Write a frame to the bridge (ui_response, ping). Returns false if down. */
    fun send(frame: JsonObject): Boolean {
        val s = socket ?: return false
        return s.send(frame.toString())
    }

    private suspend fun openOnce() {
        if (!bridge.configured) {
            _state.value = GatewayState.CLOSED
            return
        }
        _state.value =
            if (attempts > 0) GatewayState.RECONNECTING else GatewayState.CONNECTING
        val request = Request.Builder().url(bridge.wsUrl()).build()
        try {
            withTimeout(15_000) {
                suspendCancellableCoroutine { cont ->
                    val listener = object : WebSocketListener() {
                        override fun onOpen(webSocket: WebSocket, response: Response) {
                            attempts = 0
                            _state.value = GatewayState.OPEN
                            if (cont.isActive) cont.resume(Unit)
                        }

                        override fun onMessage(webSocket: WebSocket, text: String) {
                            runCatching { json.parseToJsonElement(text).jsonObject }
                                .getOrNull()
                                ?.let { _frames.tryEmit(it) }
                        }

                        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                            webSocket.close(1000, null)
                        }

                        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                            socketDropped(webSocket)
                        }

                        override fun onFailure(
                            webSocket: WebSocket,
                            t: Throwable,
                            response: Response?,
                        ) {
                            socketDropped(webSocket)
                            if (cont.isActive) {
                                cont.resumeWithException(
                                    IOException("bridge socket connect failed: ${t.message}", t)
                                )
                            }
                        }
                    }
                    val s = wsClient.newWebSocket(request, listener)
                    socket = s
                    cont.invokeOnCancellation { s.cancel() }
                }
            }
        } catch (_: Exception) {
            socket?.cancel()
        }
    }

    private fun socketDropped(dropped: WebSocket?) {
        if (dropped != null && dropped !== socket) return
        socket = null
        if (manualClose) {
            _state.value = GatewayState.CLOSED
            return
        }
        attempts++
        _state.value = GatewayState.RECONNECTING
        val backoff = min(1_000L shl (attempts - 1).coerceAtMost(5), 30_000L)
        connectJob = scope.launch {
            delay(backoff)
            openOnce()
        }
    }
}

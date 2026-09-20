package com.cornerstone.agency.data

import kotlinx.coroutines.CompletableDeferred
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
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.longOrNull
import kotlinx.serialization.json.put
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import java.io.IOException
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicLong
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.math.min

enum class GatewayState { CONNECTING, OPEN, RECONNECTING, CLOSED }

/** Server-pushed frame normalized to {type, session_id, payload}. */
data class GatewayEvent(
    val type: String,
    val sessionId: String?,
    val payload: JsonObject?,
    val seq: Long? = null,
)

/**
 * Server→client JSON-RPC request ({id:"srq-…", method, params}) — the agent
 * thread blocks until we answer this exact frame id. approval / clarify /
 * secret / sudo / vault.* / mcp.setup all arrive in this shape.
 */
data class GatewayRequest(
    /** Raw frame id — echoed verbatim in the response (usually "srq-…"). */
    val id: JsonElement,
    val method: String,
    val params: JsonObject,
) {
    val idString: String
        get() = (id as? kotlinx.serialization.json.JsonPrimitive)?.content ?: id.toString()
}

class GatewayRpcException(val code: Int, message: String) : Exception(message)

/**
 * JSON-RPC 2.0 over the dashboard WebSocket (ws://host/api/ws?ticket=…).
 * Frames: out {jsonrpc,id,method,params}; in {id,result|error} or push
 * {method:"event",params:{type,session_id,payload}} / bare {method,params}.
 * Reconnects with 1s→30s capped exponential backoff, minting a fresh ticket
 * each attempt; disconnect() is manual and stops the loop.
 */
class GatewaySocket(
    private val repo: ConnectionRepository,
    private val scope: CoroutineScope,
) {
    private val json = Json { ignoreUnknownKeys = true }

    private val _state = MutableStateFlow(GatewayState.CLOSED)
    val state: StateFlow<GatewayState> = _state.asStateFlow()

    private val _events = MutableSharedFlow<GatewayEvent>(extraBufferCapacity = 512)
    val events: SharedFlow<GatewayEvent> = _events.asSharedFlow()

    private val _requests = MutableSharedFlow<GatewayRequest>(extraBufferCapacity = 64)
    val requests: SharedFlow<GatewayRequest> = _requests.asSharedFlow()

    // WebSocket needs no read timeout (idle is normal) and keepalive pings.
    private val wsClient by lazy {
        repo.client.newBuilder()
            .readTimeout(0, TimeUnit.MILLISECONDS)
            .pingInterval(20, TimeUnit.SECONDS)
            .build()
    }

    private val nextId = AtomicLong(1)
    private val pending = ConcurrentHashMap<Long, CompletableDeferred<JsonElement>>()

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
        failPending(IOException("socket disconnected"))
        _state.value = GatewayState.CLOSED
    }

    /** Answer a server→client request frame, echoing its raw id. */
    fun respond(requestId: JsonElement, result: JsonObject): Boolean {
        val s = socket ?: return false
        val frame = buildJsonObject {
            put("jsonrpc", "2.0")
            put("id", requestId)
            put("result", result)
        }
        return s.send(frame.toString())
    }

    suspend fun call(method: String, params: JsonObject = JsonObject(emptyMap())): JsonElement {
        val s = socket
        if (_state.value != GatewayState.OPEN || s == null) {
            throw IOException("gateway socket not open")
        }
        val id = nextId.getAndIncrement()
        val deferred = CompletableDeferred<JsonElement>()
        pending[id] = deferred
        val frame = buildJsonObject {
            put("jsonrpc", "2.0")
            put("id", id)
            put("method", method)
            put("params", params)
        }
        if (!s.send(frame.toString())) {
            pending.remove(id)
            throw IOException("websocket send failed")
        }
        try {
            return withTimeout(120_000) { deferred.await() }
        } finally {
            pending.remove(id)
        }
    }

    private suspend fun openOnce() {
        _state.value =
            if (attempts > 0) GatewayState.RECONNECTING else GatewayState.CONNECTING
        val request = try {
            Request.Builder().url(repo.wsUrl(repo.wsTicket())).build()
        } catch (e: Exception) {
            socketDropped(null)
            return
        }
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
                            dispatch(text)
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
                                    IOException("websocket connect failed: ${t.message}", t)
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
            // Timeout or handshake failure — the listener's onClosed/onFailure (or the
            // cancellation below) drives the reconnect path.
            socket?.cancel()
        }
    }

    private fun socketDropped(dropped: WebSocket?) {
        // Stale callbacks from an older socket must not disturb the current one.
        if (dropped != null && dropped !== socket) return
        socket = null
        failPending(IOException("socket dropped"))
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

    private fun failPending(cause: Throwable) {
        val all = pending.values.toList()
        pending.clear()
        all.forEach { it.completeExceptionally(cause) }
    }

    private fun dispatch(text: String) {
        val obj = runCatching { json.parseToJsonElement(text).jsonObject }.getOrNull() ?: return

        // Server→client request: carries BOTH id and method (our call
        // responses never have a method). Answered via respond(id, result).
        val methodField = obj["method"]?.jsonPrimitive?.contentOrNull
        val rawId = obj["id"]
        if (methodField != null && rawId != null) {
            _requests.tryEmit(
                GatewayRequest(
                    id = rawId,
                    method = methodField,
                    params = obj["params"] as? JsonObject ?: JsonObject(emptyMap()),
                )
            )
            return
        }

        // JSON-RPC response: {id, result} | {id, error:{code,message}}
        val id = obj["id"]?.jsonPrimitive?.longOrNull
        if (id != null && (obj.containsKey("result") || obj.containsKey("error"))) {
            val deferred = pending.remove(id) ?: return
            val err = obj["error"] as? JsonObject
            if (err != null) {
                deferred.completeExceptionally(
                    GatewayRpcException(
                        code = err["code"]?.jsonPrimitive?.intOrNull ?: 0,
                        message = err["message"]?.jsonPrimitive?.contentOrNull ?: "rpc error",
                    )
                )
            } else {
                deferred.complete(obj["result"] ?: JsonNull)
            }
            return
        }

        // Server push — envelope {method:"event", params:{type,session_id,payload,seq}}
        // or bare {method:"<type>", session_id, params:{...}}.
        val method = obj["method"]?.jsonPrimitive?.contentOrNull ?: return
        val event = if (method == "event") {
            val p = obj["params"] as? JsonObject ?: return
            GatewayEvent(
                type = p["type"]?.jsonPrimitive?.contentOrNull ?: return,
                sessionId = p["session_id"]?.jsonPrimitive?.contentOrNull,
                payload = p["payload"] as? JsonObject,
                seq = p["seq"]?.jsonPrimitive?.longOrNull,
            )
        } else {
            GatewayEvent(
                type = method,
                sessionId = obj["session_id"]?.jsonPrimitive?.contentOrNull,
                payload = obj["params"] as? JsonObject,
            )
        }
        _events.tryEmit(event)
    }
}

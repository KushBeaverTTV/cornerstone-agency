package com.cornerstone.agency.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.longOrNull
import kotlinx.serialization.json.put
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

/**
 * ChatSource over a bridge Prima session (docs/BRIDGE.md). One
 * prime-agent worker per session; REST for commands, /prima/ws for events.
 * Produces the same ChatItem stream as ChatRepository so ChatScreen is shared.
 */
class PrimaChatRepository(
    private val bridge: BridgeRepository,
    private val socket: PrimaSocket,
) : ChatSource {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private val localId = AtomicLong(0)

    override val linkState: StateFlow<GatewayState> = socket.state

    private val _items = MutableStateFlow<List<ChatItem>>(emptyList())
    override val items: StateFlow<List<ChatItem>> = _items.asStateFlow()

    private val _title = MutableStateFlow("")
    override val title: StateFlow<String> = _title.asStateFlow()

    private val _opening = MutableStateFlow(true)
    override val opening: StateFlow<Boolean> = _opening.asStateFlow()

    private val _streaming = MutableStateFlow(false)
    override val streaming: StateFlow<Boolean> = _streaming.asStateFlow()

    private val _statusLine = MutableStateFlow("")
    override val statusLine: StateFlow<String> = _statusLine.asStateFlow()

    private val _lastError = MutableStateFlow("")
    override val lastError: StateFlow<String> = _lastError.asStateFlow()

    /** Bridge session id (the worker's id) once resolved. */
    var sessionId: String? = null
        private set

    private var collectJob: Job? = null
    private var assistantId: String? = null
    private var thinkingId: String? = null
    private val openTools = HashMap<String, String>() // toolCallId -> ChatItem.id
    private val reqIndex = ConcurrentHashMap<String, String>() // ui request id -> ChatItem.id
    private val reqTimers = HashMap<String, Job>()

    // -- lifecycle --------------------------------------------------------

    override suspend fun open(requestedStoredId: String?) {
        socket.connect()
        subscribe()
        try {
            if (requestedStoredId != null) {
                sessionId = requestedStoredId
            } else {
                sessionId = bridge.createPrimaSession()
            }
            hydrate()
        } catch (e: Exception) {
            _opening.value = false
            _lastError.value = friendly(e)
        }
    }

    override suspend fun refreshAfterReconnect() {
        if (sessionId != null) runCatching { hydrate() }
    }

    private fun subscribe() {
        if (collectJob?.isActive == true) return
        collectJob = scope.launch {
            socket.frames.collect { frame ->
                val sid = frame["session_id"]?.jsonPrimitive?.contentOrNull
                if (sid != sessionId) return@collect
                when (frame["type"]?.jsonPrimitive?.contentOrNull) {
                    "event" -> (frame["event"] as? JsonObject)?.let { onEvent(it) }
                    "ui_request" -> (frame["request"] as? JsonObject)?.let { onUiRequest(it) }
                    "worker" -> onWorker(frame)
                }
            }
        }
    }

    override fun close() {
        collectJob?.cancel()
        collectJob = null
        reqTimers.values.forEach { it.cancel() }
        reqTimers.clear()
        // Workers intentionally survive — bridge owns their lifecycle.
    }

    // -- commands ---------------------------------------------------------

    override suspend fun send(text: String) {
        val t = text.trim()
        if (t.isEmpty()) return
        _lastError.value = ""
        val sid = sessionId
        if (sid == null) {
            append(ChatKind.Error, "No Prima session — bridge unreachable?")
            return
        }
        append(ChatKind.User, t)
        try {
            bridge.primaPrompt(sid, t, if (_streaming.value) "followUp" else null)
        } catch (e: Exception) {
            append(ChatKind.Error, "Send failed: ${friendly(e)}")
        }
    }

    override suspend fun stop() {
        sessionId?.let { runCatching { bridge.primaAbort(it) } }
    }

    override suspend fun newSession() {
        try {
            sessionId = bridge.createPrimaSession()
            _items.value = emptyList()
            assistantId = null
            thinkingId = null
            openTools.clear()
            _title.value = ""
            hydrate()
        } catch (e: Exception) {
            append(ChatKind.Error, "New session failed: ${friendly(e)}")
        }
    }

    override suspend fun rename(newTitle: String) {
        val sid = sessionId ?: return
        runCatching {
            bridge.primaRename(sid, newTitle)
            _title.value = newTitle
        }
    }

    // -- request cards (extension_ui_request → ui_response) ----------------

    private fun patchRequest(itemId: String, f: (PendingRequest) -> PendingRequest) {
        mutate(itemId) { item ->
            item.request?.let { item.copy(request = f(it)) } ?: item
        }
    }

    override suspend fun approveRequest(itemId: String, choice: String) {
        val req = _items.value.find { it.id == itemId }?.request ?: return
        patchRequest(itemId) { it.copy(state = RequestState.Sending, respondError = "") }
        val confirmed = choice != "deny"
        val ok = respondOk(req.requestId, buildJsonObject { put("confirmed", confirmed) })
        patchRequest(itemId) {
            if (ok) it.copy(state = RequestState.Resolved, resolution = if (confirmed) "Confirmed" else "Denied")
            else it.copy(state = RequestState.Open, respondError = "bridge socket down")
        }
        reqTimers[req.requestId]?.cancel()
    }

    override suspend fun answerClarify(itemId: String, answers: Map<String, String>) {
        val req = _items.value.find { it.id == itemId }?.request ?: return
        patchRequest(itemId) { it.copy(state = RequestState.Sending, respondError = "") }
        val value = answers.values.firstOrNull() ?: ""
        val ok = respondOk(req.requestId, buildJsonObject { put("value", value) })
        patchRequest(itemId) {
            if (ok) it.copy(state = RequestState.Resolved, resolution = "Answered: $value")
            else it.copy(state = RequestState.Open, respondError = "bridge socket down")
        }
        reqTimers[req.requestId]?.cancel()
    }

    override suspend fun submitRequestValue(itemId: String, value: String) {
        val req = _items.value.find { it.id == itemId }?.request ?: return
        patchRequest(itemId) { it.copy(state = RequestState.Sending, respondError = "") }
        val ok = respondOk(req.requestId, buildJsonObject { put("value", value) })
        patchRequest(itemId) {
            if (ok) it.copy(state = RequestState.Resolved, resolution = "Answered")
            else it.copy(state = RequestState.Open, respondError = "bridge socket down")
        }
        reqTimers[req.requestId]?.cancel()
    }

    override suspend fun respondMcp(itemId: String, approved: Boolean) {
        val req = _items.value.find { it.id == itemId }?.request ?: return
        val ok = respondOk(req.requestId, buildJsonObject { put("confirmed", approved) })
        patchRequest(itemId) {
            if (ok) it.copy(state = RequestState.Resolved, resolution = if (approved) "Allowed" else "Denied")
            else it.copy(respondError = "bridge socket down")
        }
        reqTimers[req.requestId]?.cancel()
    }

    private fun respondOk(requestId: String?, fields: JsonObject): Boolean {
        val sid = sessionId ?: return false
        if (requestId == null) return false
        return socket.send(buildJsonObject {
            put("type", "ui_response")
            put("session_id", sid)
            put("response", buildJsonObject {
                put("id", requestId)
                fields.forEach { (k, v) -> put(k, v) }
            })
        })
    }

    // -- events -------------------------------------------------------------

    private fun onEvent(ev: JsonObject) {
        when (ev.str("type")) {
            "agent_start", "turn_start" -> {
                _streaming.value = true
                _statusLine.value = "working…"
            }
            "message_start" -> {
                val msg = ev["message"] as? JsonObject
                when (msg?.str("role")) {
                    "assistant" -> startAssistant()
                    // user echo and internal "custom" harness digests stay hidden.
                }
            }
            "message_update" -> {
                val ame = ev["assistantMessageEvent"] as? JsonObject ?: return
                when (ame.str("type")) {
                    "text_delta" -> appendAssistantDelta(ame.str("delta") ?: "")
                    "thinking_delta" -> appendThinking(ame.str("delta") ?: "")
                }
            }
            "tool_execution_start" -> {
                val callId = ev.str("toolCallId") ?: nextId()
                val name = ev.str("toolName") ?: "tool"
                val id = nextId()
                openTools[callId] = id
                insertAboveAssistant(
                    ChatItem(id = id, kind = ChatKind.Tool, text = "▶ $name", done = false)
                )
            }
            "tool_execution_end" -> {
                val callId = ev.str("toolCallId")
                val name = ev.str("toolName") ?: "tool"
                val isError = ev["isError"]?.jsonPrimitive?.contentOrNull == "true" ||
                    ev["isError"]?.toString() == "true"
                val itemId = callId?.let { openTools.remove(it) }
                if (itemId != null) {
                    mutate(itemId) {
                        it.copy(text = "${if (isError) "✗" else "✓"} $name", done = true)
                    }
                } else {
                    insertAboveAssistant(
                        ChatItem(
                            id = nextId(), kind = ChatKind.Tool,
                            text = "${if (isError) "✗" else "✓"} $name", done = true,
                        )
                    )
                }
            }
            "message_end" -> {
                val msg = ev["message"] as? JsonObject ?: return
                if (msg.str("role") == "assistant") {
                    val text = assistantText(msg)
                    if (text.isNotEmpty()) setAssistantText(text)
                }
            }
            "turn_end" -> finalizeStreaming()
            "agent_end" -> {
                finalizeStreaming()
                _streaming.value = false
                _statusLine.value = ""
            }
            "auto_retry_start" -> _statusLine.value = "retrying…"
            "extension_error" -> append(
                ChatKind.Error,
                ev["error"]?.jsonPrimitive?.contentOrNull ?: "extension error",
            )
        }
    }

    private fun onUiRequest(req: JsonObject) {
        val requestId = req.str("id") ?: return
        val method = req.str("method") ?: return
        val timeoutMs = req["timeout"]?.jsonPrimitive?.longOrNull
        val expiresAt = timeoutMs?.let { System.currentTimeMillis() + it }
            ?: (System.currentTimeMillis() + PendingRequest.REQUEST_TTL_MS)
        when (method) {
            "confirm" -> {
                val pr = PendingRequest(
                    kind = RequestKind.Approval,
                    requestId = requestId,
                    sessionId = sessionId,
                    expiresAt = expiresAt,
                    description = listOfNotNull(req.str("title"), req.str("message"))
                        .filter { it.isNotBlank() }.joinToString(" — "),
                    toolName = "prima",
                    choices = listOf("once", "deny"),
                    allowPermanent = false,
                    allowSession = false,
                )
                pushRequest(requestId, pr)
            }
            "select" -> {
                val options = (req["options"] as? JsonArray)
                    ?.mapNotNull { it.jsonPrimitive.contentOrNull } ?: emptyList()
                val pr = PendingRequest(
                    kind = RequestKind.Clarify,
                    requestId = requestId,
                    sessionId = sessionId,
                    expiresAt = expiresAt,
                    questions = listOf(
                        ClarifyQuestion(
                            qid = "q0",
                            question = req.str("title") ?: "Pick one",
                            choices = options,
                        )
                    ),
                )
                pushRequest(requestId, pr)
            }
            "input", "editor" -> {
                val pr = PendingRequest(
                    kind = RequestKind.Value,
                    requestId = requestId,
                    sessionId = sessionId,
                    expiresAt = expiresAt,
                    prompt = req.str("title") ?: "",
                    description = req.str("placeholder") ?: "",
                )
                pushRequest(requestId, pr)
            }
            // Fire-and-forget methods — display, don't answer.
            "notify" -> append(
                ChatKind.Status,
                listOfNotNull(req.str("title"), req.str("message"))
                    .filter { it.isNotBlank() }.joinToString(" — ").ifBlank { "notify" },
            )
            "setStatus" -> _statusLine.value = req.str("statusText") ?: req.str("text") ?: ""
            "setTitle" -> _title.value = req.str("title") ?: _title.value
            else -> {} // setWidget / set_editor_text — no surface in M6.
        }
    }

    private fun pushRequest(requestId: String, pr: PendingRequest) {
        if (reqIndex.containsKey(requestId)) return
        val id = nextId()
        reqIndex[requestId] = id
        append(ChatItem(id = id, kind = ChatKind.Request, text = "", request = pr))
        reqTimers[requestId] = scope.launch {
            delay(pr.expiresAt - System.currentTimeMillis())
            mutate(id) { item ->
                item.request?.takeIf { it.open }?.let {
                    item.copy(request = it.copy(state = RequestState.Expired))
                } ?: item
            }
        }
    }

    private fun onWorker(frame: JsonObject) {
        if (frame.str("state") == "exited") {
            _streaming.value = false
            _statusLine.value = ""
            append(ChatKind.Error, "Prima worker exited (code ${frame["code"]})")
        }
    }

    // -- hydration ----------------------------------------------------------

    private suspend fun hydrate() {
        val sid = sessionId ?: return
        val messages = bridge.primaMessages(sid)
        val list = mutableListOf<ChatItem>()
        for (m in messages) {
            val msg = m as? JsonObject ?: continue
            when (msg.str("role")) {
                "user" -> {
                    val text = userText(msg)
                    if (text.isNotBlank()) {
                        list += ChatItem(id = nextId(), kind = ChatKind.User, text = text)
                    }
                }
                "assistant" -> {
                    val content = msg["content"] as? JsonArray ?: continue
                    var text = ""
                    for (part in content) {
                        val p = part as? JsonObject ?: continue
                        when (p.str("type")) {
                            "thinking" -> p.str("thinking")?.takeIf { it.isNotBlank() }?.let {
                                list += ChatItem(id = nextId(), kind = ChatKind.Thinking, text = it)
                            }
                            "text" -> text += p.str("text") ?: ""
                            "toolCall" -> list += ChatItem(
                                id = nextId(), kind = ChatKind.Tool,
                                text = "✓ ${p.str("name") ?: "tool"}", done = true,
                            )
                        }
                    }
                    if (text.isNotBlank()) {
                        list += ChatItem(id = nextId(), kind = ChatKind.Assistant, text = text)
                    }
                }
                "toolResult" -> {
                    val isErr = msg["isError"]?.toString() == "true"
                    list += ChatItem(
                        id = nextId(), kind = ChatKind.Tool,
                        text = "${if (isErr) "✗" else "✓"} ${msg.str("toolName") ?: "tool"}",
                        done = true,
                    )
                }
                "bashExecution" -> {
                    list += ChatItem(
                        id = nextId(), kind = ChatKind.Tool,
                        text = "✓ \$ ${msg.str("command") ?: ""}".trim(),
                        done = true,
                    )
                }
            }
        }
        _items.value = list
        _opening.value = false
        fetchTitle(sid)
    }

    private suspend fun fetchTitle(sid: String) {
        runCatching {
            bridge.primaSessions().find { it.id == sid }?.let {
                _title.value = it.label
            }
        }
    }

    // -- item plumbing (mirrors ChatRepository) ------------------------------

    private fun nextId() = "p${localId.incrementAndGet()}"

    private fun append(kind: ChatKind, text: String) {
        append(ChatItem(id = nextId(), kind = kind, text = text))
    }

    private fun append(item: ChatItem) {
        _items.value = _items.value + item
    }

    private fun insertAboveAssistant(item: ChatItem) {
        val cur = _items.value.toMutableList()
        val idx = cur.indexOfLast { it.id == assistantId }
        if (idx >= 0) cur.add(idx, item) else cur.add(item)
        _items.value = cur
    }

    private fun mutate(id: String, f: (ChatItem) -> ChatItem) {
        _items.value = _items.value.map { if (it.id == id) f(it) else it }
    }

    private fun startAssistant() {
        if (assistantId != null) return
        val id = nextId()
        assistantId = id
        append(ChatItem(id = id, kind = ChatKind.Assistant, text = "", streaming = true))
    }

    private fun appendAssistantDelta(delta: String) {
        val id = assistantId ?: run { startAssistant(); assistantId!! }
        mutate(id) { it.copy(text = it.text + delta, streaming = true) }
    }

    private fun setAssistantText(text: String) {
        val id = assistantId
        if (id == null) {
            append(ChatItem(id = nextId(), kind = ChatKind.Assistant, text = text))
        } else {
            mutate(id) { it.copy(text = text, streaming = false) }
        }
    }

    private fun appendThinking(delta: String) {
        val id = thinkingId
        if (id == null) {
            val newId = nextId()
            thinkingId = newId
            insertAboveAssistant(ChatItem(id = newId, kind = ChatKind.Thinking, text = delta))
        } else {
            mutate(id) { it.copy(text = it.text + delta) }
        }
    }

    private fun finalizeStreaming() {
        assistantId?.let { id -> mutate(id) { it.copy(streaming = false) } }
        assistantId = null
        thinkingId = null
        openTools.entries.toList().forEach { (call, id) ->
            mutate(id) { it.copy(done = true) }
            openTools.remove(call)
        }
    }

    private fun friendly(e: Exception): String = when (e) {
        is ApiException -> e.message ?: "HTTP error"
        is java.io.IOException -> "bridge unreachable"
        else -> e.message ?: "error"
    }
}

private fun JsonObject.str(key: String): String? =
    this[key]?.jsonPrimitive?.contentOrNull

private fun userText(msg: JsonObject): String {
    return when (val c = msg["content"]) {
        is kotlinx.serialization.json.JsonPrimitive -> c.contentOrNull ?: ""
        is JsonArray -> c.mapNotNull {
            (it as? JsonObject)?.str("text")
        }.joinToString("\n")
        else -> ""
    }
}

private fun assistantText(msg: JsonObject): String {
    val content = msg["content"] as? JsonArray ?: return ""
    return content.mapNotNull {
        val p = it as? JsonObject ?: return@mapNotNull null
        if (p.str("type") == "text") p.str("text") else null
    }.joinToString("")
}

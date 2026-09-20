package com.cornerstone.agency.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.put
import java.util.concurrent.atomic.AtomicLong

private val JSON = Json { ignoreUnknownKeys = true }

private val REQUEST_EVENTS = setOf(
    "approval.request", "clarify.request", "secret.request",
    "sudo.request", "mcp.setup.request",
)

/** *.resolved/.expired/.answered/.cancelled/.timeout events close the card they name. */
private val RESOLVE_SUFFIX = Regex("\\.(resolved|expired|answered|cancelled|canceled|timeout)$")

/**
 * One bot's chat: resolves a live session for [profile], hydrates the transcript
 * via session.resume (stored id → runtime snapshot with messages[]), then renders
 * server-pushed events as ChatItems.
 *
 * ID discipline (tui_gateway contract): session.list/delete take STORED ids;
 * session.resume takes a stored id and returns a runtime `session_id`; prompt,
 * interrupt and event session_ids are the runtime id.
 */
class ChatRepository(
    private val gateway: GatewaySocket,
    val profile: String,
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private val localId = AtomicLong(0)

    private val _items = MutableStateFlow<List<ChatItem>>(emptyList())
    val items: StateFlow<List<ChatItem>> = _items.asStateFlow()

    private val _title = MutableStateFlow("")
    val title: StateFlow<String> = _title.asStateFlow()

    private val _opening = MutableStateFlow(true)
    val opening: StateFlow<Boolean> = _opening.asStateFlow()

    private val _streaming = MutableStateFlow(false)
    val streaming: StateFlow<Boolean> = _streaming.asStateFlow()

    /** Transient turn status line ("(◇) orchestrating…") — NOT a transcript row. */
    private val _statusLine = MutableStateFlow("")
    val statusLine: StateFlow<String> = _statusLine.asStateFlow()

    private val _lastError = MutableStateFlow("")
    val lastError: StateFlow<String> = _lastError.asStateFlow()

    var storedSessionId: String? = null
        private set
    var runtimeSessionId: String? = null
        private set

    // Turn-scoped render state: one streaming assistant bubble (its list index is
    // the insertion point for thinking/tool rows so they always sit ABOVE the
    // answer), one coalesced thinking bubble, one open tool line.
    private var assistantId: String? = null
    private var turnAssistantIndex: Int? = null
    private var thinkingId: String? = null
    private var openToolId: String? = null

    // Interactive requests: item id -> expiry job, plus dedupe indexes on both
    // the wire frame id (srq-…) and the payload request_id.
    private val reqTimers = HashMap<String, kotlinx.coroutines.Job>()
    private val reqIndex = HashMap<String, String>()

    // Every runtime id this repo has owned (re-resume mints new ones) — all are
    // "this chat is open" for notification suppression until close().
    private val registeredSids = HashSet<String>()

    init {
        scope.launch { gateway.events.collect(::onEvent) }
        scope.launch { gateway.requests.collect(::onRequestFrame) }
        // Resolutions that happened outside this transcript (notification
        // action, TTL) collapse the matching card — the server stays silent.
        scope.launch {
            com.cornerstone.agency.service.NotificationCenter.resolvedKeys
                .collect { key -> resolveRequest(key, "handled") }
        }
    }

    // ---- session resolution ---------------------------------------------

    /**
     * Herald's order: explicit stored id → resume; else session.list{profile}
     * most-recent → resume; else session.create{profile}.
     */
    suspend fun open(requestedStoredId: String? = null) {
        _opening.value = true
        try {
            when {
                requestedStoredId != null -> resume(requestedStoredId)
                else -> {
                    val sessions = listSessions()
                    val recent = sessions.firstOrNull()
                    if (recent != null) {
                        _title.value = recent.title
                        resume(recent.id)
                    } else {
                        create()
                    }
                }
            }
            _lastError.value = ""
        } catch (e: Exception) {
            _lastError.value = friendly(e)
        } finally {
            _opening.value = false
        }
    }

    /** Re-resolve after a socket reconnect: resume the stored session for a fresh runtime id. */
    suspend fun refreshAfterReconnect() {
        val stored = storedSessionId ?: return
        try {
            resume(stored)
        } catch (_: Exception) {
        }
    }

    private suspend fun resume(storedId: String) {
        val res = gateway.call(
            "session.resume",
            buildJsonObject {
                put("session_id", storedId)
                put("profile", profile)
            },
        ).asObj()
        runtimeSessionId = res.str("session_id") ?: runtimeSessionId
        storedSessionId = res.str("stored_session_id")
            ?: res.str("session_key") ?: res.str("resumed") ?: storedId
        GatewayHub.registerSession(runtimeSessionId, storedSessionId, profile)
        runtimeSessionId?.let {
            registeredSids += it
            GatewayHub.openChats += it
        }
        hydrate(res)
        seedPendingFromResume(res)
        fetchTitle()
        seedPendingApprovals()
    }

    /** session.resume can carry still-open requests (open_requests entries look
     * like serialized request frames {id, method, params}; pending_* fields are
     * per-kind payloads). Seeded cards dedupe against live replays by request_id. */
    private fun seedPendingFromResume(res: JsonObject) {
        (res["open_requests"] as? JsonArray)?.forEach { el ->
            val r = el as? JsonObject ?: return@forEach
            val method = r.str("method") ?: r.str("type")?.removeSuffix(".request")
                ?: return@forEach
            val params = (r["params"] as? JsonObject)
                ?: (r["payload"] as? JsonObject) ?: r
            val srq = r["id"]?.takeIf {
                it.jsonPrimitive.contentOrNull?.startsWith("srq-") == true
            }
            pushRequest(method, params, srqId = srq)
        }
        mapOf(
            "pending_approval" to "approval", "pending_clarify" to "clarify",
            "pending_secret" to "secret", "pending_sudo" to "sudo",
            "pending_mcp_setup" to "mcp.setup",
        ).forEach { (key, method) ->
            (res[key] as? JsonObject)?.let { pushRequest(method, it, srqId = null) }
        }
    }

    private suspend fun create() {
        val res = gateway.call(
            "session.create",
            buildJsonObject { put("profile", profile) },
        ).asObj()
        runtimeSessionId = res.str("session_id")
        storedSessionId = res.str("stored_session_id") ?: storedSessionId
        GatewayHub.registerSession(runtimeSessionId, storedSessionId, profile)
        runtimeSessionId?.let {
            registeredSids += it
            GatewayHub.openChats += it
        }
        hydrate(res)
        seedPendingFromResume(res)
        seedPendingApprovals()
    }

    /** approval.pending catches requests still open after a reconnect — the
     * resume snapshot's open_requests is empty when the old transport died. */
    private suspend fun seedPendingApprovals() {
        val rt = runtimeSessionId ?: return
        try {
            val res = gateway.call(
                "approval.pending",
                buildJsonObject { put("session_id", rt) },
            ).asObj()
            val list = (res["approvals"] as? JsonArray)
                ?: (res["pending"] as? JsonArray)
                ?: JsonArray(emptyList())
            list.forEach { el ->
                (el as? JsonObject)?.let { pushRequest("approval", it, srqId = null) }
            }
        } catch (_: Exception) {
            // Older servers may not implement approval.pending.
        }
    }

    suspend fun listSessions(): List<SessionSummary> =
        listProfileSessions(gateway, profile)

    // ---- actions ----------------------------------------------------------

    suspend fun send(text: String) {
        val t = text.trim()
        if (t.isEmpty()) return
        if (runtimeSessionId == null) {
            try {
                create()
            } catch (e: Exception) {
                append(ChatKind.Error, "Could not start a session: ${friendly(e)}")
                return
            }
        }
        append(ChatKind.User, t)
        _streaming.value = true
        _statusLine.value = ""
        try {
            gateway.call(
                "prompt.submit",
                buildJsonObject {
                    put("session_id", runtimeSessionId)
                    put("text", t)
                },
            )
        } catch (e: Exception) {
            _streaming.value = false
            append(ChatKind.Error, "Send failed: ${friendly(e)}")
        }
    }

    suspend fun stop() {
        val rt = runtimeSessionId ?: return
        try {
            gateway.call("session.interrupt", buildJsonObject { put("session_id", rt) })
        } catch (_: Exception) {
        }
        finalizeStreaming()
    }

    suspend fun newSession() {
        _opening.value = true
        try {
            create()
            _title.value = ""
            _lastError.value = ""
        } catch (e: Exception) {
            _lastError.value = friendly(e)
        } finally {
            _opening.value = false
        }
    }

    suspend fun deleteSession(storedId: String) {
        gateway.call("session.delete", buildJsonObject { put("session_id", storedId) })
    }

    suspend fun rename(newTitle: String) {
        val id = runtimeSessionId ?: storedSessionId ?: return
        val t = newTitle.trim()
        if (t.isEmpty()) return
        gateway.call(
            "session.title",
            buildJsonObject {
                put("session_id", id)
                put("title", t)
            },
        )
        _title.value = t
    }

    private suspend fun fetchTitle() {
        val id = runtimeSessionId ?: storedSessionId ?: return
        try {
            val res = gateway.call(
                "session.title",
                buildJsonObject { put("session_id", id) },
            ).asObj()
            res.str("title")?.takeIf { it.isNotBlank() }?.let {
                _title.value = it
                GatewayHub.sessions[runtimeSessionId]?.title = it
            }
        } catch (_: Exception) {
        }
    }

    /** Leaving the chat: best-effort interrupt of a live turn, then tear down. */
    @OptIn(DelicateCoroutinesApi::class)
    fun close() {
        reqTimers.values.forEach { it.cancel() }
        reqTimers.clear()
        reqIndex.clear()
        GatewayHub.openChats -= registeredSids
        registeredSids.clear()
        PendingRequests.clear(storedSessionId ?: runtimeSessionId ?: "", profile)
        if (_streaming.value) {
            val rt = runtimeSessionId
            if (rt != null) {
                // Outlives this repo's scope so the interrupt still reaches the wire.
                GlobalScope.launch(Dispatchers.IO) {
                    runCatching {
                        gateway.call(
                            "session.interrupt",
                            buildJsonObject { put("session_id", rt) },
                        )
                    }
                }
            }
        }
        scope.cancel()
    }

    // ---- transcript hydration ----------------------------------------------

    private fun hydrate(res: JsonObject) {
        val msgs = res["messages"] as? JsonArray
        val list = mutableListOf<ChatItem>()
        msgs?.forEach { el ->
            val m = el as? JsonObject ?: return@forEach
            if (m.str("display_kind") == "hidden") return@forEach
            when (m.str("role")) {
                "user" -> list += ChatItem(
                    id = nextId(), kind = ChatKind.User, text = m.str("text") ?: "",
                )

                "assistant" -> {
                    val text = m.str("text") ?: ""
                    // reasoning.available can carry the final ANSWER verbatim —
                    // don't render it as a duplicate thinking bubble.
                    m.str("reasoning")
                        ?.takeIf { it.isNotBlank() && it.trim() != text.trim() }
                        ?.let {
                            list += ChatItem(id = nextId(), kind = ChatKind.Thinking, text = it)
                        }
                    list += ChatItem(
                        id = nextId(), kind = ChatKind.Assistant, text = text,
                    )
                }

                "tool" -> list += ChatItem(
                    id = nextId(), kind = ChatKind.Tool,
                    text = "✓ ${m.str("name") ?: "tool"}",
                    meta = m.str("context") ?: "",
                )
            }
        }
        _items.value = list
        assistantId = null
        turnAssistantIndex = null
        thinkingId = null
        openToolId = null
        // Hydration replaces the transcript — request cards must be re-seeded
        // from open_requests, so drop the stale dedupe index and timers.
        reqTimers.values.forEach { it.cancel() }
        reqTimers.clear()
        reqIndex.clear()
        _statusLine.value = ""
        _streaming.value = res["running"]?.jsonPrimitive?.booleanOrNull == true
        reportOpenRequests()
    }

    // ---- live events ---------------------------------------------------------

    private fun onEvent(ev: GatewayEvent) {
        val sid = ev.sessionId
        if (sid != null && sid != runtimeSessionId && sid != storedSessionId) return
        val p = ev.payload
        when (ev.type) {
            "message.start" -> startAssistant()

            "message.delta" ->
                appendAssistantDelta(p?.str("delta") ?: p?.str("text") ?: "")

            "message.interim", "message.complete" -> {
                val text = p?.str("text")
                if (!text.isNullOrEmpty()) setAssistantText(text)
                finalizeStreaming()
            }

            // thinking.delta carries the turn status line ("(◇) orchestrating…")
            // followed by {"text":""} when done — a transient caption, NOT a
            // transcript row. reasoning.delta is the thinking bubble's only source.
            "thinking.delta" -> _statusLine.value = p?.str("text") ?: ""

            "reasoning.delta" -> appendThinking(p?.str("text") ?: "")

            "reasoning.available" -> {
                // Sometimes carries the final answer text instead of reasoning —
                // ignore blanks and answer-echoes, else (re)write the thinking row.
                val t = p?.str("text") ?: ""
                val answer = assistantId?.let { id ->
                    _items.value.firstOrNull { it.id == id }?.text
                } ?: ""
                if (t.isNotBlank() && t.trim() != answer.trim()) setThinking(t)
            }

            "tool.start" -> {
                openToolId = nextId()
                insertAboveAssistant(
                    ChatItem(
                        id = openToolId!!, kind = ChatKind.Tool, done = false,
                        text = "▶ ${p?.str("name") ?: "tool"}",
                    )
                )
            }

            "tool.complete" -> {
                val id = openToolId
                if (id != null) {
                    mutate(id) {
                        it.copy(text = "✓ ${p?.str("name") ?: "tool"}", done = true)
                    }
                    openToolId = null
                } else {
                    insertAboveAssistant(
                        ChatItem(
                            id = nextId(), kind = ChatKind.Tool, done = true,
                            text = "✓ ${p?.str("name") ?: "tool"}",
                        )
                    )
                }
            }

            "status.update" -> append(ChatKind.Status, p?.str("text") ?: "")

            "error" -> {
                append(ChatKind.Error, p?.str("message") ?: p?.str("text") ?: "Unknown error")
                finalizeStreaming()
            }

            // Legacy event-style requests (some builds emit *.request events
            // instead of srq frames) become the same cards; they answer via
            // the *.respond RPCs since they carry no frame id.
            in REQUEST_EVENTS ->
                pushRequest(ev.type.removeSuffix(".request"), p ?: JsonObject(emptyMap()), srqId = null)

            // The backend withdrew an open request: {id, method, reason}.
            "request.cancel" -> {
                val rid = p?.str("id")
                resolveRequest(rid, p?.str("reason") ?: "cancelled")
            }

            "session.info" -> {
                val info = p?.get("info") as? JsonObject ?: p
                info?.str("title")?.takeIf { it.isNotBlank() }?.let { _title.value = it }
            }

            // sessions.changed / everything else: no transcript row.
            else -> {
                // *.resolved / *.expired / *.answered / *.cancelled / *.timeout
                // carry the request_id they closed.
                if (RESOLVE_SUFFIX.containsMatchIn(ev.type)) {
                    resolveRequest(p?.str("request_id") ?: p?.str("requestId"), "handled elsewhere")
                }
            }
        }
    }

    // ---- interactive requests ------------------------------------------------

    /** A server→client request frame (srq id) scoped to this session. */
    private fun onRequestFrame(req: GatewayRequest) {
        val sid = req.params.str("session_id")
        if (sid != null && sid != runtimeSessionId && sid != storedSessionId) return
        pushRequest(req.method, req.params, srqId = req.id)
    }

    /** Push a request card; dedupe by request_id / srq id; start the TTL timer. */
    private fun pushRequest(method: String, params: JsonObject, srqId: JsonElement?) {
        val req = pendingRequestFrom(method, params, srqId)
        val dedupeKeys = listOfNotNull(req.requestId, srqIdString(req))
        if (dedupeKeys.any { reqIndex.containsKey(it) }) return

        val id = nextId()
        reqIndex += dedupeKeys.associateWith { id }
        append(
            ChatItem(
                id = id, kind = ChatKind.Request,
                text = req.command.ifBlank {
                    req.questions.firstOrNull()?.question ?: req.prompt.ifBlank { req.title }
                },
                request = req,
            )
        )
        reqTimers[id] = scope.launch {
            val wait = req.expiresAt - System.currentTimeMillis()
            if (wait > 0) kotlinx.coroutines.delay(wait)
            patchRequest(id) { it.copy(state = RequestState.Expired, resolution = "timed out") }
        }
        reportOpenRequests()
    }

    private fun patchRequest(itemId: String, f: (PendingRequest) -> PendingRequest) {
        val item = _items.value.firstOrNull { it.id == itemId } ?: return
        val req = item.request ?: return
        val patched = f(req)
        if (patched.state == RequestState.Resolved || patched.state == RequestState.Expired) {
            reqTimers.remove(itemId)?.cancel()
        }
        mutate(itemId) { it.copy(request = patched) }
        reportOpenRequests()
    }

    /** Server-side resolution noticed via request.cancel / *.resolved events. */
    private fun resolveRequest(requestIdOrSrq: String?, resolution: String) {
        if (requestIdOrSrq == null) return
        val itemId = reqIndex[requestIdOrSrq] ?: return
        com.cornerstone.agency.service.NotificationCenter.resolve(requestIdOrSrq)
        patchRequest(itemId) {
            if (it.open) it.copy(state = RequestState.Resolved, resolution = resolution) else it
        }
    }

    private fun srqIdString(req: PendingRequest): String? =
        req.srqId?.jsonPrimitive?.contentOrNull

    private fun reportOpenRequests() {
        val open = _items.value.count { it.request?.open == true }
        PendingRequests.report(storedSessionId ?: runtimeSessionId ?: "", profile, open)
    }

    /** Optimistic respond: mark Resolved immediately, revert to Open on failure. */
    private suspend fun respond(
        itemId: String,
        resolution: String,
        result: JsonObject,
        rpcFallback: suspend (PendingRequest) -> Unit,
    ) {
        val req = _items.value.firstOrNull { it.id == itemId }?.request ?: return
        if (req.state != RequestState.Open) return
        patchRequest(itemId) { it.copy(state = RequestState.Sending, respondError = "") }
        try {
            val srq = req.srqId
            if (srq != null) {
                if (!gateway.respond(srq, result)) throw java.io.IOException("socket closed")
            } else {
                rpcFallback(req)
            }
            patchRequest(itemId) {
                it.copy(state = RequestState.Resolved, resolution = resolution)
            }
            com.cornerstone.agency.service.NotificationCenter.resolve(
                req.requestId ?: req.srqId?.jsonPrimitive?.contentOrNull,
            )
        } catch (e: Exception) {
            patchRequest(itemId) {
                it.copy(state = RequestState.Open, respondError = friendly(e))
            }
        }
    }

    suspend fun approveRequest(itemId: String, choice: String) {
        val resolution = if (choice == "deny") "Denied" else "Approved $choice"
        respond(
            itemId, resolution,
            buildJsonObject { put("choice", choice) },
        ) { req ->
            gateway.call(
                "approval.respond",
                buildJsonObject {
                    put("session_id", runtimeSessionId ?: req.sessionId)
                    put("choice", choice)
                    req.requestId?.let { put("request_id", it) }
                },
            )
            Unit
        }
    }

    /** [answers] maps question qid → answer; a single "" key means {answer}. */
    suspend fun answerClarify(itemId: String, answers: Map<String, String>) {
        val single = answers[""] ?: answers.values.firstOrNull().orEmpty()
        val result = if (answers.size <= 1) {
            buildJsonObject { put("answer", single) }
        } else {
            buildJsonObject {
                put("answers", buildJsonObject {
                    answers.forEach { (qid, a) -> if (qid.isNotEmpty()) put(qid, a) }
                })
            }
        }
        respond(itemId, "Answered: $single", result) { req ->
            gateway.call(
                "clarify.respond",
                buildJsonObject {
                    req.requestId?.let { put("request_id", it) }
                    if (answers.size <= 1) {
                        put("answer", single)
                    } else {
                        put("answers", buildJsonObject {
                            answers.forEach { (qid, a) -> if (qid.isNotEmpty()) put(qid, a) }
                        })
                    }
                },
            )
            Unit
        }
    }

    /** secret / sudo / vault.* / other one-string prompts. Never logged or persisted. */
    suspend fun submitRequestValue(itemId: String, value: String) {
        respond(itemId, "Sent", buildJsonObject { put("value", value) }) { req ->
            val rpc = when (req.kind) {
                RequestKind.Secret -> "secret.respond" to "value"
                RequestKind.Sudo -> "sudo.respond" to "password"
                else -> return@respond Unit
            }
            gateway.call(
                rpc.first,
                buildJsonObject {
                    req.requestId?.let { put("request_id", it) }
                    put(rpc.second, value)
                },
            )
            Unit
        }
    }

    suspend fun respondMcp(itemId: String, approved: Boolean) {
        respond(
            itemId, if (approved) "Approved" else "Denied",
            buildJsonObject { put("approved", approved) },
        ) { req ->
            gateway.call(
                "mcp.setup.respond",
                buildJsonObject {
                    req.requestId?.let { put("request_id", it) }
                    put("result", "{\"approved\":$approved}")
                },
            )
            Unit
        }
    }

    // ---- item mutation helpers -------------------------------------------------

    private fun nextId() = "i${localId.incrementAndGet()}"

    private fun append(kind: ChatKind, text: String) {
        append(ChatItem(id = nextId(), kind = kind, text = text))
    }

    private fun append(item: ChatItem) {
        _items.value = _items.value + item
    }

    /**
     * Insert a row just above the current turn's assistant bubble (keeping
     * thinking/tool lines visually before the answer) or append if the bubble
     * doesn't exist yet. Each insert bumps [turnAssistantIndex] so later rows
     * stay ordered.
     */
    private fun insertAboveAssistant(item: ChatItem) {
        val idx = turnAssistantIndex
        if (idx != null && idx <= _items.value.size) {
            _items.value = _items.value.toMutableList().apply { add(idx, item) }
            turnAssistantIndex = idx + 1
        } else {
            append(item)
        }
    }

    private fun mutate(id: String, f: (ChatItem) -> ChatItem) {
        _items.value = _items.value.map { if (it.id == id) f(it) else it }
    }

    private fun startAssistant() {
        thinkingId = null
        val id = nextId()
        assistantId = id
        turnAssistantIndex = _items.value.size
        append(ChatItem(id = id, kind = ChatKind.Assistant, text = "", streaming = true))
        _streaming.value = true
    }

    private fun appendAssistantDelta(delta: String) {
        if (delta.isEmpty()) return
        val id = assistantId ?: run {
            startAssistant()
            assistantId!!
        }
        mutate(id) { it.copy(text = it.text + delta, streaming = true) }
    }

    private fun setAssistantText(text: String) {
        val id = assistantId ?: run {
            val newId = nextId()
            assistantId = newId
            turnAssistantIndex = _items.value.size
            append(ChatItem(id = newId, kind = ChatKind.Assistant, text = text))
            newId
        }
        mutate(id) { it.copy(text = text, streaming = false, done = true) }
    }

    private fun appendThinking(delta: String) {
        if (delta.isEmpty()) return
        val id = thinkingId
        if (id == null) {
            val newId = nextId()
            thinkingId = newId
            insertAboveAssistant(ChatItem(id = newId, kind = ChatKind.Thinking, text = delta))
        } else {
            mutate(id) { it.copy(text = it.text + delta) }
        }
    }

    private fun setThinking(text: String) {
        val id = thinkingId
        if (id == null) {
            val newId = nextId()
            thinkingId = newId
            insertAboveAssistant(ChatItem(id = newId, kind = ChatKind.Thinking, text = text))
        } else {
            mutate(id) { it.copy(text = text) }
        }
    }

    private fun finalizeStreaming() {
        _streaming.value = false
        _statusLine.value = ""
        _items.value = _items.value.map {
            if (it.streaming || !it.done) it.copy(streaming = false, done = true) else it
        }
        assistantId = null
        turnAssistantIndex = null
        thinkingId = null
        openToolId = null
    }

    private fun friendly(e: Exception): String = when (e) {
        is GatewayRpcException -> e.message ?: "rpc error ${e.code}"
        is java.io.IOException -> "socket unavailable"
        else -> e.message ?: e.javaClass.simpleName
    }
}

private fun kotlinx.serialization.json.JsonElement.asObj(): JsonObject =
    this as? JsonObject ?: JsonObject(emptyMap())

private fun JsonObject.str(key: String): String? =
    (this[key] as? kotlinx.serialization.json.JsonPrimitive)?.contentOrNull

/** session.list for one profile — the server scopes the listing per profile DB. */
internal suspend fun listProfileSessions(
    gateway: GatewaySocket,
    profile: String,
    limit: Int = 200,
): List<SessionSummary> {
    val res = gateway.call(
        "session.list",
        buildJsonObject {
            put("profile", profile)
            put("limit", limit)
        },
    )
    return runCatching {
        JSON.decodeFromJsonElement(SessionListResult.serializer(), res)
    }.getOrNull()?.sessions
        ?.sortedByDescending { it.startedAt }
        ?: emptyList()
}

internal suspend fun deleteStoredSession(gateway: GatewaySocket, storedId: String) {
    gateway.call("session.delete", buildJsonObject { put("session_id", storedId) })
}

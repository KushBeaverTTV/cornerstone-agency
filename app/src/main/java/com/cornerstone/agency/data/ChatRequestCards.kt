package com.cornerstone.agency.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.longOrNull
import kotlinx.serialization.json.put
import java.util.concurrent.atomic.AtomicLong

/** Interactive request cards (approval / clarify / secret / sudo / MCP setup) inside a chat. */
abstract class ChatRequestCards(
    gateway: GatewayLink,
    profile: String,
    dispatcher: CoroutineDispatcher,
) : ChatTranscript(gateway, profile, dispatcher) {

    // Interactive requests: item id -> expiry job, plus dedupe indexes on both
    // the wire frame id (srq-…) and the payload request_id.
    protected val reqTimers = HashMap<String, Job>()
    protected val reqIndex = HashMap<String, String>()

    /** session.resume can carry still-open requests (open_requests entries look
     * like serialized request frames {id, method, params}; pending_* fields are
     * per-kind payloads). Seeded cards dedupe against live replays by request_id. */
    protected fun seedPendingFromResume(res: JsonObject) {
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

    /** approval.pending catches requests still open after a reconnect — the
     * resume snapshot's open_requests is empty when the old transport died. */
    protected suspend fun seedPendingApprovals() {
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

    // ---- interactive requests ------------------------------------------------

    /** A server→client request frame (srq id) scoped to this session. */
    protected fun onRequestFrame(req: GatewayRequest) {
        val sid = req.params.str("session_id")
        if (sid != null && sid != runtimeSessionId && sid != storedSessionId) return
        pushRequest(req.method, req.params, srqId = req.id)
    }

    /** Push a request card; dedupe by request_id / srq id; start the TTL timer. */
    protected fun pushRequest(method: String, params: JsonObject, srqId: JsonElement?) {
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

    protected fun patchRequest(itemId: String, f: (PendingRequest) -> PendingRequest) {
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
    protected fun resolveRequest(requestIdOrSrq: String?, resolution: String) {
        if (requestIdOrSrq == null) return
        val itemId = reqIndex[requestIdOrSrq] ?: return
        com.cornerstone.agency.service.NotificationCenter.resolve(requestIdOrSrq)
        patchRequest(itemId) {
            if (it.open) it.copy(state = RequestState.Resolved, resolution = resolution) else it
        }
    }

    protected fun srqIdString(req: PendingRequest): String? =
        req.srqId?.jsonPrimitive?.contentOrNull

    protected fun reportOpenRequests() {
        val open = _items.value.count { it.request?.open == true }
        PendingRequests.report(storedSessionId ?: runtimeSessionId ?: "", profile, open)
    }

    /** Optimistic respond: mark Resolved immediately, revert to Open on failure. */
    protected suspend fun respond(
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

    override suspend fun approveRequest(itemId: String, choice: String) {
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

    /** [answers] maps question qid → answer; a single "" key means {answer}.
     * Batch requests (the server sent `questions[]` with qids) must close with
     * {"answers": {qid: …}} — a result without `answers` is a cancel-all. */
    override suspend fun answerClarify(itemId: String, answers: Map<String, String>) {
        val req = _items.value.firstOrNull { it.id == itemId }?.request ?: return
        val batch = req.questions.any { it.qid.isNotEmpty() }
        val single = answers[""] ?: answers.values.firstOrNull().orEmpty()
        val result = if (batch) {
            buildJsonObject {
                put("answers", buildJsonObject {
                    answers.forEach { (qid, a) -> put(qid.ifEmpty { "q0" }, a) }
                })
            }
        } else {
            buildJsonObject { put("answer", single) }
        }
        respond(itemId, "Answered: $single", result) { r ->
            gateway.call(
                "clarify.respond",
                buildJsonObject {
                    r.requestId?.let { put("request_id", it) }
                    if (batch) {
                        put("answers", buildJsonObject {
                            answers.forEach { (qid, a) -> put(qid.ifEmpty { "q0" }, a) }
                        })
                    } else {
                        put("answer", single)
                        answers.keys.firstOrNull()
                            ?.takeIf { it.isNotEmpty() }
                            ?.let { put("question_id", it) }
                    }
                },
            )
            Unit
        }
    }

    /** secret / sudo / vault.* / other one-string prompts. Never logged or persisted. */
    override suspend fun submitRequestValue(itemId: String, value: String) {
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

    override suspend fun respondMcp(itemId: String, approved: Boolean) {
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

}

private fun JsonElement.asObj(): JsonObject =
    this as? JsonObject ?: JsonObject(emptyMap())

private fun JsonObject.str(key: String): String? =
    (this[key] as? JsonPrimitive)?.contentOrNull

private fun JsonObject.long(key: String): Long? =
    (this[key] as? JsonPrimitive)?.let { it.longOrNull ?: it.contentOrNull?.toLongOrNull() }

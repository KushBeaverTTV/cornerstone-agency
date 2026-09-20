package com.cornerstone.agency.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonPrimitive

/** tui_gateway server→client request methods the chat surface can answer. */
enum class RequestKind(val wire: String, val kicker: String) {
    Approval("approval", "APPROVAL"),
    Clarify("clarify", "QUESTION"),
    Secret("secret", "SECRET"),
    Sudo("sudo", "SUDO"),
    McpSetup("mcp.setup", "MCP SETUP"),
    /** vault.* / terminal.read / preview.* / tour — one-string answer prompts. */
    Value("", "REQUEST"),
    ;

    companion object {
        fun forMethod(method: String): RequestKind =
            entries.firstOrNull { it.wire == method } ?: Value
    }
}

enum class RequestState { Open, Sending, Resolved, Expired }

data class ClarifyQuestion(
    val qid: String,
    val question: String,
    val choices: List<String> = emptyList(),
    val multiSelect: Boolean = false,
)

/**
 * One interactive request shown as a transcript card. Mirrors Herald's flat
 * RequestData (single record with per-kind optional fields) rather than a
 * sealed hierarchy — the card renders by kind and patches state by copy.
 *
 * [srqId] is the live request frame id (answerable via GatewaySocket.respond);
 * [requestId] is the server-side request id carried in the payload (used for
 * dedupe, *.resolved matching, and the *.respond RPC fallback).
 */
data class PendingRequest(
    val kind: RequestKind,
    val srqId: JsonElement? = null,
    val requestId: String? = null,
    val sessionId: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val expiresAt: Long = createdAt + REQUEST_TTL_MS,
    val state: RequestState = RequestState.Open,
    val resolution: String = "",
    val respondError: String = "",
    // approval
    val command: String = "",
    val description: String = "",
    val toolName: String = "",
    val choices: List<String> = emptyList(),
    val allowPermanent: Boolean = true,
    val allowSession: Boolean = true,
    // clarify
    val questions: List<ClarifyQuestion> = emptyList(),
    // secret / sudo / value
    val prompt: String = "",
    val envVar: String = "",
    // mcp / unknown
    val title: String = "",
) {
    val open: Boolean
        get() = state == RequestState.Open || state == RequestState.Sending

    val minutesLeft: Long
        get() = ((expiresAt - System.currentTimeMillis()) / 60_000).coerceAtLeast(0)

    companion object {
        const val REQUEST_TTL_MS = 5 * 60 * 1000L
    }
}

private fun JsonObject.str(key: String): String? =
    this[key]?.jsonPrimitive?.contentOrNull

private fun JsonObject.bool(key: String, default: Boolean): Boolean =
    this[key]?.jsonPrimitive?.contentOrNull?.let {
        it.equals("true", true) || it == "1"
    } ?: default

private fun JsonObject.strList(key: String): List<String> =
    (this[key] as? kotlinx.serialization.json.JsonArray)
        ?.mapNotNull { it.jsonPrimitive.contentOrNull } ?: emptyList()

/** Build a PendingRequest from a request method + params (live frame or seeded). */
fun pendingRequestFrom(
    method: String,
    params: JsonObject,
    srqId: JsonElement?,
): PendingRequest {
    val kind = RequestKind.forMethod(method)
    val questions = (params["questions"] as? kotlinx.serialization.json.JsonArray)
        ?.mapNotNull { el ->
            (el as? JsonObject)?.let {
                ClarifyQuestion(
                    qid = it.str("qid") ?: "",
                    question = it.str("question") ?: "",
                    choices = it.strList("choices"),
                    multiSelect = it.bool("multi_select", false),
                )
            }
        } ?: emptyList()
    val single = params.str("question")
    return PendingRequest(
        kind = kind,
        srqId = srqId,
        requestId = params.str("request_id") ?: params.str("requestId"),
        sessionId = params.str("session_id"),
        command = params.str("command") ?: "",
        description = params.str("description") ?: params.str("prompt") ?: "",
        toolName = params.str("tool_name") ?: params.str("tool") ?: "",
        choices = params.strList("choices"),
        allowPermanent = params.bool("allow_permanent", true),
        allowSession = params.bool("allow_session", true),
        questions = when {
            questions.isNotEmpty() -> questions
            single != null -> listOf(
                ClarifyQuestion(
                    qid = "",
                    question = single,
                    choices = params.strList("choices"),
                    multiSelect = params.bool("multi_select", false),
                )
            )
            else -> emptyList()
        },
        prompt = params.str("prompt") ?: "",
        envVar = params.str("env_var") ?: params.str("envVar") ?: "",
        title = params.str("title") ?: method,
    )
}

/**
 * Cross-screen badge state: which stored-session ids (and profiles) currently
 * have an Open request. ChatRepository instances report their counts; Chat is
 * the only place requests can be answered today, so entries clear when the
 * owning repo closes.
 */
object PendingRequests {
    private val trackerScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private val _openBySession = MutableStateFlow<Map<String, Int>>(emptyMap())
    private val _openByProfile = MutableStateFlow<Map<String, Int>>(emptyMap())
    // Requests seen by the global socket listener (NotificationCenter) for
    // sessions no chat screen currently has open.
    private val _externalBySession = MutableStateFlow<Map<String, Int>>(emptyMap())
    private val _externalByProfile = MutableStateFlow<Map<String, Int>>(emptyMap())

    private fun merged(
        a: Map<String, Int>, b: Map<String, Int>,
    ): Map<String, Int> = (a.keys + b.keys).associateWith {
        (a[it] ?: 0) + (b[it] ?: 0)
    }

    val anyOpen: StateFlow<Boolean> =
        kotlinx.coroutines.flow.combine(_openBySession, _externalBySession) { a, b ->
            merged(a, b).values.any { it > 0 }
        }.stateIn(trackerScope, SharingStarted.Eagerly, false)
    val openSessions: StateFlow<Set<String>> =
        kotlinx.coroutines.flow.combine(_openBySession, _externalBySession) { a, b ->
            merged(a, b).filterValues { it > 0 }.keys
        }.stateIn(trackerScope, SharingStarted.Eagerly, emptySet())
    val openProfiles: StateFlow<Set<String>> =
        kotlinx.coroutines.flow.combine(_openByProfile, _externalByProfile) { a, b ->
            merged(a, b).filterValues { it > 0 }.keys
        }.stateIn(trackerScope, SharingStarted.Eagerly, emptySet())

    fun report(sessionKey: String, profile: String, count: Int) {
        if (sessionKey.isNotEmpty()) {
            _openBySession.value = _openBySession.value +
                (sessionKey to count)
        }
        _openByProfile.value = _openByProfile.value + (profile to count)
    }

    /** Absolute per-session/per-profile open counts from the global listener. */
    fun reportExternal(bySession: Map<String, Int>, byProfile: Map<String, Int>) {
        _externalBySession.value = bySession
        _externalByProfile.value = byProfile
    }

    fun clear(sessionKey: String, profile: String) {
        _openBySession.value = _openBySession.value - sessionKey
        _openByProfile.value = _openByProfile.value - profile
    }
}

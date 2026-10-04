package com.cornerstone.agency.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withTimeoutOrNull
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.put

/**
 * Writes the Feed with the default bot, headlessly: opens a throwaway session over the
 * gateway, asks for a JSON batch of posts, parses it into [FeedPost]s, then deletes the
 * session so it never shows up as a chat. One run at a time.
 */
class FeedWriter(
    private val gateway: GatewaySocket,
    private val companion: CompanionRepository,
) {
    sealed interface State {
        data object Idle : State
        data class Writing(val status: String) : State
        data class Failed(val message: String) : State
        data class Done(val added: Int) : State
    }

    private val _state = MutableStateFlow<State>(State.Idle)
    val state: StateFlow<State> = _state.asStateFlow()

    val busy: Boolean get() = _state.value is State.Writing

    fun dismiss() {
        if (!busy) _state.value = State.Idle
    }

    suspend fun write(profile: String = "default") {
        if (busy) return
        _state.value = State.Writing("Opening a line to the gateway…")
        if (gateway.state.value != GatewayState.OPEN) {
            gateway.connect()
            val ok = withTimeoutOrNull(10_000) { gateway.state.first { it == GatewayState.OPEN } }
            if (ok == null) {
                _state.value = State.Failed("Gateway is offline")
                return
            }
        }
        val chat = ChatRepository(gateway, profile)
        var keepSession = false
        // session.create may not echo a stored id, and session.delete needs one: diff the
        // profile's session list before/after so the throwaway can always be found.
        val before = runCatching { listProfileSessions(gateway, profile, includeHidden = true).map { it.id }.toHashSet() }
            .getOrDefault(hashSetOf())
        try {
            chat.newSession()
            if (chat.lastError.value.isNotEmpty()) {
                _state.value = State.Failed(chat.lastError.value)
                return
            }
            // Born hidden: the gateway keeps it resumable but leaves it out of session.list, so the
            // throwaway never shows in Chats — even if the app dies before cleanup runs.
            chat.runtimeSessionId?.let { rt ->
                runCatching {
                    gateway.call("session.set_hidden", buildJsonObject { put("session_id", rt); put("hidden", true) })
                }.onFailure { android.util.Log.w(TAG, "session.set_hidden failed: ${it.message}") }
            }
            _state.value = State.Writing("Reading what's new…")
            chat.send(prompt(companion.state.value.feedInstructions))

            // Poll. `streaming` can dip to false between tool rounds, so the turn only counts as
            // finished once it has been quiet (no streaming, no new rows) for a couple of seconds
            // and the last row is assistant text. Bail if the bot asks for permission.
            val started = System.currentTimeMillis()
            var finished = false
            var quietSince = 0L
            var lastSig = ""
            while (System.currentTimeMillis() - started < TIMEOUT_MS) {
                delay(400)
                val items = chat.items.value
                if (items.any { it.kind == ChatKind.Request && it.request?.open == true }) {
                    keepSession = true
                    _state.value = State.Failed("Needs your approval — open the chat to continue")
                    return
                }
                val last = items.lastOrNull()
                val tool = items.lastOrNull { it.kind == ChatKind.Tool && !it.done }
                _state.value = when {
                    tool != null -> State.Writing("Using ${tool.text.removePrefix("▶").trim().take(28)}…")
                    last?.kind == ChatKind.Thinking -> State.Writing("Thinking…")
                    last?.kind == ChatKind.Assistant -> State.Writing("Writing…")
                    else -> _state.value
                }
                val sig = "${items.size}:${last?.text?.length ?: 0}:${chat.streaming.value}"
                if (sig != lastSig) { lastSig = sig; quietSince = System.currentTimeMillis() }
                val quiet = !chat.streaming.value && tool == null &&
                    System.currentTimeMillis() - quietSince >= QUIET_MS
                if (quiet && last?.kind == ChatKind.Assistant && last.text.isNotBlank()) {
                    finished = true
                    break
                }
                if (quiet && last?.kind == ChatKind.Error) break
            }
            if (!finished) {
                val err = chat.items.value.lastOrNull { it.kind == ChatKind.Error }?.text
                _state.value = State.Failed(err ?: "Took too long — try again")
                return
            }
            // Parse the newest assistant text that reads as a batch; earlier ones may be preamble.
            val posts = chat.items.value.asReversed()
                .filter { it.kind == ChatKind.Assistant && it.text.isNotBlank() }
                .firstNotNullOfOrNull { parse(it.text).takeIf { p -> p.isNotEmpty() } }
                .orEmpty()
            if (posts.isEmpty()) {
                keepSession = true
                _state.value = State.Failed("Couldn't read the reply as posts — open the chat to see it")
                return
            }
            val added = companion.addPosts(posts)
            _state.value = State.Done(added)
        } catch (e: Exception) {
            _state.value = State.Failed(e.message ?: "Feed writing failed")
        } finally {
            val stored = chat.storedSessionId
            val runtime = chat.runtimeSessionId
            if (keepSession) {
                // The user needs to open this one, so put it back in the list.
                (runtime ?: stored)?.let { id ->
                    runCatching {
                        gateway.call("session.set_hidden", buildJsonObject { put("session_id", id); put("hidden", false) })
                    }
                }
            }
            chat.close()
            if (!keepSession) cleanup(profile, runtime, stored, before)
        }
    }

    /**
     * The gateway refuses `session.delete` while the runtime session is live ("cannot delete an
     * active session"), so tear it down with `session.close` first, then delete every stored id
     * that appeared since we started and verify, retrying a few times for the late persist.
     */
    private suspend fun cleanup(profile: String, runtime: String?, stored: String?, before: Set<String>) {
        runtime?.let { rt ->
            runCatching { gateway.call("session.close", buildJsonObject { put("session_id", rt) }) }
                .onFailure { android.util.Log.w(TAG, "session.close $rt failed: ${it.message}") }
        }
        delay(1500)
        repeat(4) { attempt ->
            val fresh = runCatching { listProfileSessions(gateway, profile, includeHidden = true) }.getOrDefault(emptyList())
                .map { it.id }.filterNot { it in before }
            val targets = LinkedHashSet<String>().apply { stored?.let { add(it) }; addAll(fresh) }
            if (targets.isEmpty() && attempt > 0) return
            targets.forEach { id ->
                runCatching { deleteStoredSession(gateway, id) }
                    .onFailure { android.util.Log.w(TAG, "session.delete $id failed: ${it.message}") }
            }
            delay(2000)
            val still = runCatching { listProfileSessions(gateway, profile, includeHidden = true) }.getOrDefault(emptyList())
                .map { it.id }.filterNot { it in before }
            if (still.isEmpty()) return
            android.util.Log.w(TAG, "feed session still listed after delete (attempt ${attempt + 1}): $still")
        }
    }

    private fun prompt(instructions: String): String = buildString {
        appendLine("Write my feed for today.")
        appendLine()
        appendLine("Brief: ${instructions.trim()}")
        appendLine()
        appendLine("Return 6 to 8 posts as ONE JSON array and nothing else — no prose, no code fences:")
        appendLine("""[{"topic":"AI models","title":"…","body":"…","link":"https://…"}]""")
        appendLine("- topic: one or two words naming the section (e.g. \"AI models\", \"Dev tools\", \"PC gaming\")")
        appendLine("- title: under 80 characters, plain and specific, no clickbait")
        appendLine("- body: one to three sentences — what happened and why it matters to me")
        appendLine("- link: a real source URL if you have one, otherwise an empty string")
        appendLine("Use web search if you have it so the items are current. Do not ask me questions; just write.")
    }

    /** Lenient: finds the outermost array in the reply, tolerates fences and stray prose. */
    private fun parse(reply: String): List<FeedPost> {
        val cleaned = reply.replace(Regex("```[a-zA-Z]*"), "")
        val start = cleaned.indexOf('[')
        val end = cleaned.lastIndexOf(']')
        if (start < 0 || end <= start) return emptyList()
        val arr = runCatching { JSON.parseToJsonElement(cleaned.substring(start, end + 1)) as? JsonArray }
            .getOrNull() ?: return emptyList()
        val now = System.currentTimeMillis()
        return arr.mapIndexedNotNull { i, el ->
            val o = el as? JsonObject ?: return@mapIndexedNotNull null
            fun s(k: String) = (o[k] as? JsonPrimitive)?.contentOrNull?.trim().orEmpty()
            val title = s("title").ifBlank { s("headline") }
            if (title.isBlank()) return@mapIndexedNotNull null
            FeedPost(
                title = title,
                body = s("body").ifBlank { s("summary") },
                link = s("link").ifBlank { s("url") }.takeIf { it.startsWith("http") }.orEmpty(),
                topic = s("topic").ifBlank { s("section") },
                // Keep the writer's order: first item newest.
                publishedAt = now - i,
            )
        }
    }

    companion object {
        private const val TAG = "FeedWriter"
        private const val TIMEOUT_MS = 6 * 60 * 1000L
        private const val QUIET_MS = 2500L
        private val JSON = Json { ignoreUnknownKeys = true; isLenient = true }
    }
}

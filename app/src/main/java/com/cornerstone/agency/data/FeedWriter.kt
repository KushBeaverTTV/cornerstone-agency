package com.cornerstone.agency.data

import android.content.Context
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.withContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
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
 *
 * Process-scoped (like [GatewayHub]): a run started from the Feed tab keeps going when the
 * Activity is swiped away, and the scheduled [com.cornerstone.agency.service.FeedWorker]
 * drives the same instance so there is never more than one issue being written.
 */
class FeedWriter private constructor(private val appContext: Context) {

    companion object {
        private const val TAG = "FeedWriter"
        private const val TIMEOUT_MS = 6 * 60 * 1000L
        private const val QUIET_MS = 2500L
        private val JSON = Json { ignoreUnknownKeys = true; isLenient = true }

        @Volatile private var instance: FeedWriter? = null

        fun get(context: Context): FeedWriter =
            instance ?: synchronized(this) {
                instance ?: FeedWriter(context.applicationContext).also { instance = it }
            }
    }

    sealed interface State {
        data object Idle : State
        data class Writing(val status: String) : State
        data class Failed(val message: String) : State
        data class Done(val added: Int) : State
    }

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private val companion: CompanionRepository get() = CompanionRepository.get(appContext)

    private val _state = MutableStateFlow<State>(State.Idle)
    val state: StateFlow<State> = _state.asStateFlow()

    val busy: Boolean get() = _state.value is State.Writing

    @Volatile private var cancelRequested = false

    fun dismiss() {
        if (!busy) _state.value = State.Idle
    }

    /** Stop the current run. The poll loop notices, interrupts the turn and cleans up. */
    fun cancel() {
        if (busy) cancelRequested = true
    }

    /** Fire-and-forget from the UI. Survives the ViewModel. */
    fun start() {
        if (busy) return
        scope.launch { write() }
    }

    /**
     * The socket normally already exists (the ViewModel attached it). A scheduled run in a
     * cold process has to attach one itself from the stored credentials.
     */
    private fun gateway(): GatewaySocket =
        GatewayHub.socket ?: GatewayHub.attach(ConnectionRepository(SecureStore(appContext)))

    /**
     * Write one issue. Returns the final state. [scheduled] runs post a notification on
     * success (the user is not looking at the app) and never surface approval prompts.
     */
    suspend fun write(scheduled: Boolean = false): State {
        if (busy) return _state.value
        cancelRequested = false
        _state.value = State.Writing("Opening a line to the gateway…")
        companion.awaitLoaded()
        val profile = companion.state.value.feedProfile.ifBlank { "default" }
        val gateway = gateway()
        if (gateway.state.value != GatewayState.OPEN) {
            gateway.connect()
            val ok = withTimeoutOrNull(15_000) { gateway.state.first { it == GatewayState.OPEN } }
            if (ok == null) {
                _state.value = State.Failed("Gateway is offline")
                return _state.value
            }
        }
        val chat = ChatRepository(gateway, profile, persistent = false)
        var keepSession = false
        // Captured before cleanup: the UI may dismiss Done → Idle while inally is still deleting the session.
        var result: State = State.Idle
        // session.create may not echo a stored id, and session.delete needs one: diff the
        // profile's session list before/after so the throwaway can always be found.
        val before = runCatching { listProfileSessions(gateway, profile, includeHidden = true).map { it.id }.toHashSet() }
            .getOrDefault(hashSetOf())
        try {
            chat.newSession()
            if (chat.lastError.value.isNotEmpty()) {
                _state.value = State.Failed(chat.lastError.value)
                return _state.value
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
                if (cancelRequested) {
                    chat.stop()
                    _state.value = State.Idle
                    return _state.value
                }
                val items = chat.items.value
                if (items.any { it.kind == ChatKind.Request && it.request?.open == true }) {
                    if (scheduled) {
                        // Nobody is there to answer; don't leave a half-written session around.
                        chat.stop()
                        _state.value = State.Failed("Skipped today's issue — the bot needed approval")
                    } else {
                        keepSession = true
                        _state.value = State.Failed("Needs your approval — open the chat to continue")
                    }
                    return _state.value
                }
                val last = items.lastOrNull()
                val tool = items.lastOrNull { it.kind == ChatKind.Tool && !it.done }
                val mins = (System.currentTimeMillis() - started) / 60_000
                val suffix = if (mins >= 1) " · ${mins}m" else ""
                _state.value = when {
                    tool != null -> State.Writing("Using ${tool.text.removePrefix("▶").trim().take(28)}…$suffix")
                    last?.kind == ChatKind.Thinking -> State.Writing("Thinking…$suffix")
                    last?.kind == ChatKind.Assistant -> State.Writing("Writing…$suffix")
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
                return _state.value
            }
            // Parse the newest assistant text that reads as a batch; earlier ones may be preamble.
            val posts = chat.items.value.asReversed()
                .filter { it.kind == ChatKind.Assistant && it.text.isNotBlank() }
                .firstNotNullOfOrNull { parse(it.text).takeIf { p -> p.isNotEmpty() } }
                .orEmpty()
            if (posts.isEmpty()) {
                keepSession = true
                _state.value = State.Failed("Couldn't read the reply as posts — open the chat to see it")
                return _state.value
            }
            val added = companion.addPosts(posts)
            companion.markFeedRun()
            _state.value = State.Done(added)
            // Tell the user when they are not looking: scheduled runs always, manual runs only
            // if the app went to the background while the bot was writing.
            if (added > 0 && (scheduled || !GatewayHub.foregrounded)) {
                com.cornerstone.agency.service.NotificationCenter.postFeedReady(
                    appContext, companion.state.value.feedBotTitle, added,
                )
            }
        } catch (e: CancellationException) {
            // Worker stopped by the system: not an error worth showing.
            _state.value = State.Idle
        } catch (e: Exception) {
            _state.value = State.Failed(e.message ?: "Feed writing failed")
        } finally {
            result = _state.value
            val stored = chat.storedSessionId
            val runtime = chat.runtimeSessionId
            // Must run even if this coroutine was cancelled, or the throwaway session leaks.
            withContext(NonCancellable) {
                if (keepSession) {
                    // The user needs to open this one, so put it back in the list.
                    (runtime ?: stored)?.let { id ->
                        runCatching {
                            gateway.call("session.set_hidden", buildJsonObject { put("session_id", id); put("hidden", false) })
                        }
                    }
                } else if (result !is State.Done) {
                    // Timed out or cancelled mid-turn: stop the bot before deleting its session.
                    runCatching { chat.stop() }
                }
                chat.close()
                if (!keepSession) cleanup(gateway, profile, runtime, stored, before)
            }
        }
        return result
    }

    /**
     * The gateway refuses `session.delete` while the runtime session is live ("cannot delete an
     * active session"), so tear it down with `session.close` first, then delete every stored id
     * that appeared since we started and verify, retrying a few times for the late persist.
     */
    private suspend fun cleanup(gateway: GatewaySocket, profile: String, runtime: String?, stored: String?, before: Set<String>) {
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

    private fun parse(reply: String): List<FeedPost> = parseFeedPosts(reply)
}

private val FEED_JSON = Json { ignoreUnknownKeys = true; isLenient = true }

/**
 * Turn the bot's reply into posts. Lenient on purpose: strips code fences, takes the
 * outermost `[...]`, accepts `title|headline`, `body|summary`, `link|url`, `topic|section`,
 * drops entries without a title and non-http links. Order is preserved (first = newest).
 */
fun parseFeedPosts(reply: String, now: Long = System.currentTimeMillis()): List<FeedPost> {
    val cleaned = reply.replace(Regex("```[a-zA-Z]*"), "")
    val start = cleaned.indexOf('[')
    val end = cleaned.lastIndexOf(']')
    if (start < 0 || end <= start) return emptyList()
    val arr = runCatching { FEED_JSON.parseToJsonElement(cleaned.substring(start, end + 1)) as? JsonArray }
        .getOrNull() ?: return emptyList()
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
            publishedAt = now - i,
        )
    }
}

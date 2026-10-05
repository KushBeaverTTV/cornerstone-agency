package com.cornerstone.agency.data

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import java.io.File

/**
 * On-device copy of recent chats so the app opens instantly — and still shows something — when
 * the PC is unreachable. Plain JSON files under `filesDir/chat-cache`:
 *  - `t/<profile>/<storedId>.json` — the last [MAX_ITEMS] transcript rows of a chat,
 *  - `sessions-<profile>.json` — that bot's session list,
 *  - `profiles.json` — the bot roster,
 *  - `last-<profile>.txt` — the chat that bot last opened.
 * Bounded: at most [MAX_TRANSCRIPTS] transcript files (oldest evicted). Request cards, streaming
 * placeholders and phone-local picks are never written.
 */
object ChatCache {
    private const val MAX_ITEMS = 150
    private const val MAX_TRANSCRIPTS = 60

    private val json = Json { ignoreUnknownKeys = true; encodeDefaults = false }
    private val io = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    @Volatile
    private var root: File? = null

    val ready: Boolean get() = root != null

    fun init(context: Context) {
        if (root == null) root = File(context.applicationContext.filesDir, "chat-cache").apply { mkdirs() }
    }

    @Serializable
    private data class Row(
        val kind: String,
        val text: String,
        val meta: String = "",
        val rowId: Long? = null,
        val attachments: List<ChatAttachment> = emptyList(),
        val reactions: List<Reaction> = emptyList(),
        val ts: Double = 0.0,
    )

    @Serializable
    private data class Transcript(val title: String = "", val rows: List<Row> = emptyList())

    private fun safe(s: String) = s.replace(Regex("[^A-Za-z0-9._-]"), "_").take(80)

    private fun transcriptFile(profile: String, storedId: String): File? =
        root?.let { File(File(File(it, "t"), safe(profile)), safe(storedId) + ".json") }

    // ---- transcripts ---------------------------------------------------------

    fun saveTranscript(profile: String, storedId: String, title: String, items: List<ChatItem>) {
        val f = transcriptFile(profile, storedId) ?: return
        val rows = items.asSequence()
            .filter { it.kind != ChatKind.Request && !it.streaming }
            .map { item ->
                Row(
                    kind = item.kind.name, text = item.text, meta = item.meta, rowId = item.rowId,
                    // Local content:// grants do not survive a restart; keep only PC-side refs.
                    attachments = item.attachments.filter { it.remotePath.isNotEmpty() },
                    reactions = item.reactions, ts = item.timestamp,
                )
            }.toList().takeLast(MAX_ITEMS)
        if (rows.isEmpty()) return
        val payload = json.encodeToString(Transcript.serializer(), Transcript(title, rows))
        io.launch {
            runCatching {
                f.parentFile?.mkdirs()
                val tmp = File(f.parentFile, f.name + ".tmp")
                tmp.writeText(payload)
                if (!tmp.renameTo(f)) { f.delete(); tmp.renameTo(f) }
                evict()
            }
        }
    }

    /** Cached rows for a chat, with fresh local ids. Null when nothing is cached. */
    suspend fun loadTranscript(profile: String, storedId: String, newId: () -> String): Pair<String, List<ChatItem>>? =
        withContext(Dispatchers.IO) {
            val f = transcriptFile(profile, storedId)?.takeIf { it.isFile } ?: return@withContext null
            val t = runCatching { json.decodeFromString(Transcript.serializer(), f.readText()) }.getOrNull()
                ?: return@withContext null
            t.title to t.rows.mapNotNull { r ->
                val kind = runCatching { ChatKind.valueOf(r.kind) }.getOrNull() ?: return@mapNotNull null
                ChatItem(
                    id = newId(), kind = kind, text = r.text, meta = r.meta, rowId = r.rowId,
                    attachments = r.attachments, reactions = r.reactions, timestamp = r.ts,
                )
            }
        }

    fun forget(profile: String, storedId: String) {
        io.launch { transcriptFile(profile, storedId)?.delete() }
    }

    private fun evict() {
        val dir = root?.let { File(it, "t") } ?: return
        val files = dir.walkTopDown().filter { it.isFile && it.name.endsWith(".json") }.toList()
        if (files.size <= MAX_TRANSCRIPTS) return
        files.sortedBy { it.lastModified() }.take(files.size - MAX_TRANSCRIPTS).forEach { it.delete() }
    }

    // ---- last opened chat per bot ---------------------------------------------

    fun setLastSession(profile: String, storedId: String) {
        val r = root ?: return
        io.launch { runCatching { File(r, "last-${safe(profile)}.txt").writeText(storedId) } }
    }

    suspend fun lastSession(profile: String): String? = withContext(Dispatchers.IO) {
        root?.let { File(it, "last-${safe(profile)}.txt") }?.takeIf { it.isFile }
            ?.readText()?.trim()?.takeIf { it.isNotEmpty() }
    }

    // ---- lists ------------------------------------------------------------------

    fun saveSessions(profile: String, sessions: List<SessionSummary>) {
        val r = root ?: return
        val payload = json.encodeToString(ListSerializer(SessionSummary.serializer()), sessions.take(100))
        io.launch { runCatching { File(r, "sessions-${safe(profile)}.json").writeText(payload) } }
    }

    suspend fun loadSessions(profile: String): List<SessionSummary> = withContext(Dispatchers.IO) {
        val f = root?.let { File(it, "sessions-${safe(profile)}.json") }?.takeIf { it.isFile }
            ?: return@withContext emptyList()
        runCatching { json.decodeFromString(ListSerializer(SessionSummary.serializer()), f.readText()) }
            .getOrDefault(emptyList())
    }

    fun saveProfiles(profiles: List<BotProfile>) {
        val r = root ?: return
        val payload = json.encodeToString(ListSerializer(BotProfile.serializer()), profiles)
        io.launch { runCatching { File(r, "profiles.json").writeText(payload) } }
    }

    suspend fun loadProfiles(): List<BotProfile> = withContext(Dispatchers.IO) {
        val f = root?.let { File(it, "profiles.json") }?.takeIf { it.isFile } ?: return@withContext emptyList()
        runCatching { json.decodeFromString(ListSerializer(BotProfile.serializer()), f.readText()) }
            .getOrDefault(emptyList())
    }

    fun clearAll() {
        val r = root ?: return
        io.launch { r.deleteRecursively(); r.mkdirs() }
    }
}

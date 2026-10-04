package com.cornerstone.agency.data

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.io.File
import java.util.UUID

/**
 * Local-first store for the Feed / Goals / Artifacts tabs.
 *
 * Everything lives under <filesDir>/companion/: one companion.json plus an
 * artifacts/ directory for saved file bytes. The repository surface is
 * deliberately gateway-shaped (addPost, goals CRUD, artifact import) so a
 * future Hermes backend can replace the file implementation without touching
 * the UI.
 */
class CompanionRepository private constructor(private val appContext: Context) {

    companion object {
        @Volatile private var instance: CompanionRepository? = null

        /** One store per process: the ViewModel, the widget and the scheduled worker all share it. */
        fun get(context: Context): CompanionRepository =
            instance ?: synchronized(this) {
                instance ?: CompanionRepository(context.applicationContext).also { instance = it }
            }
    }

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private val json = Json { ignoreUnknownKeys = true; prettyPrint = true }

    private val storeFile: File
        get() = File(appContext.filesDir, "companion/companion.json")
    private val artifactsDir: File
        get() = File(appContext.filesDir, "companion/artifacts")

    private val _state = MutableStateFlow(CompanionState())
    val state: StateFlow<CompanionState> = _state.asStateFlow()

    private val loaded = kotlinx.coroutines.CompletableDeferred<Unit>()

    init {
        scope.launch { load() }
    }

    /** Suspends until the on-disk state has been read (background callers need this). */
    suspend fun awaitLoaded() = loaded.await()

    private suspend fun load() {
        withContext(Dispatchers.IO) {
            val f = storeFile
            if (f.exists()) {
                runCatching {
                    _state.value = json.decodeFromString(CompanionState.serializer(), f.readText())
                }
            }
        }
        loaded.complete(Unit)
        com.cornerstone.agency.service.AgencyWidget.refresh(appContext)
    }

    private fun persist(snapshot: CompanionState) {
        scope.launch(Dispatchers.IO) {
            runCatching {
                storeFile.parentFile?.mkdirs()
                storeFile.writeText(json.encodeToString(CompanionState.serializer(), snapshot))
            }
        }
    }

    private fun update(fn: (CompanionState) -> CompanionState) {
        val prev = _state.value
        val next = fn(prev)
        _state.value = next
        persist(next)
        // The widget shows the unread count; keep it honest without polling.
        if (prev.posts.count { !it.read } != next.posts.count { !it.read }) {
            com.cornerstone.agency.service.AgencyWidget.refresh(appContext)
        }
    }

    // ---- Feed -----------------------------------------------------------

    fun saveFeedInstructions(text: String) =
        update { it.copy(feedInstructions = text) }

    fun setFeedSchedule(schedule: FeedSchedule) =
        update { it.copy(feedSchedule = schedule) }

    fun setFeedBot(profile: String, title: String) {
        if (profile.isBlank()) return
        val s = _state.value
        if (profile != s.feedProfile || (title.isNotBlank() && title != s.feedBotTitle)) {
            update { it.copy(feedProfile = profile, feedBotTitle = title.ifBlank { it.feedBotTitle }) }
        }
    }

    fun markFeedRun(at: Long = System.currentTimeMillis()) =
        update { it.copy(lastFeedRunAt = at) }

    fun addPost(post: FeedPost) = update {
        val withId = if (post.id.isBlank()) post.copy(id = UUID.randomUUID().toString()) else post
        it.copy(posts = listOf(withId) + it.posts)
    }

    /** A fresh batch from the writer: skip titles we already have, newest first, cap history. */
    fun addPosts(posts: List<FeedPost>): Int {
        var added = 0
        update { s ->
            val seen = s.posts.map { it.title.trim().lowercase() }.toHashSet()
            val fresh = posts.filter { p ->
                val k = p.title.trim().lowercase()
                k.isNotEmpty() && seen.add(k)
            }.map { p -> if (p.id.isBlank()) p.copy(id = UUID.randomUUID().toString()) else p }
            added = fresh.size
            s.copy(posts = (fresh + s.posts).take(200))
        }
        return added
    }

    fun markPostRead(id: String) = update {
        it.copy(posts = it.posts.map { p -> if (p.id == id) p.copy(read = true) else p })
    }

    fun markAllPostsRead() = update {
        it.copy(posts = it.posts.map { p -> p.copy(read = true) })
    }

    fun clearPosts() = update { it.copy(posts = emptyList()) }

    // ---- Goals ----------------------------------------------------------

    fun addGoal(title: String, detail: String, category: String) {
        val now = System.currentTimeMillis()
        update {
            it.copy(
                goals = it.goals + Goal(
                    id = UUID.randomUUID().toString(),
                    title = title.trim(),
                    detail = detail.trim(),
                    category = category.ifBlank { "Personal" },
                    createdAt = now,
                ),
            )
        }
    }

    fun toggleGoal(id: String) {
        val now = System.currentTimeMillis()
        update {
            it.copy(
                goals = it.goals.map { g ->
                    if (g.id != id) g
                    else if (g.done) g.copy(done = false, doneAt = 0L)
                    else g.copy(done = true, doneAt = now)
                },
            )
        }
    }

    fun deleteGoal(id: String) = update {
        it.copy(goals = it.goals.filterNot { g -> g.id == id })
    }

    // ---- Artifacts ------------------------------------------------------

    /**
     * Copies [bytes] into the artifacts dir and registers the entry.
     * Returns null when the copy fails.
     */
    suspend fun importArtifact(displayName: String, mimeType: String, bytes: ByteArray): ArtifactEntry? =
        withContext(Dispatchers.IO) {
            runCatching {
                val id = UUID.randomUUID().toString()
                val ext = displayName.substringAfterLast('.', "").takeIf { it.isNotBlank() }
                    ?.let { ".$it" } ?: ""
                artifactsDir.mkdirs()
                val file = File(artifactsDir, "$id$ext")
                file.writeBytes(bytes)
                val entry = ArtifactEntry(
                    id = id,
                    name = displayName.ifBlank { "artifact$ext" },
                    mimeType = mimeType.ifBlank { "application/octet-stream" },
                    size = bytes.size.toLong(),
                    addedAt = System.currentTimeMillis(),
                )
                update { it.copy(artifacts = listOf(entry) + it.artifacts) }
                entry
            }.getOrNull()
        }

    fun deleteArtifact(id: String) {
        update { it.copy(artifacts = it.artifacts.filterNot { a -> a.id == id }) }
        scope.launch(Dispatchers.IO) {
            runCatching {
                artifactsDir.listFiles()
                    ?.filter { it.name.startsWith(id) }
                    ?.forEach { it.delete() }
            }
        }
    }

    /** Local file for an artifact entry, or null if the bytes are gone. */
    fun artifactFile(entry: ArtifactEntry): File? =
        artifactsDir.listFiles()?.firstOrNull { it.name.startsWith(entry.id) }
}

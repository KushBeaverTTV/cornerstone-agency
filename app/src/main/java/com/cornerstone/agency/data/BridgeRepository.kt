package com.cornerstone.agency.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonArray
import kotlinx.serialization.json.putJsonObject
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.concurrent.TimeUnit

@Serializable
data class BridgeHealthPrima(
    val available: Boolean = false,
    val version: String? = null,
    val sessions: Int = 0,
)

@Serializable
data class BridgeHealthLaya(
    val loaded: Boolean = false,
    val checkpoint: String? = null,
    val device: String? = null,
    val present: Boolean = false,
    val error: String? = null,
)

@Serializable
data class BridgeHealth(
    val ok: Boolean = false,
    val service: String = "",
    val version: String = "",
    val prima: BridgeHealthPrima = BridgeHealthPrima(),
    val laya: BridgeHealthLaya = BridgeHealthLaya(),
)

/** A Prima conversation saved on the PC (~/.prime/agent/sessions), from GET /prima/saved. */
@Serializable
data class PrimaSaved(
    val path: String = "",
    val session_id: String = "",
    val title: String = "",
    val preview: String = "",
    val cwd: String = "",
    val message_count: Int = 0,
    val model: String = "",
    val updated_at: Double = 0.0,
    /** Bridge session id when a worker already has it open. */
    val open_id: String? = null,
)

@Serializable
private data class PrimaSavedResponse(val sessions: List<PrimaSaved> = emptyList())

/** GET /app/latest — the newest signed release in the repo's dist/. */
@Serializable
data class AppRelease(val version_name: String = "", val size: Long = 0, val file: String = "") {
    /** True when this release is newer than [current] ("1.3.0" style). */
    fun newerThan(current: String): Boolean {
        fun parts(v: String) = v.substringBefore('-').split('.').map { it.toIntOrNull() ?: 0 } + listOf(0, 0, 0)
        val a = parts(version_name)
        val b = parts(current)
        for (i in 0 until 3) if (a[i] != b[i]) return a[i] > b[i]
        return false
    }
}

/** One PC service on the Me screen, from GET /services. */
@Serializable
data class PcService(
    val name: String = "",
    val label: String = "",
    val port: Int = 0,
    val ok: Boolean = false,
    val pid: Int? = null,
    val restartable: Boolean = false,
)

@Serializable
data class HermesRelayStatus(val backend: String = "", val phones: Int = 0)

@Serializable
data class PcServices(
    val services: List<PcService> = emptyList(),
    val hermes: HermesRelayStatus = HermesRelayStatus(),
    val watchdog: List<String> = emptyList(),
)

@Serializable
data class PrimaSession(
    val id: String = "",
    val name: String = "",
    val cwd: String = "",
    val is_streaming: Boolean = false,
    val message_count: Int = 0,
    val created_at: Double = 0.0,
    val last_activity: Double = 0.0,
    val alive: Boolean = true,
) {
    val label: String
        get() = name.ifBlank { "Session ${id.take(6)}" }
}

/**
 * OkHttp client for the desktop bridge (docs/BRIDGE.md). Bearer auth on every
 * route except /health; host:port + token live in SecureStore.
 */
class BridgeRepository(private val store: SecureStore) {

    private val json = Json { ignoreUnknownKeys = true; coerceInputValues = true }

    var host: String = store.bridgeHost.ifBlank { DEFAULT_HOST }
    var token: String = store.bridgeToken

    val configured: Boolean
        get() = host.isNotBlank() && token.isNotBlank()

    /** Persist current host/token into SecureStore. */
    fun save() {
        store.bridgeHost = host
        store.bridgeToken = token
    }

    internal val client = OkHttpClient.Builder()
        .connectTimeout(8, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS) // laya cold load can take ~1 min
        .build()

    private fun base() = "http://$host"

    fun wsUrl(): String =
        "ws://$host/prima/ws?token=${java.net.URLEncoder.encode(token, "UTF-8")}"

    private suspend fun api(
        path: String,
        method: String = "GET",
        body: String? = null,
        auth: Boolean = true,
    ): String = withContext(Dispatchers.IO) {
        val request = Request.Builder()
            .url("${base()}$path")
            .header("Accept", "application/json")
            .apply {
                if (auth) header("Authorization", "Bearer $token")
                when (method) {
                    "POST" -> post((body ?: "").toRequestBody(JSON_MEDIA))
                    "PUT" -> put((body ?: "").toRequestBody(JSON_MEDIA))
                    "DELETE" -> delete()
                }
            }
            .build()
        client.newCall(request).execute().use { resp ->
            val text = resp.body?.string().orEmpty()
            if (!resp.isSuccessful) {
                val detail = runCatching {
                    (json.parseToJsonElement(text) as? JsonObject)
                        ?.get("detail")
                        ?.let { (it as? kotlinx.serialization.json.JsonPrimitive)?.content ?: it.toString() }
                }.getOrNull() ?: "HTTP ${resp.code}"
                throw ApiException(resp.code, detail)
            }
            text
        }
    }

    // -- endpoints --------------------------------------------------------

    suspend fun health(): BridgeHealth =
        json.decodeFromString(api("/health", auth = false))

    /** Auto-connect: the PC's current address book (GET /pairing, bearer). */
    suspend fun pairing(): PairingResponse = json.decodeFromString(api("/pairing"))

    /**
     * Auto-connect probe: does a bridge at [hostPort] answer `/health` AND accept [token]?
     * `/hermes/status` is the cheapest authenticated route. Uses a short client so a dead host
     * fails fast; the caller adds its own overall timeout.
     */
    suspend fun probe(hostPort: String, token: String): Boolean = withContext(Dispatchers.IO) {
        val req = Request.Builder()
            .url("http://$hostPort/hermes/status")
            .header("Accept", "application/json")
            .header("Authorization", "Bearer $token")
            .build()
        probeClient.newCall(req).execute().use { it.isSuccessful }
    }

    private val probeClient by lazy {
        client.newBuilder().connectTimeout(3, TimeUnit.SECONDS).readTimeout(3, TimeUnit.SECONDS).build()
    }

    suspend fun primaSessions(): List<PrimaSession> =
        json.decodeFromString<PrimaSessionsResponse>(api("/prima/sessions")).sessions

    suspend fun primaSaved(limit: Int = 50): List<PrimaSaved> =
        json.decodeFromString<PrimaSavedResponse>(api("/prima/saved?limit=$limit")).sessions

    suspend fun services(): PcServices = json.decodeFromString(api("/services"))

    suspend fun restartService(name: String) {
        api("/services/$name/restart", "POST", "{}")
    }

    /** Newest release the PC offers (`dist/`), or null when none / older bridge. */
    suspend fun appLatest(): AppRelease? = runCatching {
        json.decodeFromString<AppRelease>(api("/app/latest"))
    }.getOrNull()?.takeIf { it.version_name.isNotBlank() }

    /** Stream the release APK into [dest], reporting 0..1 progress. */
    suspend fun downloadApk(dest: java.io.File, onProgress: (Float) -> Unit) = withContext(Dispatchers.IO) {
        val req = Request.Builder().url("${base()}/app/latest.apk").header("Authorization", "Bearer $token").build()
        client.newCall(req).execute().use { resp ->
            if (!resp.isSuccessful) throw ApiException(resp.code, "download failed (${resp.code})")
            val body = resp.body ?: throw ApiException(500, "empty download")
            val total = body.contentLength().takeIf { it > 0 } ?: -1L
            dest.parentFile?.mkdirs()
            val tmp = java.io.File(dest.parentFile, dest.name + ".part")
            body.byteStream().use { input ->
                tmp.outputStream().use { out ->
                    val buf = ByteArray(64 * 1024)
                    var done = 0L
                    while (true) {
                        val n = input.read(buf)
                        if (n < 0) break
                        out.write(buf, 0, n)
                        done += n
                        if (total > 0) onProgress(done.toFloat() / total)
                    }
                }
            }
            if (!tmp.renameTo(dest)) { dest.delete(); tmp.renameTo(dest) }
        }
    }

    suspend fun createPrimaSession(cwd: String? = null, name: String = "", sessionPath: String? = null): String {
        val body = buildJsonObject {
            if (cwd != null) put("cwd", cwd)
            if (name.isNotBlank()) put("name", name)
            if (sessionPath != null) put("session_path", sessionPath)
        }
        val res = json.parseToJsonElement(api("/prima/sessions", "POST", body.toString()))
        return (res as? JsonObject)?.get("id")
            ?.let { it as? kotlinx.serialization.json.JsonPrimitive }?.content
            ?: throw ApiException(500, "bridge returned no session id")
    }

    /** Raw get_messages passthrough — the bridge returns the worker's data. */
    suspend fun primaMessages(sid: String): JsonArray {
        val res = json.parseToJsonElement(api("/prima/sessions/$sid/messages"))
        val obj = res as? JsonObject ?: return JsonArray(emptyList())
        return (obj["messages"] as? JsonArray) ?: JsonArray(emptyList())
    }

    suspend fun primaPrompt(
        sid: String,
        message: String,
        streamingBehavior: String? = null,
        attachments: List<OutgoingAttachment> = emptyList(),
    ) {
        fun b64(bytes: ByteArray) = android.util.Base64.encodeToString(bytes, android.util.Base64.NO_WRAP)
        val body = buildJsonObject {
            put("message", message)
            if (streamingBehavior != null) put("streaming_behavior", streamingBehavior)
            val images = attachments.filter { it.isImage }
            val files = attachments.filterNot { it.isImage }
            if (images.isNotEmpty()) put("images", kotlinx.serialization.json.buildJsonArray {
                images.forEach { a -> add(buildJsonObject { put("data", b64(a.bytes)); put("mime_type", a.mime) }) }
            })
            if (files.isNotEmpty()) put("files", kotlinx.serialization.json.buildJsonArray {
                files.forEach { a -> add(buildJsonObject { put("name", a.name); put("data", b64(a.bytes)) }) }
            })
        }
        api("/prima/sessions/$sid/prompt", "POST", body.toString())
    }

    suspend fun primaAbort(sid: String) {
        api("/prima/sessions/$sid/abort", "POST", "{}")
    }

    suspend fun primaRename(sid: String, name: String) {
        val body = buildJsonObject { put("name", name) }
        api("/prima/sessions/$sid/name", "POST", body.toString())
    }

    /** Current model + everything this Prima worker can switch to, grouped like Hermes' picker. */
    suspend fun primaModels(sid: String): Pair<Pair<String, String>?, List<ModelProvider>> {
        val res = json.parseToJsonElement(api("/prima/sessions/$sid/models")) as JsonObject
        val cur = (res["current"] as? JsonObject)?.let {
            val p = (it["provider"] as? kotlinx.serialization.json.JsonPrimitive)?.content.orEmpty()
            val id = (it["id"] as? kotlinx.serialization.json.JsonPrimitive)?.content.orEmpty()
            if (id.isBlank()) null else p to id
        }
        val groups = LinkedHashMap<String, MutableList<String>>()
        (res["models"] as? JsonArray)?.forEach { el ->
            val m = el as? JsonObject ?: return@forEach
            val p = (m["provider"] as? kotlinx.serialization.json.JsonPrimitive)?.content.orEmpty()
            val id = (m["id"] as? kotlinx.serialization.json.JsonPrimitive)?.content ?: return@forEach
            groups.getOrPut(p) { mutableListOf() } += id
        }
        val providers = groups.map { (slug, ids) ->
            ModelProvider(
                slug = slug,
                name = slug.replaceFirstChar { it.uppercase() },
                isCurrent = slug == cur?.first,
                totalModels = ids.size,
                models = ids,
            )
        }
        return cur to providers
    }

    suspend fun primaSetModel(sid: String, provider: String, modelId: String) {
        val body = buildJsonObject {
            put("provider", provider)
            put("model_id", modelId)
        }
        api("/prima/sessions/$sid/model", "POST", body.toString())
    }

    suspend fun primaDelete(sid: String) {
        api("/prima/sessions/$sid", "DELETE")
    }

    suspend fun layaStatus(): JsonObject =
        json.parseToJsonElement(api("/laya/status")) as JsonObject

    /** POST /laya/route — returns {bot, probs, confidence}. */
    suspend fun layaRoute(text: String, bots: List<Pair<String, String>>): JsonObject {
        val body = buildJsonObject {
            put("text", text)
            putJsonArray("bots") {
                bots.forEach { (name, desc) ->
                    add(buildJsonObject {
                        put("name", name)
                        put("description", desc)
                    })
                }
            }
        }
        return json.parseToJsonElement(api("/laya/route", "POST", body.toString())) as JsonObject
    }

    @Serializable
    private data class PrimaSessionsResponse(val sessions: List<PrimaSession> = emptyList())

    companion object {
        const val DEFAULT_HOST = "127.0.0.1:9121"
        private val JSON_MEDIA = "application/json".toMediaType()
    }
}

package com.cornerstone.agency.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.concurrent.TimeUnit

class ApiException(val status: Int, message: String) : Exception(message)

/**
 * OkHttp client for the Hermes dashboard ("hermes serve") REST surface.
 * Cookie-session auth: POST /auth/password-login sets hermes_session_* cookies;
 * the jar persists them into SecureStore so the session survives restarts.
 * Any 401 triggers one re-login with stored credentials, then one retry.
 */
class ConnectionRepository(private val store: SecureStore) {

    private val json = Json { ignoreUnknownKeys = true }

    var baseUrl: String = ""
        private set
    var username: String = ""
        private set
    private var password: String = ""

    /** In-memory cookie jar mirrored to SecureStore on every Set-Cookie. */
    private inner class SessionCookieJar : CookieJar {
        private val cookies = LinkedHashMap<String, Cookie>()

        @Synchronized
        fun seed(cookieHeader: String, host: String) {
            cookies.clear()
            for (pair in cookieHeader.split(";")) {
                val kv = pair.trim()
                if (kv.isEmpty() || '=' !in kv) continue
                val name = kv.substringBefore('=').trim()
                val value = kv.substringAfter('=').trim()
                cookies[name] = Cookie.Builder()
                    .name(name).value(value)
                    .domain(host).path("/")
                    .build()
            }
        }

        @Synchronized
        fun snapshotHeader(): String =
            cookies.values.joinToString("; ") { "${it.name}=${it.value}" }

        @Synchronized
        override fun saveFromResponse(url: HttpUrl, newCookies: List<Cookie>) {
            var touched = false
            for (c in newCookies) {
                if (!c.name.startsWith("hermes_session_")) continue
                if (c.value.isEmpty()) cookies.remove(c.name) else cookies[c.name] = c
                touched = true
            }
            if (touched) store.saveCookie(snapshotHeader())
        }

        @Synchronized
        override fun loadForRequest(url: HttpUrl): List<Cookie> {
            val now = System.currentTimeMillis()
            return cookies.values.filter { it.expiresAt > now && it.matches(url) }
        }
    }

    private val jar = SessionCookieJar()

    private val client = OkHttpClient.Builder()
        .cookieJar(jar)
        .connectTimeout(8, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    /** Restore a persisted connection. Returns false if nothing was stored. */
    fun restore(): Boolean {
        val conn = store.load() ?: return false
        attach(conn)
        return true
    }

    private fun attach(conn: SecureStore.Connection) {
        baseUrl = conn.baseUrl
        username = conn.username
        password = conn.password
        val host = runCatching { conn.baseUrl.toHttpUrl().host }.getOrDefault("")
        if (conn.cookie.isNotBlank() && host.isNotEmpty()) {
            jar.seed(conn.cookie, host)
        }
    }

    fun disconnect() {
        store.clear()
        baseUrl = ""
        username = ""
        password = ""
    }

    /** Debug/dev seed: attach a pre-minted session cookie without a password login. */
    fun seedConnection(url: String, user: String, cookie: String) {
        val conn = SecureStore.Connection(normalizeBaseUrl(url), user, "", cookie)
        attach(conn)
        store.save(conn)
    }

    // ---- HTTP -----------------------------------------------------------

    private suspend fun execute(path: String, method: String = "GET", body: String? = null): String =
        withContext(Dispatchers.IO) {
            val request = Request.Builder()
                .url("$baseUrl$path")
                .header("Accept", "application/json")
                .apply {
                    when (method) {
                        "POST" -> post((body ?: "").toRequestBody(JSON_MEDIA))
                        "PUT" -> put((body ?: "").toRequestBody(JSON_MEDIA))
                        "DELETE" -> delete()
                    }
                }
                .build()
            client.newCall(request).execute().use { resp ->
                val text = resp.body?.string().orEmpty()
                if (resp.code == 401) throw ApiException(401, "session expired")
                if (!resp.isSuccessful) throw ApiException(resp.code, errorDetail(text, resp.code))
                text
            }
        }

    /** api() with a single 401 → re-login → retry cycle, mirroring Herald's client. */
    private suspend fun api(path: String, method: String = "GET", body: String? = null): String {
        try {
            return execute(path, method, body)
        } catch (e: ApiException) {
            if (e.status != 401 || password.isEmpty()) throw e
        }
        // Session lapsed — one silent re-login with stored creds, then retry once.
        loginRaw(username, password)
        return execute(path, method, body)
    }

    private fun errorDetail(text: String, code: Int): String {
        val parsed = runCatching { json.decodeFromString<ApiErrorBody>(text) }.getOrNull()
        return parsed?.detail ?: parsed?.error ?: "HTTP $code"
    }

    // ---- endpoints ------------------------------------------------------

    suspend fun health(url: String): Health = withContext(Dispatchers.IO) {
        val request = Request.Builder()
            .url("${normalizeBaseUrl(url)}/api/health")
            .header("Accept", "application/json")
            .build()
        client.newCall(request).execute().use { resp ->
            if (!resp.isSuccessful) throw ApiException(resp.code, "health check failed (${resp.code})")
            json.decodeFromString(resp.body?.string().orEmpty())
        }
    }

    suspend fun authProviders(): List<AuthProviderInfo> =
        json.decodeFromString<ProvidersResponse>(api("/api/auth/providers")).providers

    /** Probe health and attach the base URL so subsequent calls share the jar. */
    suspend fun probe(url: String): Health {
        val normalized = normalizeBaseUrl(url)
        val h = health(normalized)
        baseUrl = normalized
        return h
    }

    private suspend fun loginRaw(user: String, pass: String) = withContext(Dispatchers.IO) {
        val payload = json.encodeToString(PasswordLoginBody("basic", user, pass))
        val request = Request.Builder()
            .url("$baseUrl/auth/password-login")
            .header("Accept", "application/json")
            .post(payload.toRequestBody(JSON_MEDIA))
            .build()
        client.newCall(request).execute().use { resp ->
            val text = resp.body?.string().orEmpty()
            if (!resp.isSuccessful) throw ApiException(resp.code, errorDetail(text, resp.code))
        }
    }

    suspend fun login(user: String, pass: String): SessionInfo {
        loginRaw(user, pass)
        val me = me()
        username = me.userId.ifBlank { user }
        password = pass
        store.save(SecureStore.Connection(baseUrl, username, password, jar.snapshotHeader()))
        return me
    }

    suspend fun me(): SessionInfo = json.decodeFromString(api("/api/auth/me"))

    suspend fun profiles(): List<BotProfile> =
        json.decodeFromString<ProfilesResponse>(api("/api/profiles")).profiles

    suspend fun status(): StatusPayload = json.decodeFromString(api("/api/status"))

    companion object {
        private val JSON_MEDIA = "application/json".toMediaType()

        fun normalizeBaseUrl(input: String): String {
            var s = input.trim()
            require(s.isNotEmpty()) { "Address is required" }
            if (!s.startsWith("http://") && !s.startsWith("https://") &&
                !s.startsWith("ws://") && !s.startsWith("wss://")
            ) {
                s = "http://$s"
            }
            // ws(s):// → http(s)://, then strip trailing slashes.
            s = s.replace(Regex("^ws", RegexOption.IGNORE_CASE), "http").trimEnd('/')
            val parsed = runCatching { s.toHttpUrl() }.getOrNull()
            require(parsed != null && parsed.host.isNotEmpty()) { "Invalid address" }
            return "${parsed.scheme}://${parsed.host}${if (parsed.port != 80 && parsed.port != 443) ":${parsed.port}" else ""}"
        }
    }
}

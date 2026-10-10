package com.cornerstone.agency.data

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withTimeoutOrNull
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

/**
 * Everything the phone needs to find and trust Kush's PC without a human in the loop.
 *
 * Two sources, merged by [AutoConnect.candidates]:
 *  - **Baked** at build time from `Cornerstone_Secrets/agency-pairing.env` (BuildConfig). A fresh
 *    install already knows the PC.
 *  - **Learned** from the bridge (`GET /pairing`) after every successful connection and kept in
 *    SecureStore. The freshest address book wins, so a tailnet address learned over the USB cable
 *    works later on cellular with no rebuild.
 *
 * `hosts` are `host:port` of the bridge, best first (USB loopback, then tailnet).
 */
@Serializable
data class KnownPc(
    val name: String = "",
    val hosts: List<String> = emptyList(),
    val token: String = "",
    val voicePort: Int = 8766,
    val voiceToken: String = "",
) {
    val usable: Boolean get() = hosts.isNotEmpty() && token.length >= 16

    /** The pairing the existing QR path understands, for one chosen bridge host. */
    fun linkFor(bridgeHost: String): PairingLink = PairingLink(
        bridge = bridgeHost,
        token = token,
        voice = "${bridgeHost.substringBeforeLast(':')}:$voicePort",
        voiceToken = voiceToken,
        name = name,
    )

    companion object {
        private val json = Json { ignoreUnknownKeys = true; coerceInputValues = true }
        private val HOST = Regex("^[A-Za-z0-9.\\-]+:\\d{2,5}$")

        fun fromJson(raw: String): KnownPc? =
            runCatching { json.decodeFromString<KnownPc>(raw) }.getOrNull()?.takeIf { it.usable }

        fun toJson(pc: KnownPc): String = json.encodeToString(serializer(), pc)

        /** From BuildConfig-style strings; `hosts` is comma-separated `host:port`. */
        fun fromBaked(name: String, hosts: String, token: String, voicePort: String, voiceToken: String): KnownPc? {
            val list = hosts.split(',').map { it.trim() }.filter { HOST.matches(it) }
            return KnownPc(
                name = name.trim().take(40),
                hosts = list,
                token = token.trim(),
                voicePort = voicePort.trim().toIntOrNull() ?: 8766,
                voiceToken = voiceToken.trim(),
            ).takeIf { it.usable }
        }

        /** From the bridge's `GET /pairing` body. */
        fun fromPairingResponse(body: PairingResponse): KnownPc? = KnownPc(
            name = body.name.take(40),
            hosts = body.hosts.map { "$it:${body.bridge_port}" }.filter { HOST.matches(it) },
            token = body.token,
            voicePort = body.voice_port,
            voiceToken = body.voice_token,
        ).takeIf { it.usable }
    }
}

/** Wire shape of `GET /pairing` on the bridge. */
@Serializable
data class PairingResponse(
    val v: Int = 1,
    val name: String = "",
    val hosts: List<String> = emptyList(),
    val bridge_port: Int = 9121,
    val token: String = "",
    val voice_port: Int = 8766,
    val voice_token: String = "",
)

object AutoConnect {
    const val PROBE_TIMEOUT_MS = 4_000L

    /** Why the app is (re)connecting — for logs and the Me screen. */
    enum class Reason { COLD_START, FOREGROUND, SOCKET_DOWN, USER }

    /**
     * Hosts to try, best first: the learned book (freshest) with the baked one as a fallback,
     * de-duplicated. The learned list only counts when it was issued with the same token the
     * baked build carries, or when there is no baked build at all — a stale learned token from a
     * previous PC would otherwise shadow a fresh build forever.
     */
    fun candidates(learned: KnownPc?, baked: KnownPc?): List<Pair<String, KnownPc>> {
        val out = ArrayList<Pair<String, KnownPc>>()
        val seen = HashSet<String>()
        fun add(pc: KnownPc?) {
            pc ?: return
            for (h in pc.hosts) if (seen.add(h)) out += h to pc
        }
        val learnedOk = learned != null && (baked == null || learned.token == baked.token)
        if (learnedOk) add(learned)
        add(baked)
        if (!learnedOk) add(learned)
        return out
    }

    /**
     * Probe every host at once and return the reachable one with the best priority (list order),
     * or null. A host that takes longer than [timeoutMs] counts as down: on a phone with the USB
     * cable unplugged, 127.0.0.1 refuses instantly, and a tailnet peer that is asleep must not
     * hold the app for a minute.
     */
    suspend fun <T> pickReachable(
        candidates: List<T>,
        timeoutMs: Long = PROBE_TIMEOUT_MS,
        probe: suspend (T) -> Boolean,
    ): T? = coroutineScope {
        val jobs: List<Deferred<Boolean>> = candidates.map { c ->
            async { withTimeoutOrNull(timeoutMs) { runCatching { probe(c) }.getOrDefault(false) } ?: false }
        }
        // Priority order: the first winner in list order, but stop waiting on the rest once the
        // best-ranked candidate answers.
        for ((i, job) in jobs.withIndex()) {
            if (job.await()) {
                jobs.drop(i + 1).forEach { it.cancel() }
                return@coroutineScope candidates[i]
            }
        }
        null
    }

    /** "USB" for the loopback cable, otherwise "Tailscale". */
    fun pathLabel(bridgeHost: String): String =
        if (bridgeHost.startsWith("127.0.0.1") || bridgeHost.startsWith("localhost")) "USB" else "Tailscale"
}

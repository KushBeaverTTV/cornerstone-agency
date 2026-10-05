package com.cornerstone.agency.data

import java.net.URI
import java.net.URLDecoder

/**
 * A pairing link from `agency-bridge pair` (shown as a QR on the PC):
 * `cornerstone-agency://pair?v=1&bridge=<host:port>&token=<t>&voice=<host:port>&voice_token=<t>&name=<pc>`.
 * Scanning it with the phone camera opens the app straight into pairing.
 */
data class PairingLink(
    val bridge: String,
    val token: String,
    val voice: String = "",
    val voiceToken: String = "",
    val name: String = "",
) {
    companion object {
        const val SCHEME = "cornerstone-agency"

        fun parse(raw: String): PairingLink? {
            val uri = runCatching { URI(raw.trim()) }.getOrNull() ?: return null
            if (!uri.scheme.equals(SCHEME, true) || !uri.host.equals("pair", true)) return null
            val q = (uri.rawQuery ?: return null).split('&').mapNotNull {
                val k = it.substringBefore('=', "")
                if (k.isEmpty()) null else k to URLDecoder.decode(it.substringAfter('=', ""), "UTF-8")
            }.toMap()
            val bridge = q["bridge"].orEmpty()
            val token = q["token"].orEmpty()
            if (!Regex("^[A-Za-z0-9.\\-]+:\\d{2,5}$").matches(bridge) || token.length < 16) return null
            val voice = q["voice"].orEmpty().takeIf { Regex("^[A-Za-z0-9.\\-]+:\\d{2,5}$").matches(it) }.orEmpty()
            return PairingLink(bridge, token, voice, q["voice_token"].orEmpty(), q["name"].orEmpty().take(40))
        }
    }
}

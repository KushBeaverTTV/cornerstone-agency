package com.cornerstone.agency.data

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

private val SESSIONS_JSON = Json { ignoreUnknownKeys = true }

/** session.list for one profile — the server scopes the listing per profile DB. */
internal suspend fun listProfileSessions(
    gateway: GatewayLink,
    profile: String,
    limit: Int = 200,
    includeHidden: Boolean = false,
): List<SessionSummary> {
    val res = gateway.call(
        "session.list",
        buildJsonObject {
            put("profile", profile)
            put("limit", limit)
            if (includeHidden) put("include_hidden", true)
        },
    )
    // The server already orders rows by LAST ACTIVITY (newest first). Re-sorting by start time
    // made the phone open (and list first) the newest-STARTED chat, not the one the user was
    // actually in on the desktop.
    return runCatching {
        SESSIONS_JSON.decodeFromJsonElement(SessionListResult.serializer(), res)
    }.getOrNull()?.sessions ?: emptyList()
}

/**
 * Cross-bot recency for a list that is already in last-activity order. Rows carry only their
 * start time, but a row can never be less recently active than any row listed after it, so the
 * largest start time at-or-below each row is a safe, monotone stand-in for "last active".
 */
internal fun List<SessionSummary>.activityScores(): List<Pair<SessionSummary, Double>> {
    var floor = 0.0
    val out = ArrayList<Pair<SessionSummary, Double>>(size)
    for (s in asReversed()) {
        floor = maxOf(floor, s.activeAt)
        out += s to floor
    }
    out.reverse()
    return out
}

internal suspend fun deleteStoredSession(gateway: GatewayLink, storedId: String, profile: String? = null) {
    gateway.call(
        "session.delete",
        buildJsonObject {
            put("session_id", storedId)
            if (!profile.isNullOrBlank()) put("profile", profile)
        },
    )
}

/** Hide (or unhide) a stored session: it leaves every list but stays resumable. */
internal suspend fun hideStoredSession(gateway: GatewayLink, storedId: String, profile: String?, hidden: Boolean = true) {
    gateway.call(
        "session.set_hidden",
        buildJsonObject {
            put("session_id", storedId)
            put("hidden", hidden)
            if (!profile.isNullOrBlank()) put("profile", profile)
        },
    )
}

/**
 * Delete a session the user no longer wants. The gateway refuses to delete one that is still
 * live (code 4023), so in that case fall back to hiding it — gone from view either way.
 * Returns true when it was deleted, false when it was only hidden.
 */
internal suspend fun removeStoredSession(gateway: GatewayLink, storedId: String, profile: String?): Boolean {
    try {
        deleteStoredSession(gateway, storedId, profile)
        return true
    } catch (e: GatewayRpcException) {
        if (e.code != 4023) throw e
        hideStoredSession(gateway, storedId, profile)
        return false
    }
}

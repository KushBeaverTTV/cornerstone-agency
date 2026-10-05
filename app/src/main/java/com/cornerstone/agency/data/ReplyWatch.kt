package com.cornerstone.agency.data

import android.content.Context
import android.content.SharedPreferences
import org.json.JSONObject

/**
 * Chats waiting on a reply to something sent from the phone. If the app is backgrounded and its
 * socket dies (Doze, the user swiped it away, the FGS hit its limit), the catch-up worker checks
 * these sessions over REST and notifies when the reply has landed. Entries are dropped once the
 * reply is shown or notified, or after [MAX_AGE_MS].
 */
object ReplyWatch {
    const val MAX_AGE_MS = 6 * 60 * 60 * 1000L

    data class Entry(val storedId: String, val profile: String, val afterRow: Long, val sentAt: Long)

    @Volatile
    private var prefs: SharedPreferences? = null

    fun init(context: Context) {
        if (prefs == null) prefs = context.applicationContext.getSharedPreferences("reply-watch", Context.MODE_PRIVATE)
    }

    fun expect(profile: String, storedId: String, afterRow: Long) {
        val p = prefs ?: return
        val v = JSONObject().put("profile", profile).put("after", afterRow).put("at", System.currentTimeMillis())
        p.edit().putString(storedId, v.toString()).apply()
    }

    fun clear(storedId: String?) {
        if (storedId.isNullOrEmpty()) return
        prefs?.edit()?.remove(storedId)?.apply()
    }

    fun all(now: Long = System.currentTimeMillis()): List<Entry> {
        val p = prefs ?: return emptyList()
        val out = ArrayList<Entry>()
        val stale = ArrayList<String>()
        for ((k, v) in p.all) {
            val o = runCatching { JSONObject(v as String) }.getOrNull()
            if (o == null || now - o.optLong("at") > MAX_AGE_MS) {
                stale += k
                continue
            }
            out += Entry(k, o.optString("profile", "default"), o.optLong("after"), o.optLong("at"))
        }
        if (stale.isNotEmpty()) p.edit().apply { stale.forEach { remove(it) } }.apply()
        return out
    }

    fun isEmpty(): Boolean = prefs?.all?.isEmpty() ?: true

    /**
     * The finished reply in [rows] (state-DB rows, oldest first) after [afterRow], or null while
     * the turn is still running (the newest visible row is not an assistant answer yet).
     */
    fun landedReply(rows: List<kotlinx.serialization.json.JsonObject>, afterRow: Long): String? {
        var n = 0
        val items = TranscriptMapping.fromDbRows(rows, { "w${++n}" })
        val last = items.lastOrNull() ?: return null
        if (last.kind != ChatKind.Assistant || last.text.isBlank()) return null
        return if ((last.rowId ?: 0) > afterRow) last.text else null
    }
}

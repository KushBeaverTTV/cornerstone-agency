package com.cornerstone.agency.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.prefsStore by preferencesDataStore(name = "agency_prefs")

/** Small user-facing preferences (persisted via DataStore). */
class Prefs(private val app: Context) {

    /** Hide cron-job session chatter on the Chats tab. */
    val hideCronChatter: Flow<Boolean> =
        app.prefsStore.data.map { it[HIDE_CRON_CHATTER] ?: false }

    suspend fun setHideCronChatter(hide: Boolean) {
        app.prefsStore.edit { it[HIDE_CRON_CHATTER] = hide }
    }

    /** Keep the gateway socket + process alive while backgrounded (foreground service). */
    val stayConnected: Flow<Boolean> =
        app.prefsStore.data.map { it[STAY_CONNECTED] ?: false }

    suspend fun setStayConnected(stay: Boolean) {
        app.prefsStore.edit { it[STAY_CONNECTED] = stay }
    }

    /** Last share-intent target profile (pinned on top of the share sheet). */
    val lastShareProfile: Flow<String> =
        app.prefsStore.data.map { it[LAST_SHARE_PROFILE] ?: "" }

    suspend fun setLastShareProfile(profile: String) {
        app.prefsStore.edit { it[LAST_SHARE_PROFILE] = profile }
    }

    /** Voice bridge address, "host:port" (needs adb reverse over USB). */
    val voiceBridge: Flow<String> =
        app.prefsStore.data.map { it[VOICE_BRIDGE] ?: "127.0.0.1:8766" }

    suspend fun setVoiceBridge(addr: String) {
        app.prefsStore.edit { it[VOICE_BRIDGE] = addr }
    }

    private companion object {
        val HIDE_CRON_CHATTER = booleanPreferencesKey("hide_cron_chatter")
        val STAY_CONNECTED = booleanPreferencesKey("stay_connected")
        val LAST_SHARE_PROFILE = stringPreferencesKey("last_share_profile")
        val VOICE_BRIDGE = stringPreferencesKey("voice_bridge")
    }
}

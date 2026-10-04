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

    /** Voice bridge bearer token (HERMES_VOICE_BRIDGE_TOKEN on the host). */
    val voiceToken: Flow<String> =
        app.prefsStore.data.map { it[VOICE_TOKEN] ?: "" }

    suspend fun setVoiceToken(token: String) {
        app.prefsStore.edit { it[VOICE_TOKEN] = token }
    }

    private companion object {
        val STAY_CONNECTED = booleanPreferencesKey("stay_connected")
        val LAST_SHARE_PROFILE = stringPreferencesKey("last_share_profile")
        val VOICE_BRIDGE = stringPreferencesKey("voice_bridge")
        val VOICE_TOKEN = stringPreferencesKey("voice_token")
    }
}

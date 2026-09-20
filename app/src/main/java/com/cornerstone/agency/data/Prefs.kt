package com.cornerstone.agency.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
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

    private companion object {
        val HIDE_CRON_CHATTER = booleanPreferencesKey("hide_cron_chatter")
    }
}

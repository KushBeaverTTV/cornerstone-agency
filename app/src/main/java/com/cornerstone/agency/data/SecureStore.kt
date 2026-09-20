package com.cornerstone.agency.data

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

/** Connection secrets (base URL, credentials, session cookie) in EncryptedSharedPreferences. */
class SecureStore(context: Context) {

    data class Connection(
        val baseUrl: String,
        val username: String,
        val password: String,
        val cookie: String,
    )

    private val prefs: SharedPreferences = EncryptedSharedPreferences.create(
        context,
        FILE,
        MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build(),
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
    )

    fun load(): Connection? {
        val url = prefs.getString(KEY_URL, null)?.takeIf { it.isNotBlank() } ?: return null
        return Connection(
            baseUrl = url,
            username = prefs.getString(KEY_USER, "") ?: "",
            password = prefs.getString(KEY_PASSWORD, "") ?: "",
            cookie = prefs.getString(KEY_COOKIE, "") ?: "",
        )
    }

    fun save(conn: Connection) {
        prefs.edit()
            .putString(KEY_URL, conn.baseUrl)
            .putString(KEY_USER, conn.username)
            .putString(KEY_PASSWORD, conn.password)
            .putString(KEY_COOKIE, conn.cookie)
            .apply()
    }

    fun saveCookie(cookie: String) {
        prefs.edit().putString(KEY_COOKIE, cookie).apply()
    }

    fun clear() {
        prefs.edit().clear().apply()
    }

    companion object {
        private const val FILE = "agency_connection"
        private const val KEY_URL = "base_url"
        private const val KEY_USER = "username"
        private const val KEY_PASSWORD = "password"
        private const val KEY_COOKIE = "cookie"
    }
}

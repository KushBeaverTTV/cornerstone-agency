package com.cornerstone.agency

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.cornerstone.agency.service.NotificationCenter
import com.cornerstone.agency.theme.AgencyTheme
import com.cornerstone.agency.ui.AppNav
import com.cornerstone.agency.ui.AppViewModel

class MainActivity : ComponentActivity() {

    private lateinit var vm: AppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        com.cornerstone.agency.data.ChatCache.init(this)
        enableEdgeToEdge(
            statusBarStyle = androidx.activity.SystemBarStyle.dark(android.graphics.Color.TRANSPARENT),
            navigationBarStyle = androidx.activity.SystemBarStyle.dark(android.graphics.Color.TRANSPARENT),
        )
        // The app is dark-only: always request LIGHT system-bar icons (the OS default flips
        // them dark when the phone itself is in light mode, which made the clock vanish).
        androidx.core.view.WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = false
            isAppearanceLightNavigationBars = false
        }
        vm = ViewModelProvider(this)[AppViewModel::class.java]

        // Debug-only dev seed: `am start --es seed_url <u> --es seed_user <u> --es seed_cookie <c>`
        // attaches a pre-minted dashboard session without a password round-trip.
        if (BuildConfig.DEBUG) {
            intent?.getStringExtra("seed_url")?.takeIf { it.isNotBlank() }?.let { url ->
                vm.seedConnection(
                    url = url,
                    user = intent.getStringExtra("seed_user") ?: "",
                    cookie = intent.getStringExtra("seed_cookie") ?: "",
                )
            }
        }
        handleWidget(intent)
        handleDeepLink(intent)
        handleShare(intent)
        if (BuildConfig.DEBUG && intent?.getBooleanExtra("debug_inbox", false) == true) {
            com.cornerstone.agency.service.NotificationCenter.debugInject()
        }

        setContent {
            AgencyTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AppNav(vm)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        applyDarkBars()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) applyDarkBars()
    }

    /** Dark-only app: light status/nav icons no matter the phone's own light/dark setting. */
    private fun applyDarkBars() {
        androidx.core.view.WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = false
            isAppearanceLightNavigationBars = false
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleWidget(intent)
        handleDeepLink(intent)
        handleShare(intent)
    }

    /** Widget taps: open the default bot's latest chat, optionally straight into voice. */
    private fun handleWidget(intent: Intent?) {
        if (intent?.action != com.cornerstone.agency.service.AgencyWidget.ACTION_WIDGET) return
        if (intent.getBooleanExtra(com.cornerstone.agency.service.AgencyWidget.EXTRA_VOICE, false)) {
            vm.requestVoice()
        }
        vm.requestDeepLink("default", null)
        // Consume it so a rotation / process restore does not replay the tap.
        intent.action = null
    }

    /** Notification taps carry PROFILE/SESSION extras → AppNav navigates; TAB → AppShell switches. */
    private fun handleDeepLink(intent: Intent?) {
        intent?.getStringExtra(NotificationCenter.EXTRA_TAB)?.takeIf { it.isNotBlank() }?.let { tab ->
            vm.requestTab(tab)
            intent.removeExtra(NotificationCenter.EXTRA_TAB)
        }
        val profile = intent?.getStringExtra(NotificationCenter.EXTRA_PROFILE)
            ?.takeIf { it.isNotBlank() } ?: return
        vm.requestDeepLink(
            profile,
            intent.getStringExtra(NotificationCenter.EXTRA_SESSION),
        )
    }

    /**
     * ACTION_SEND / ACTION_SEND_MULTIPLE → ShareTargetScreen via the VM.
     * Text goes through verbatim; images become [image: name] markers (the
     * dashboard socket has no artifact upload path).
     */
    private fun handleShare(intent: Intent?) {
        intent ?: return
        val text = intent.getStringExtra(Intent.EXTRA_TEXT).orEmpty()
        val images = mutableListOf<String>()
        when (intent.action) {
            Intent.ACTION_SEND -> {
                androidx.core.content.IntentCompat.getParcelableExtra(intent, Intent.EXTRA_STREAM, android.net.Uri::class.java)
                    ?.let { images += shareName(it) }
            }
            Intent.ACTION_SEND_MULTIPLE -> {
                androidx.core.content.IntentCompat.getParcelableArrayListExtra(intent, Intent.EXTRA_STREAM, android.net.Uri::class.java)
                    ?.forEach { images += shareName(it) }
            }
            else -> return
        }
        if (text.isBlank() && images.isEmpty()) return
        vm.requestShare(text, images)
    }

    private fun shareName(uri: android.net.Uri): String {
        val cursor = contentResolver.query(uri, null, null, null, null)
        cursor?.use {
            val col = it.getColumnIndex(android.provider.OpenableColumns.DISPLAY_NAME)
            if (col >= 0 && it.moveToFirst()) {
                it.getString(col)?.let { n -> return n }
            }
        }
        return uri.lastPathSegment ?: "attachment"
    }
}

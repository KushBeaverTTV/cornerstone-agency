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
        enableEdgeToEdge()
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
        handleDeepLink(intent)
        handleShare(intent)

        setContent {
            AgencyTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AppNav(vm)
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleDeepLink(intent)
        handleShare(intent)
    }

    /** Notification taps carry PROFILE/SESSION extras → AppNav navigates. */
    private fun handleDeepLink(intent: Intent?) {
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
                intent.getParcelableExtra<android.net.Uri>(Intent.EXTRA_STREAM)
                    ?.let { images += shareName(it) }
            }
            Intent.ACTION_SEND_MULTIPLE -> {
                intent.getParcelableArrayListExtra<android.net.Uri>(Intent.EXTRA_STREAM)
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

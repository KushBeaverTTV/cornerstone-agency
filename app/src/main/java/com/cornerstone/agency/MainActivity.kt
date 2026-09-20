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
}

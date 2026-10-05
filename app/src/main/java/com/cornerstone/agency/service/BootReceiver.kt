package com.cornerstone.agency.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.cornerstone.agency.data.Prefs
import com.cornerstone.agency.data.SecureStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

/**
 * After a reboot or an app update, bring back "Stay connected" (if it was on) and the reply
 * catch-up schedule, so background delivery does not silently stop until the app is opened.
 */
class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != Intent.ACTION_BOOT_COMPLETED && intent.action != Intent.ACTION_MY_PACKAGE_REPLACED) return
        val app = context.applicationContext
        if (SecureStore(app).load() == null) return // not signed in
        CatchUpWorker.schedule(app)
        val pending = goAsync()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                if (Prefs(app).stayConnected.first()) {
                    // remoteMessaging may start from BOOT_COMPLETED (dataSync may not, on 15+).
                    runCatching { ContextCompat.startForegroundService(app, ConnectionService.intent(app)) }
                }
            } finally {
                pending.finish()
            }
        }
    }
}

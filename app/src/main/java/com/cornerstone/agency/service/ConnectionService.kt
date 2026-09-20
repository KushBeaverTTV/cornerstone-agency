package com.cornerstone.agency.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo
import android.os.Build
import android.os.IBinder
import androidx.core.app.ServiceCompat
import com.cornerstone.agency.data.GatewayHub
import com.cornerstone.agency.data.Prefs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

/**
 * Foreground keep-alive for the gateway socket ("Stay connected in background").
 *
 * Deliberately thin: the socket lives in GatewayHub (process scope); this
 * service only keeps the process at foreground priority and hosts the
 * persistent notification. ACTION_DISCONNECT drops the socket, stops the
 * service, and clears the stay-connected preference.
 */
class ConnectionService : Service() {

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.action == ACTION_DISCONNECT) {
            GatewayHub.socket?.disconnect()
            NotificationCenter.cancelAll(this)
            serviceScope.launch {
                Prefs(applicationContext).setStayConnected(false)
                stopSelf()
            }
            return START_NOT_STICKY
        }
        NotificationCenter.ensureChannels(this)
        ServiceCompat.startForeground(
            this,
            NotificationCenter.CONN_NOTIFICATION_ID,
            NotificationCenter.connectionNotification(this),
            if (Build.VERSION.SDK_INT >= 29)
                ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC else 0,
        )
        return START_STICKY
    }

    override fun onDestroy() {
        serviceScope.cancel()
        super.onDestroy()
    }

    companion object {
        const val ACTION_DISCONNECT = "com.cornerstone.agency.action.DISCONNECT"

        fun intent(context: Context, action: String? = null): Intent =
            Intent(context, ConnectionService::class.java).apply { this.action = action }
    }
}

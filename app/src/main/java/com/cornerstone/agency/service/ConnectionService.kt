package com.cornerstone.agency.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo
import android.os.Build
import android.os.IBinder
import android.util.Log
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
 *
 * Service type: on Android 14+ it runs as `remoteMessaging` (continuing chats that live on
 * another device — exactly this app), which has no daily cap. Android 15 limits `dataSync` to
 * 6 hours per day and kills the app if the service does not stop in time; that type is only
 * used below Android 14, where no cap exists. [onTimeout] still stops cleanly if the system
 * ever ends the service, and [CatchUpWorker] covers replies while the socket is down.
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
        ensureSocket()
        ServiceCompat.startForeground(
            this,
            NotificationCenter.CONN_NOTIFICATION_ID,
            NotificationCenter.connectionNotification(this),
            serviceType(),
        )
        return START_STICKY
    }

    /**
     * Started without the app (after a reboot / update): there is no ViewModel to own the socket,
     * so bring it up here from the saved sign-in.
     */
    private fun ensureSocket() {
        if (GatewayHub.socket != null) return
        val repo = com.cornerstone.agency.data.ConnectionRepository(com.cornerstone.agency.data.SecureStore(applicationContext))
        if (!repo.restore()) return
        GatewayHub.foregrounded = false
        val socket = GatewayHub.attach(repo)
        NotificationCenter.bind(applicationContext, socket)
        socket.connect()
    }

    /** Android 15+: the system ended this service type's time budget. Stop now (or crash). */
    override fun onTimeout(startId: Int, fgsType: Int) {
        Log.w(TAG, "foreground service timed out (type $fgsType); relying on catch-up worker")
        CatchUpWorker.schedule(this)
        stopSelf(startId)
    }

    override fun onDestroy() {
        serviceScope.cancel()
        super.onDestroy()
    }

    companion object {
        private const val TAG = "ConnectionService"
        const val ACTION_DISCONNECT = "com.cornerstone.agency.action.DISCONNECT"

        fun intent(context: Context, action: String? = null): Intent =
            Intent(context, ConnectionService::class.java).apply { this.action = action }

        fun serviceType(): Int = when {
            Build.VERSION.SDK_INT >= 34 -> ServiceInfo.FOREGROUND_SERVICE_TYPE_REMOTE_MESSAGING
            Build.VERSION.SDK_INT >= 29 -> ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC
            else -> 0
        }
    }
}

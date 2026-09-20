package com.cornerstone.agency.service

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.cornerstone.agency.data.GatewayHub
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

/**
 * Approval actions on heads-up notifications ("Allow once" / "Deny").
 * Answers the live srq frame through the hub socket — the exact same path a
 * request card uses — then cancels the notification. If the socket is gone
 * (process restarted), the tap extra still routes the user into the chat.
 */
class RequestActionReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != ACTION_RESPOND) return
        val key = intent.getStringExtra(EXTRA_KEY)
        val srq = intent.getStringExtra(EXTRA_SRQ)
        val choice = intent.getStringExtra(EXTRA_CHOICE) ?: "deny"
        val notifId = intent.getIntExtra(EXTRA_NOTIF_ID, 0)

        val socket = GatewayHub.socket
        if (socket != null && srq != null) {
            socket.respond(
                JsonPrimitive(srq),
                buildJsonObject { put("choice", choice) },
            )
        }
        if (key != null) NotificationCenter.resolve(context, key)
        if (notifId != 0) {
            context.getSystemService(NotificationManager::class.java)?.cancel(notifId)
        }
    }

    companion object {
        const val ACTION_RESPOND = "com.cornerstone.agency.action.REQUEST_RESPOND"
        const val EXTRA_NOTIF_ID = "notif_id"
        const val EXTRA_KEY = "request_key"
        const val EXTRA_SRQ = "srq_id"
        const val EXTRA_CHOICE = "choice"
    }
}

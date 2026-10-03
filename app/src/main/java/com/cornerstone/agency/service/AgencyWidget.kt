package com.cornerstone.agency.service

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.cornerstone.agency.MainActivity
import com.cornerstone.agency.R

/** Home-screen shortcut: tap the card to open Kaori, tap the mic to start a voice call. */
class AgencyWidget : AppWidgetProvider() {
    override fun onUpdate(context: Context, manager: AppWidgetManager, ids: IntArray) {
        ids.forEach { id ->
            val views = RemoteViews(context.packageName, R.layout.widget_agency)
            views.setOnClickPendingIntent(R.id.widget_chat, launch(context, 1, voice = false))
            views.setOnClickPendingIntent(R.id.widget_voice, launch(context, 2, voice = true))
            manager.updateAppWidget(id, views)
        }
    }

    private fun launch(context: Context, code: Int, voice: Boolean): PendingIntent {
        val intent = Intent(context, MainActivity::class.java).apply {
            action = ACTION_WIDGET
            putExtra(EXTRA_VOICE, voice)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }
        return PendingIntent.getActivity(
            context, code, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )
    }

    companion object {
        const val ACTION_WIDGET = "com.cornerstone.agency.action.WIDGET"
        const val EXTRA_VOICE = "com.cornerstone.agency.extra.VOICE"
    }
}

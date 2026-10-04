package com.cornerstone.agency.service

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.cornerstone.agency.MainActivity
import com.cornerstone.agency.R
import com.cornerstone.agency.data.CompanionRepository

/**
 * Home-screen card. Mark → Feed, name/line → the default bot's latest chat, mic → voice.
 * The line shows the unread Feed count; [CompanionRepository] calls [refresh] when it changes.
 */
class AgencyWidget : AppWidgetProvider() {
    override fun onUpdate(context: Context, manager: AppWidgetManager, ids: IntArray) {
        val state = CompanionRepository.get(context).state.value
        val unread = state.posts.count { !it.read }
        val line = when {
            unread == 1 -> "1 new post in your Feed"
            unread > 1 -> "$unread new posts in your Feed"
            else -> "What's on your mind?"
        }
        ids.forEach { id ->
            val views = RemoteViews(context.packageName, R.layout.widget_agency)
            views.setTextViewText(R.id.widget_title, state.feedBotTitle.ifBlank { "Kaori" })
            views.setTextViewText(R.id.widget_line, line)
            views.setOnClickPendingIntent(R.id.widget_chat, launch(context, 1, voice = false))
            views.setOnClickPendingIntent(R.id.widget_voice, launch(context, 2, voice = true))
            views.setOnClickPendingIntent(R.id.widget_feed, openFeed(context))
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

    private fun openFeed(context: Context): PendingIntent {
        val intent = Intent(context, MainActivity::class.java).apply {
            action = ACTION_FEED
            putExtra(NotificationCenter.EXTRA_TAB, "feed")
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }
        return PendingIntent.getActivity(
            context, 3, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )
    }

    companion object {
        const val ACTION_WIDGET = "com.cornerstone.agency.action.WIDGET"
        const val ACTION_FEED = "com.cornerstone.agency.action.WIDGET_FEED"
        const val EXTRA_VOICE = "com.cornerstone.agency.extra.VOICE"

        /** Re-render every placed instance (no-op when none). */
        fun refresh(context: Context) {
            val app = context.applicationContext
            val manager = AppWidgetManager.getInstance(app) ?: return
            val ids = manager.getAppWidgetIds(ComponentName(app, AgencyWidget::class.java))
            if (ids.isEmpty()) return
            app.sendBroadcast(
                Intent(app, AgencyWidget::class.java).apply {
                    action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
                    putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids)
                },
            )
        }
    }
}

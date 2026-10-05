package com.cornerstone.agency.service

import android.content.Context
import android.util.Log
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.cornerstone.agency.data.ConnectionRepository
import com.cornerstone.agency.data.GatewayHub
import com.cornerstone.agency.data.ReplyWatch
import com.cornerstone.agency.data.SecureStore
import java.util.concurrent.TimeUnit

/**
 * Background safety net. The live socket (kept by [ConnectionService]) delivers replies the
 * moment they finish, but Android can still kill it: Doze, battery savers, the app being swiped
 * away. Every ~15 minutes this checks, over plain REST, the chats that are waiting on a reply to
 * something sent from the phone ([ReplyWatch]) and notifies for any that have landed.
 */
class CatchUpWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val app = applicationContext
        ReplyWatch.init(app)
        if (GatewayHub.foregrounded && GatewayHub.repo != null) return Result.success() // on screen already
        val waiting = ReplyWatch.all()
        if (waiting.isEmpty()) return Result.success()
        val repo = GatewayHub.repo ?: ConnectionRepository(SecureStore(app)).takeIf { it.restore() }
            ?: return Result.success()
        var failures = 0
        for (w in waiting) {
            val rows = runCatching { repo.tail(w.storedId, w.profile, 6) }.getOrElse {
                failures++
                null
            } ?: continue
            val text = ReplyWatch.landedReply(rows, w.afterRow) ?: continue
            if (w.storedId in GatewayHub.openChatStoredIds()) continue
            NotificationCenter.postReply(app, w.profile, w.storedId, text)
            ReplyWatch.clear(w.storedId)
            Log.i(TAG, "caught up reply in ${w.storedId}")
        }
        return if (failures == waiting.size) Result.retry() else Result.success()
    }

    companion object {
        private const val TAG = "CatchUpWorker"
        private const val WORK = "reply-catch-up"

        fun schedule(context: Context) {
            val req = PeriodicWorkRequestBuilder<CatchUpWorker>(15, TimeUnit.MINUTES)
                .setConstraints(Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build())
                .build()
            WorkManager.getInstance(context.applicationContext)
                .enqueueUniquePeriodicWork(WORK, ExistingPeriodicWorkPolicy.KEEP, req)
        }

        fun cancel(context: Context) {
            WorkManager.getInstance(context.applicationContext).cancelUniqueWork(WORK)
        }
    }
}

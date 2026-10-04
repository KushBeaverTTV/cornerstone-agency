package com.cornerstone.agency.service

import android.content.Context
import android.util.Log
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.cornerstone.agency.data.CompanionRepository
import com.cornerstone.agency.data.FeedWriter
import java.util.concurrent.TimeUnit

/**
 * Daily issue. One-shot work re-armed after every run (more precise than PeriodicWork's
 * 15-minute flex), surviving reboots because WorkManager persists it.
 */
object FeedScheduler {
    private const val TAG = "FeedScheduler"
    private const val WORK = "feed-daily-issue"

    /** Reconcile WorkManager with the saved schedule. Call after any change and on app start. */
    fun sync(context: Context) {
        val app = context.applicationContext
        val schedule = CompanionRepository.get(app).state.value.feedSchedule
        val wm = WorkManager.getInstance(app)
        if (!schedule.enabled) {
            wm.cancelUniqueWork(WORK)
            return
        }
        val now = System.currentTimeMillis()
        val delayMs = schedule.nextRunAfter(now) - now
        val request = OneTimeWorkRequestBuilder<FeedWorker>()
            .setInitialDelay(delayMs, TimeUnit.MILLISECONDS)
            .setConstraints(Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build())
            .addTag(WORK)
            .build()
        val running = runCatching {
            wm.getWorkInfosForUniqueWork(WORK).get().any { it.state == WorkInfo.State.RUNNING }
        }.getOrDefault(false)
        val policy = if (running) ExistingWorkPolicy.APPEND_OR_REPLACE else ExistingWorkPolicy.REPLACE
        wm.enqueueUniqueWork(WORK, policy, request)
        Log.i(TAG, "next issue in ${delayMs / 60_000} min (${schedule.label()})")
    }
}

class FeedWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        val app = applicationContext
        val companion = CompanionRepository.get(app)
        companion.awaitLoaded()
        if (!companion.state.value.feedSchedule.enabled) return Result.success()
        val result = FeedWriter.get(app).write(scheduled = true)
        Log.i("FeedWorker", "scheduled issue → $result")
        return when {
            // Gateway down (phone off VPN, PC asleep): back off and try again, same day.
            result is FeedWriter.State.Failed && result.message == "Gateway is offline" && runAttemptCount < 6 ->
                Result.retry()
            else -> {
                FeedScheduler.sync(app) // arm tomorrow
                Result.success()
            }
        }
    }
}

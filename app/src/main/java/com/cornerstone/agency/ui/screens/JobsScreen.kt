package com.cornerstone.agency.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornerstone.agency.data.CronJob
import com.cornerstone.agency.data.SessionSummary
import com.cornerstone.agency.theme.AgencyTokens
import com.cornerstone.agency.ui.AppViewModel
import com.cornerstone.agency.ui.components.AgencyCard
import com.cornerstone.agency.ui.components.AgencyRow
import com.cornerstone.agency.ui.components.BotAvatar
import com.cornerstone.agency.ui.components.EmptyState
import com.cornerstone.agency.ui.components.SectionKicker
import com.cornerstone.agency.ui.components.botAvatarColor
import com.cornerstone.agency.ui.components.relativeTime
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

/** ISO-8601 ("2026-09-20T13:24:37.574801-04:00") → epoch seconds. */
internal fun isoToEpoch(iso: String?): Double {
    if (iso.isNullOrBlank()) return 0.0
    for (pattern in arrayOf("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", "yyyy-MM-dd'T'HH:mm:ssXXX")) {
        val ok = runCatching {
            val f = SimpleDateFormat(pattern, Locale.US)
            f.timeZone = TimeZone.getTimeZone("UTC")
            f.parse(iso)?.time?.div(1000.0)
        }.getOrNull()
        if (ok != null) return ok
    }
    return 0.0
}

private enum class JobHealth { OK, PAUSED, FAILING }

private fun jobHealth(job: CronJob): JobHealth = when {
    job.paused -> JobHealth.PAUSED
    job.lastStatus == "error" || job.lastStatus == "failed" || job.failureStreak > 0 ->
        JobHealth.FAILING
    else -> JobHealth.OK
}

@Composable
private fun StatusPill(health: JobHealth, label: String) {
    val colors = AgencyTokens.colors
    val tint: Color = when (health) {
        JobHealth.OK -> colors.success
        JobHealth.PAUSED -> colors.warm
        JobHealth.FAILING -> colors.destructive
    }
    Box(
        Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(tint.copy(alpha = 0.16f))
            .padding(horizontal = 10.dp, vertical = 3.dp),
    ) {
        Text(label, style = MaterialTheme.typography.labelSmall, color = tint)
    }
}

/** Jobs tab: cron routines grouped by owning bot ([bot:x] name prefix). */
@Composable
fun JobsScreen(vm: AppViewModel, onOpenJob: (String) -> Unit = {}) {
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space
    val ui by vm.ui.collectAsState()

    var jobs by remember { mutableStateOf<List<CronJob>>(emptyList()) }
    var loading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()
    fun load() {
        scope.launch {
            try {
                jobs = vm.repo.cronJobs()
                error = ""
            } catch (e: Exception) {
                error = e.message ?: "Failed to load jobs"
            } finally {
                loading = false
            }
        }
    }
    LaunchedEffect(Unit) { load() }

    val profileTitle = { name: String -> botDisplayName(ui.profiles, name) }

    // Group by owning bot, roster order (Kaori first), unknowns last.
    val grouped = jobs
        .groupBy { it.botProfile }
        .toSortedMap(
            compareBy { name ->
                ui.profiles.indexOfFirst { it.name == name }
                    .let { if (it < 0) Int.MAX_VALUE else it }
            },
        )

    PullToRefreshBox(
        isRefreshing = loading && jobs.isNotEmpty(),
        onRefresh = { loading = true; load() },
        modifier = Modifier.fillMaxSize(),
    ) {
        LazyColumn(Modifier.fillMaxSize()) {
            item {
                Text(
                    text = "Jobs",
                    style = MaterialTheme.typography.displayLarge,
                    color = colors.wordmark,
                    modifier = Modifier.padding(
                        horizontal = space.lg, vertical = space.lg,
                    ),
                )
            }

            if (jobs.isEmpty() && !loading) {
                item {
                    EmptyState(
                        if (error.isEmpty()) "No routines" else "Couldn't load jobs",
                        if (error.isEmpty()) "Scheduled work lands here" else error,
                    )
                }
            } else {
                grouped.forEach { (profileName, group) ->
                    item(key = "bot-$profileName") {
                        Row(
                            Modifier.padding(
                                start = space.lg, top = space.md, bottom = space.xs,
                            ),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            BotAvatar(
                                name = profileTitle(profileName),
                                color = botAvatarColor(profileName, profileName == "default"),
                                size = 20.dp,
                            )
                            Spacer(Modifier.width(space.sm))
                            SectionKicker(profileTitle(profileName))
                        }
                    }
                    items(group, key = { it.id }) { job ->
                        val health = jobHealth(job)
                        AgencyRow(
                            title = job.displayName,
                            subtitle = job.scheduleText,
                            meta = "",
                            modifier = Modifier.padding(horizontal = space.sm),
                            onClick = { onOpenJob(job.id) },
                            trailing = {
                                StatusPill(
                                    health,
                                    when (health) {
                                        JobHealth.OK ->
                                            isoToEpoch(job.lastRunAt)
                                                .let { if (it > 0) relativeTime(it) else "ok" }
                                        JobHealth.PAUSED -> "paused"
                                        JobHealth.FAILING -> "failing"
                                    },
                                )
                            },
                        )
                    }
                }
            }
        }
        if (loading && jobs.isEmpty()) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center).size(28.dp),
                color = colors.gold, strokeWidth = 2.dp,
            )
        }
    }
}

/** Job detail: schedule, prompt/script card, recent runs, actions. */
@Composable
fun JobDetailScreen(
    vm: AppViewModel,
    jobId: String,
    onBack: () -> Unit,
    onDeleted: () -> Unit,
    onOpenSession: (String, String) -> Unit = { _, _ -> },
) {
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space
    val ui by vm.ui.collectAsState()
    val scope = rememberCoroutineScope()
    val snackbar = remember { SnackbarHostState() }

    var job by remember { mutableStateOf<CronJob?>(null) }
    var runs by remember { mutableStateOf<List<SessionSummary>>(emptyList()) }
    var error by remember { mutableStateOf("") }
    var busy by remember { mutableStateOf(false) }
    var confirmDelete by remember { mutableStateOf(false) }

    fun load() {
        scope.launch {
            try {
                job = vm.repo.cronJob(jobId)
                runs = runCatching { vm.repo.cronJobRuns(jobId) }.getOrDefault(emptyList())
                error = ""
            } catch (e: Exception) {
                error = e.message ?: "Failed to load job"
            }
        }
    }
    LaunchedEffect(jobId) { load() }

    fun action(verb: String, call: suspend () -> Unit, done: String) {
        busy = true
        scope.launch {
            try {
                call()
                snackbar.showSnackbar(done)
                load()
            } catch (e: Exception) {
                snackbar.showSnackbar("$verb failed: ${e.message}")
            } finally {
                busy = false
            }
        }
    }

    Box(Modifier.fillMaxSize().safeDrawingPadding()) {
        Column(Modifier.fillMaxSize()) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = space.sm, vertical = space.xs),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back", tint = colors.foreground)
                }
                Text(
                    "Job",
                    style = MaterialTheme.typography.titleMedium,
                    color = colors.foreground,
                )
            }

            Column(
                Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = space.lg),
            ) {
                when {
                    job == null && error.isEmpty() -> {
                        Spacer(Modifier.height(space.xl))
                        CircularProgressIndicator(
                            Modifier.size(28.dp),
                            color = colors.gold, strokeWidth = 2.dp,
                        )
                    }

                    job == null -> Text(error, color = colors.destructive)

                    else -> {
                        val j = job!!
                        Spacer(Modifier.height(space.md))
                        Text(
                            j.displayName,
                            style = MaterialTheme.typography.displaySmall,
                            color = colors.wordmark,
                        )
                        Spacer(Modifier.height(space.xs))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            StatusPill(
                                jobHealth(j),
                                when (jobHealth(j)) {
                                    JobHealth.OK -> "healthy"
                                    JobHealth.PAUSED -> "paused"
                                    JobHealth.FAILING -> "failing"
                                },
                            )
                            Spacer(Modifier.width(space.sm))
                            Text(
                                j.scheduleText,
                                style = MaterialTheme.typography.bodyMedium,
                                color = colors.secondary,
                            )
                        }
                        Spacer(Modifier.height(space.lg))

                        AgencyCard(Modifier.fillMaxWidth()) {
                            InfoLine("Bot", botDisplayName(ui.profiles, j.botProfile))
                            InfoLine("Runs completed", j.repeat.completed.toString())
                            InfoLine("Next run", j.nextRunAt ?: "—")
                            InfoLine("Last run", j.lastRunAt ?: "—")
                            InfoLine("Last status", j.lastStatus.ifBlank { "—" })
                            if (!j.lastError.isNullOrBlank()) InfoLine("Last error", j.lastError!!)
                            InfoLine("Deliver", j.deliver.ifBlank { "local" })
                            j.model?.let { InfoLine("Model", it) }
                            j.skill?.let { InfoLine("Skill", it) }
                            if (j.noAgent) InfoLine("Mode", "no-agent (script)")
                        }
                        Spacer(Modifier.height(space.lg))

                        if (j.prompt.isNotBlank() || !j.script.isNullOrBlank()) {
                            SectionKicker(if (j.script != null) "Script" else "Prompt")
                            Spacer(Modifier.height(space.xs))
                            AgencyCard(Modifier.fillMaxWidth()) {
                                Text(
                                    j.script ?: j.prompt,
                                    fontFamily = FontFamily.Monospace,
                                    fontSize = 13.sp,
                                    color = colors.secondary,
                                )
                            }
                            Spacer(Modifier.height(space.lg))
                        }

                        if (runs.isNotEmpty()) {
                            SectionKicker("Recent runs")
                            Spacer(Modifier.height(space.xs))
                            runs.forEach { run ->
                                AgencyRow(
                                    title = run.label,
                                    subtitle = run.preview.lineSequence().firstOrNull() ?: "",
                                    meta = relativeTime(run.startedAt),
                                    onClick = { onOpenSession(j.botProfile, run.id) },
                                )
                            }
                            Spacer(Modifier.height(space.lg))
                        }

                        // Actions
                        AgencyRow(
                            title = "Run now",
                            subtitle = "Dispatch the job immediately",
                            meta = "",
                            dimmed = busy,
                            onClick = {
                                if (!busy) action("Trigger", { vm.repo.cronAction(j.id, "trigger") }, "Triggered")
                            },
                        )
                        AgencyRow(
                            title = if (j.paused) "Resume" else "Pause",
                            subtitle = if (j.paused) "Resume the schedule" else "Pause the schedule",
                            meta = "",
                            dimmed = busy,
                            onClick = {
                                if (!busy) {
                                    val a = if (j.paused) "resume" else "pause"
                                    action(
                                        a.replaceFirstChar { it.uppercase() },
                                        { vm.repo.cronAction(j.id, a) },
                                        if (a == "pause") "Paused" else "Resumed",
                                    )
                                }
                            },
                        )
                        AgencyRow(
                            title = "Delete job",
                            subtitle = "Remove this routine",
                            meta = "",
                            dimmed = busy,
                            onClick = { if (!busy) confirmDelete = true },
                        )
                        Spacer(Modifier.height(space.xl))
                    }
                }
            }
        }
        SnackbarHost(snackbar, Modifier.align(Alignment.BottomCenter))
    }

    if (confirmDelete) {
        AlertDialog(
            onDismissRequest = { if (!busy) confirmDelete = false },
            title = { Text("Delete ${job?.displayName ?: "job"}?") },
            text = { Text("The schedule is removed; past run sessions stay.") },
            confirmButton = {
                TextButton(
                    enabled = !busy,
                    onClick = {
                        busy = true
                        scope.launch {
                            try {
                                vm.repo.deleteCronJob(jobId)
                                confirmDelete = false
                                onDeleted()
                            } catch (e: Exception) {
                                snackbar.showSnackbar("Delete failed: ${e.message}")
                            } finally {
                                busy = false
                            }
                        }
                    },
                ) { Text("Delete", color = colors.destructive) }
            },
            dismissButton = {
                TextButton(onClick = { confirmDelete = false }, enabled = !busy) {
                    Text("Cancel")
                }
            },
        )
    }
}

@Composable
private fun InfoLine(label: String, value: String) {
    val colors = AgencyTokens.colors
    Row(Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
        Text(
            label,
            style = MaterialTheme.typography.bodyMedium,
            color = colors.tertiary,
            modifier = Modifier.width(120.dp),
        )
        Text(
            value,
            style = MaterialTheme.typography.bodyMedium,
            color = colors.secondary,
            modifier = Modifier.weight(1f),
        )
    }
}

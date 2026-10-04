package com.cornerstone.agency.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornerstone.agency.data.AnalyticsUsage
import com.cornerstone.agency.data.GatewayState
import com.cornerstone.agency.data.PluginInfo
import com.cornerstone.agency.data.PluginsResult
import com.cornerstone.agency.data.SystemStats
import com.cornerstone.agency.data.ToolsetInfo
import com.cornerstone.agency.data.ToolsetsResult
import com.cornerstone.agency.theme.AgencyTokens
import com.cornerstone.agency.ui.components.PageHeader
import com.cornerstone.agency.ui.AppViewModel
import com.cornerstone.agency.ui.components.AgencyCard
import com.cornerstone.agency.ui.components.SectionKicker
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.buildJsonObject
import java.util.Locale

private val STATS_JSON = Json { ignoreUnknownKeys = true }

private fun humanBytes(bytes: Long): String = when {
    bytes >= 1L shl 30 -> "%.1f GB".format(Locale.US, bytes / 1073741824.0)
    bytes >= 1L shl 20 -> "%.0f MB".format(Locale.US, bytes / 1048576.0)
    bytes >= 1L shl 10 -> "%.0f KB".format(Locale.US, bytes / 1024.0)
    else -> "$bytes B"
}

private fun humanUptime(seconds: Double): String {
    val s = seconds.toLong()
    return when {
        s >= 86400 -> "${s / 86400}d ${s % 86400 / 3600}h"
        s >= 3600 -> "${s / 3600}h ${s % 3600 / 60}m"
        else -> "${s / 60}m"
    }
}

private fun humanTokens(n: Long): String = when {
    n >= 1_000_000 -> "%.1fM".format(Locale.US, n / 1_000_000.0)
    n >= 1_000 -> "%.1fk".format(Locale.US, n / 1_000.0)
    else -> n.toString()
}

/** System hub: version/host, gateway, host metrics, usage today, plugins/tools. */
@Composable
fun StatusScreen(vm: AppViewModel, onBack: () -> Unit) {
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space
    val ui by vm.ui.collectAsState()
    val socketState by vm.gateway.state.collectAsState()
    val scope = rememberCoroutineScope()

    var stats by remember { mutableStateOf<SystemStats?>(null) }
    var usage by remember { mutableStateOf<AnalyticsUsage?>(null) }
    var plugins by remember { mutableStateOf<List<PluginInfo>>(emptyList()) }
    var toolsets by remember { mutableStateOf<List<ToolsetInfo>>(emptyList()) }
    var pluginsOpen by remember { mutableStateOf(false) }
    var toolsetsOpen by remember { mutableStateOf(false) }
    var refreshing by remember { mutableStateOf(false) }

    fun load() {
        scope.launch {
            refreshing = true
            try {
                vm.refreshStatus()
                stats = runCatching { vm.repo.systemStats() }.getOrNull()
                usage = runCatching { vm.repo.analyticsUsage() }.getOrNull()
                if (vm.gateway.state.value == GatewayState.OPEN) {
                    plugins = runCatching {
                        STATS_JSON.decodeFromJsonElement(
                            PluginsResult.serializer(),
                            vm.gateway.call("plugins.list", buildJsonObject {}),
                        ).plugins
                    }.getOrDefault(emptyList())
                    toolsets = runCatching {
                        STATS_JSON.decodeFromJsonElement(
                            ToolsetsResult.serializer(),
                            vm.gateway.call("tools.list", buildJsonObject {}),
                        ).toolsets
                    }.getOrDefault(emptyList())
                }
            } finally {
                refreshing = false
            }
        }
    }

    // Initial load + 30s auto-refresh while the screen is visible.
    LaunchedEffect(Unit) {
        load()
        while (true) {
            delay(30_000)
            load()
        }
    }
    // WS lists depend on the socket being open.
    LaunchedEffect(socketState) {
        if (socketState == GatewayState.OPEN) load()
    }

    Column(Modifier.fillMaxSize().safeDrawingPadding()) {
        PageHeader("System", onBack = onBack) {
            IconButton(onClick = { load() }, enabled = !refreshing) {
                if (refreshing) {
                    CircularProgressIndicator(
                        Modifier.padding(12.dp),
                        color = colors.gold, strokeWidth = 2.dp,
                    )
                } else {
                    Icon(Icons.Filled.Refresh, "Refresh", tint = colors.secondary)
                }
            }
        }

        Column(
            Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = space.lg),
        ) {
            // Hermes / host
            SectionKicker("Hermes")
            Spacer(Modifier.height(space.sm))
            AgencyCard(Modifier.fillMaxWidth()) {
                StatLine("Version", "v${stats?.hermesVersion ?: ui.status?.version ?: "—"}")
                StatLine("Host", stats?.hostname ?: "—")
                StatLine("OS", stats?.platform ?: "—")
                StatLine("Python", stats?.pythonVersion ?: "—")
                StatLine("Uptime", stats?.let { humanUptime(it.uptimeSeconds) } ?: "—")
            }
            Spacer(Modifier.height(space.lg))

            // Gateway
            SectionKicker("Gateway")
            Spacer(Modifier.height(space.sm))
            AgencyCard(Modifier.fillMaxWidth()) {
                val status = ui.status
                val running = status?.gatewayRunning == true
                StatLine("State", if (status == null) "…" else if (running) "Running" else "Stopped")
                StatLine("Socket", socketState.name.lowercase())
                status?.gatewayPlatforms?.forEach { (name, p) ->
                    StatLine(name, p.state)
                }
            }
            Spacer(Modifier.height(space.lg))

            // Host metrics
            SectionKicker("Host")
            Spacer(Modifier.height(space.sm))
            stats?.let { s ->
                Row(Modifier.fillMaxWidth()) {
                    MetricTile(
                        "CPU",
                        "%.0f%%".format(Locale.US, s.cpuPercent),
                        "${s.cpuCount} cores",
                        Modifier.weight(1f),
                    )
                    Spacer(Modifier.width(space.sm))
                    MetricTile(
                        "RAM",
                        "%.0f%%".format(Locale.US, s.memory.percent),
                        "${humanBytes(s.memory.used)} / ${humanBytes(s.memory.total)}",
                        Modifier.weight(1f),
                    )
                }
                Spacer(Modifier.height(space.sm))
                Row(Modifier.fillMaxWidth()) {
                    MetricTile(
                        "Disk",
                        "%.0f%%".format(Locale.US, s.disk.percent),
                        "${humanBytes(s.disk.free)} free",
                        Modifier.weight(1f),
                    )
                    Spacer(Modifier.width(space.sm))
                    MetricTile(
                        "Process",
                        humanBytes(s.process.rss),
                        "pid ${s.process.pid} · ${s.process.numThreads} threads",
                        Modifier.weight(1f),
                    )
                }
            } ?: AgencyCard(Modifier.fillMaxWidth()) {
                StatLine("Host metrics", "unavailable")
            }
            Spacer(Modifier.height(space.lg))

            // Usage today
            usage?.daily?.lastOrNull()?.let { today ->
                SectionKicker("Usage · ${today.day}")
                Spacer(Modifier.height(space.sm))
                AgencyCard(Modifier.fillMaxWidth()) {
                    StatLine("Sessions", today.sessions.toString())
                    StatLine("API calls", today.apiCalls.toString())
                    StatLine(
                        "Tokens",
                        "${humanTokens(today.inputTokens)} in · ${humanTokens(today.outputTokens)} out" +
                            " · ${humanTokens(today.cacheReadTokens)} cached",
                    )
                    if (today.estimatedCost > 0) {
                        StatLine("Est. cost", "$%.2f".format(Locale.US, today.estimatedCost))
                    }
                }
                Spacer(Modifier.height(space.lg))
            }

            // Plugins
            SectionKicker("Plugins · ${plugins.size}")
            Spacer(Modifier.height(space.sm))
            AgencyCard(
                Modifier
                    .fillMaxWidth()
                    .clickable { pluginsOpen = !pluginsOpen },
            ) {
                StatLine(
                    "Installed",
                    "${plugins.count { it.enabled }} enabled · tap to ${if (pluginsOpen) "hide" else "show"}",
                )
                if (pluginsOpen) {
                    Spacer(Modifier.height(space.sm))
                    plugins.forEach { p ->
                        StatLine(
                            p.name.substringAfterLast('/'),
                            if (p.enabled) "enabled" else "disabled",
                        )
                    }
                }
            }
            Spacer(Modifier.height(space.lg))

            // Toolsets
            SectionKicker("Tools · ${toolsets.sumOf { it.toolCount }}")
            Spacer(Modifier.height(space.sm))
            AgencyCard(
                Modifier
                    .fillMaxWidth()
                    .clickable { toolsetsOpen = !toolsetsOpen },
            ) {
                StatLine(
                    "Toolsets",
                    "${toolsets.count { it.enabled }} enabled · tap to ${if (toolsetsOpen) "hide" else "show"}",
                )
                if (toolsetsOpen) {
                    Spacer(Modifier.height(space.sm))
                    toolsets.forEach { t ->
                        StatLine(t.name, "${t.toolCount} tools${if (!t.enabled) " · off" else ""}")
                    }
                }
            }
            Spacer(Modifier.height(space.xl))
        }
    }
}

@Composable
private fun StatLine(label: String, value: String) {
    val colors = AgencyTokens.colors
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(label, style = MaterialTheme.typography.bodyMedium, color = colors.tertiary)
        Text(
            value,
            style = MaterialTheme.typography.bodyMedium,
            color = colors.secondary,
            fontSize = 13.sp,
        )
    }
}

@Composable
private fun MetricTile(
    label: String,
    value: String,
    sub: String,
    modifier: Modifier = Modifier,
) {
    val colors = AgencyTokens.colors
    Column(
        modifier
            .clip(RoundedCornerShape(10.dp))
            .background(colors.card)
            .padding(AgencyTokens.space.md),
    ) {
        Text(label, style = MaterialTheme.typography.labelSmall, color = colors.tertiary)
        Spacer(Modifier.height(4.dp))
        Text(value, style = MaterialTheme.typography.titleLarge, color = colors.foreground)
        Spacer(Modifier.height(2.dp))
        Text(sub, style = MaterialTheme.typography.labelSmall, color = colors.tertiary)
    }
}

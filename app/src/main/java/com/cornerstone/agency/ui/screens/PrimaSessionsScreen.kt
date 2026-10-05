package com.cornerstone.agency.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cornerstone.agency.data.PrimaSaved
import com.cornerstone.agency.data.PrimaSession
import com.cornerstone.agency.theme.AgencyTokens
import com.cornerstone.agency.ui.AppViewModel
import com.cornerstone.agency.ui.components.AgencyRow
import com.cornerstone.agency.ui.components.BotAvatar
import com.cornerstone.agency.ui.components.EmptyState
import com.cornerstone.agency.ui.components.PageHeader
import com.cornerstone.agency.ui.components.SectionKicker
import com.cornerstone.agency.ui.components.relativeTime
import kotlinx.coroutines.launch

/**
 * Prima conversations: the ones a worker has open right now, then every conversation saved on
 * the PC — including ones started in the Prime Agent desktop app — which reopen in place.
 * Long-press an open one to close its worker (the saved file stays).
 */
@Composable
fun PrimaSessionsScreen(
    vm: AppViewModel,
    onBack: () -> Unit,
    onOpenSession: (String) -> Unit,
) {
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space
    val scope = rememberCoroutineScope()
    var open by remember { mutableStateOf<List<PrimaSession>?>(null) }
    var saved by remember { mutableStateOf<List<PrimaSaved>>(emptyList()) }
    var error by remember { mutableStateOf("") }
    var busyPath by remember { mutableStateOf<String?>(null) }
    var confirmClose by remember { mutableStateOf<PrimaSession?>(null) }

    fun refresh() {
        scope.launch {
            try {
                open = vm.bridge.primaSessions()
                saved = runCatching { vm.bridge.primaSaved() }.getOrDefault(emptyList())
                error = ""
            } catch (e: Exception) {
                open = emptyList()
                error = e.message ?: "Couldn't load Prima sessions"
            }
        }
    }

    fun reopen(s: PrimaSaved) {
        s.open_id?.let { onOpenSession(it); return }
        if (busyPath != null) return
        busyPath = s.path
        scope.launch {
            try {
                onOpenSession(vm.bridge.createPrimaSession(sessionPath = s.path))
            } catch (e: Exception) {
                error = "Couldn't reopen “${s.title}”: ${e.message}"
            } finally {
                busyPath = null
            }
        }
    }

    LaunchedEffect(Unit) { refresh() }

    Column(Modifier.fillMaxSize().safeDrawingPadding()) {
        PageHeader("Prima sessions", onBack = onBack)
        val live = open
        when {
            live == null -> Box(Modifier.fillMaxSize(), Alignment.Center) {
                CircularProgressIndicator(color = colors.gold, strokeWidth = 2.dp, modifier = Modifier.size(28.dp))
            }
            live.isEmpty() && saved.isEmpty() -> EmptyState(
                if (error.isEmpty()) "No Prima sessions" else "Couldn't load sessions",
                if (error.isEmpty()) "Open Prima from the Bots tab" else error,
            )
            else -> LazyColumn(Modifier.fillMaxSize()) {
                if (error.isNotEmpty()) item {
                    Text(error, color = colors.destructive, modifier = Modifier.padding(horizontal = space.lg, vertical = space.sm))
                }
                if (live.isNotEmpty()) {
                    item { SectionKicker("Open now", Modifier.padding(horizontal = space.lg, vertical = space.sm)) }
                    items(live, key = { "o-" + it.id }) { s ->
                        AgencyRow(
                            title = s.label,
                            subtitle = "${s.cwd} · ${s.message_count} msgs",
                            meta = if (s.is_streaming) "streaming" else "",
                            onClick = { onOpenSession(s.id) },
                            onLongClick = { confirmClose = s },
                            modifier = Modifier.padding(horizontal = space.sm),
                            leading = { BotAvatar(name = "Prima", color = colors.defaultViolet) },
                        )
                    }
                }
                val closed = saved.filter { it.open_id == null }
                if (closed.isNotEmpty()) {
                    item { SectionKicker("Saved on this PC", Modifier.padding(horizontal = space.lg, vertical = space.sm)) }
                    items(closed, key = { "s-" + it.path }) { s ->
                        AgencyRow(
                            title = s.title.ifBlank { "Untitled" },
                            subtitle = s.preview.ifBlank { "${s.message_count} msgs" },
                            meta = if (busyPath == s.path) "opening…" else relativeTime(s.updated_at),
                            onClick = { reopen(s) },
                            modifier = Modifier.padding(horizontal = space.sm),
                            leading = { BotAvatar(name = "Prima", color = colors.defaultViolet.copy(alpha = 0.55f)) },
                        )
                    }
                }
                item { Spacer(Modifier.height(space.lg)) }
            }
        }
    }

    confirmClose?.let { s ->
        AlertDialog(
            onDismissRequest = { confirmClose = null },
            title = { Text("Close this Prima session?") },
            text = { Text("“${s.label}” — stops its prime-agent worker. The conversation stays saved on the PC and can be reopened.") },
            confirmButton = {
                TextButton(onClick = {
                    confirmClose = null
                    scope.launch {
                        runCatching { vm.bridge.primaDelete(s.id) }
                        refresh()
                    }
                }) { Text("Close", color = colors.destructive) }
            },
            dismissButton = {
                TextButton(onClick = { confirmClose = null }) { Text("Cancel") }
            },
        )
    }
}

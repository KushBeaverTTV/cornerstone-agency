package com.cornerstone.agency.ui.screens

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import com.cornerstone.agency.data.PrimaSession
import com.cornerstone.agency.theme.AgencyTokens
import com.cornerstone.agency.ui.AppViewModel
import com.cornerstone.agency.ui.components.AgencyRow
import com.cornerstone.agency.ui.components.BotAvatar
import com.cornerstone.agency.ui.components.EmptyState
import kotlinx.coroutines.launch

/** Bridge Prima sessions — tap to open, long-press to delete (kills the worker). */
@Composable
fun PrimaSessionsScreen(
    vm: AppViewModel,
    onBack: () -> Unit,
    onOpenSession: (String) -> Unit,
) {
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space
    val scope = rememberCoroutineScope()
    var sessions by remember { mutableStateOf<List<PrimaSession>?>(null) }
    var error by remember { mutableStateOf("") }
    var confirmDelete by remember { mutableStateOf<PrimaSession?>(null) }

    fun refresh() {
        scope.launch {
            try {
                sessions = vm.bridge.primaSessions()
                error = ""
            } catch (e: Exception) {
                sessions = emptyList()
                error = e.message ?: "Couldn't load Prima sessions"
            }
        }
    }

    LaunchedEffect(Unit) { refresh() }

    Column(Modifier.fillMaxSize().safeDrawingPadding()) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack, "Back",
                    tint = colors.foreground,
                )
            }
            Text(
                "Prima sessions",
                style = MaterialTheme.typography.titleLarge,
                color = colors.wordmark,
            )
        }
        when {
            sessions == null -> Box(Modifier.fillMaxSize(), Alignment.Center) {
                CircularProgressIndicator(color = colors.gold, strokeWidth = 2.dp, modifier = Modifier.size(28.dp))
            }
            sessions!!.isEmpty() -> EmptyState(
                if (error.isEmpty()) "No Prima sessions" else "Couldn't load sessions",
                if (error.isEmpty()) "Open Prima from the Bots tab" else error,
            )
            else -> LazyColumn(Modifier.fillMaxSize()) {
                items(sessions!!, key = { it.id }) { s ->
                    AgencyRow(
                        title = s.label,
                        subtitle = "${s.cwd} · ${s.message_count} msgs",
                        meta = if (s.is_streaming) "streaming" else "",
                        onClick = { onOpenSession(s.id) },
                        onLongClick = { confirmDelete = s },
                        modifier = Modifier.padding(horizontal = space.sm),
                        leading = { BotAvatar(name = "Prima", color = colors.defaultViolet) },
                    )
                }
                item { Spacer(Modifier.height(space.lg)) }
            }
        }
    }

    confirmDelete?.let { s ->
        AlertDialog(
            onDismissRequest = { confirmDelete = null },
            title = { Text("Delete Prima session?") },
            text = { Text("“${s.label}” — kills the prime-agent worker. The on-disk session survives.") },
            confirmButton = {
                TextButton(onClick = {
                    confirmDelete = null
                    scope.launch {
                        runCatching { vm.bridge.primaDelete(s.id) }
                        refresh()
                    }
                }) { Text("Delete", color = colors.destructive) }
            },
            dismissButton = {
                TextButton(onClick = { confirmDelete = null }) { Text("Cancel") }
            },
        )
    }
}

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
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.cornerstone.agency.data.ChatRepository
import com.cornerstone.agency.data.GatewayState
import com.cornerstone.agency.data.SessionSummary
import com.cornerstone.agency.theme.AgencyTokens
import com.cornerstone.agency.ui.AppViewModel
import com.cornerstone.agency.ui.components.AgencyRow
import com.cornerstone.agency.ui.components.BotAvatar
import com.cornerstone.agency.ui.components.EmptyState
import com.cornerstone.agency.ui.components.botAvatarColor
import com.cornerstone.agency.ui.components.relativeTime
import kotlinx.coroutines.launch

@Composable
fun SessionsScreen(
    vm: AppViewModel,
    profileName: String,
    onBack: () -> Unit,
    onOpenSession: (String) -> Unit,
) {
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space
    val ui by vm.ui.collectAsState()
    val socketState by vm.gateway.state.collectAsState()
    val scope = rememberCoroutineScope()

    // Only used for session.list / session.delete — never opened as a chat.
    val chat = remember(profileName) { ChatRepository(vm.gateway, profileName) }
    val botName = botDisplayName(ui.profiles, profileName)

    var sessions by remember { mutableStateOf<List<SessionSummary>>(emptyList()) }
    var loading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf("") }
    var pendingDelete by remember { mutableStateOf<SessionSummary?>(null) }

    fun refresh() {
        scope.launch {
            loading = sessions.isEmpty()
            try {
                sessions = chat.listSessions()
                error = ""
            } catch (e: Exception) {
                error = e.message ?: "Failed to load sessions"
            } finally {
                loading = false
            }
        }
    }

    LaunchedEffect(socketState) {
        if (socketState == GatewayState.OPEN) refresh()
    }

    Column(Modifier.fillMaxSize().safeDrawingPadding()) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = space.sm, vertical = space.xs),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back", tint = colors.foreground)
            }
            BotAvatar(
                name = botName,
                color = botAvatarColor(profileName, profileName == "default"),
                size = 30.dp,
            )
            Spacer(Modifier.width(space.sm))
            Column {
                Text(
                    "Sessions",
                    style = MaterialTheme.typography.titleMedium,
                    color = colors.foreground,
                )
                Text(
                    botName,
                    style = MaterialTheme.typography.labelSmall,
                    color = colors.tertiary,
                )
            }
        }

        Box(Modifier.weight(1f).fillMaxWidth()) {
            when {
                loading -> CircularProgressIndicator(
                    Modifier.align(Alignment.Center).width(28.dp).height(28.dp),
                    color = colors.gold, strokeWidth = 2.dp,
                )

                error.isNotEmpty() -> EmptyState("Couldn't load sessions", error)

                sessions.isEmpty() -> EmptyState("No sessions", "Start a chat with $botName")

                else -> LazyColumn(Modifier.fillMaxSize()) {
                    items(sessions, key = { it.id }) { s ->
                        AgencyRow(
                            title = s.label,
                            subtitle = s.preview.lineSequence().firstOrNull() ?: "",
                            meta = relativeTime(s.startedAt),
                            onClick = { onOpenSession(s.id) },
                            onLongClick = { pendingDelete = s },
                            modifier = Modifier.padding(horizontal = space.sm),
                        )
                    }
                }
            }
        }
    }

    pendingDelete?.let { s ->
        AlertDialog(
            onDismissRequest = { pendingDelete = null },
            title = { Text("Delete session?") },
            text = { Text(s.label, maxLines = 2, overflow = TextOverflow.Ellipsis) },
            confirmButton = {
                TextButton(onClick = {
                    pendingDelete = null
                    scope.launch {
                        runCatching { chat.deleteSession(s.id) }
                        refresh()
                    }
                }) { Text("Delete", color = colors.destructive) }
            },
            dismissButton = {
                TextButton(onClick = { pendingDelete = null }) { Text("Cancel") }
            },
        )
    }
}

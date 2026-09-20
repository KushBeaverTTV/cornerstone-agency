package com.cornerstone.agency.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import com.cornerstone.agency.data.BotProfile
import com.cornerstone.agency.data.GatewayState
import com.cornerstone.agency.data.SessionSummary
import com.cornerstone.agency.data.listProfileSessions
import com.cornerstone.agency.theme.AgencyTokens
import com.cornerstone.agency.ui.AppViewModel
import com.cornerstone.agency.ui.components.AgencyRow
import com.cornerstone.agency.ui.components.AgencyTextField
import com.cornerstone.agency.ui.components.BotAvatar
import com.cornerstone.agency.ui.components.EmptyState
import com.cornerstone.agency.ui.components.SectionKicker
import com.cornerstone.agency.ui.components.botAvatarColor
import com.cornerstone.agency.ui.components.relativeTime
import kotlinx.coroutines.launch

@Composable
fun BotsScreen(vm: AppViewModel, onOpenChat: (String) -> Unit) {
    val ui by vm.ui.collectAsState()
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space
    var query by remember { mutableStateOf("") }

    val filtered = ui.profiles.filter {
        query.isBlank() ||
            it.title.contains(query, ignoreCase = true) ||
            it.subtitle.contains(query, ignoreCase = true) ||
            it.name.contains(query, ignoreCase = true)
    }

    PullToRefreshBox(
        isRefreshing = ui.profilesLoading,
        onRefresh = { vm.refreshProfiles() },
        modifier = Modifier.fillMaxSize(),
    ) {
        LazyColumn(Modifier.fillMaxSize()) {
            item {
                Column(Modifier.padding(horizontal = space.lg)) {
                    Spacer(Modifier.height(space.lg))
                    Text(
                        text = "Bots",
                        style = MaterialTheme.typography.displayLarge,
                        color = colors.wordmark,
                    )
                    Spacer(Modifier.height(space.md))
                    AgencyTextField(
                        value = query,
                        onValueChange = { query = it },
                        placeholder = "Search",
                    )
                    Spacer(Modifier.height(space.lg))
                    SectionKicker("Agents")
                    Spacer(Modifier.height(space.xs))
                }
            }

            if (ui.profilesError.isNotEmpty() && ui.profiles.isEmpty()) {
                item {
                    EmptyState("No roster", ui.profilesError)
                }
            } else if (filtered.isEmpty() && !ui.profilesLoading) {
                item {
                    EmptyState(
                        if (ui.profiles.isEmpty()) "No agents" else "No matches",
                        if (ui.profiles.isEmpty()) "Pull to refresh" else "Nothing matches “$query”",
                    )
                }
            } else {
                items(filtered, key = { it.name }) { profile ->
                    AgencyRow(
                        title = profile.title,
                        subtitle = profile.subtitle,
                        meta = if (profile.description.isNotBlank()) profile.model else "",
                        modifier = Modifier.padding(horizontal = space.sm),
                        onClick = { onOpenChat(profile.name) },
                        leading = {
                            BotAvatar(
                                name = profile.title,
                                color = botAvatarColor(profile.name, profile.isDefault),
                            )
                        },
                    )
                }
            }
        }
        if (ui.profilesLoading && ui.profiles.isEmpty()) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = colors.gold,
            )
        }
    }
}

@Composable
fun ChatsScreen(
    vm: AppViewModel,
    onOpenSession: (profile: String, sessionId: String) -> Unit,
) {
    val ui by vm.ui.collectAsState()
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space
    val socketState by vm.gateway.state.collectAsState()
    val scope = rememberCoroutineScope()

    // Sessions grouped by bot, in roster order.
    var groups by remember {
        mutableStateOf<List<Pair<BotProfile, List<SessionSummary>>>>(emptyList())
    }
    var loading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf("") }

    fun refresh() {
        val profiles = vm.ui.value.profiles
        if (profiles.isEmpty() || socketState != GatewayState.OPEN) return
        scope.launch {
            try {
                val out = mutableListOf<Pair<BotProfile, List<SessionSummary>>>()
                for (p in profiles) {
                    val sessions = runCatching { listProfileSessions(vm.gateway, p.name) }
                        .getOrDefault(emptyList())
                    if (sessions.isNotEmpty()) out += p to sessions
                }
                groups = out
                error = ""
            } catch (e: Exception) {
                error = e.message ?: "Failed to load sessions"
            } finally {
                loading = false
            }
        }
    }

    LaunchedEffect(socketState, ui.profiles) {
        if (socketState == GatewayState.OPEN && ui.profiles.isNotEmpty()) refresh()
    }
    // sessions.changed is a global push — refresh opportunistically.
    LaunchedEffect(Unit) {
        vm.gateway.events.collect { ev ->
            if (ev.type == "sessions.changed") refresh()
        }
    }

    Column(Modifier.fillMaxSize()) {
        ScreenTitle("Chats")
        when {
            loading && groups.isEmpty() -> Box(Modifier.fillMaxSize(), Alignment.Center) {
                CircularProgressIndicator(
                    color = colors.gold, strokeWidth = 2.dp,
                    modifier = Modifier.size(28.dp),
                )
            }

            groups.isEmpty() -> EmptyState(
                if (error.isEmpty()) "No chats yet" else "Couldn't load chats",
                if (error.isEmpty()) "Open a bot to start a conversation" else error,
            )

            else -> LazyColumn(Modifier.fillMaxSize()) {
                groups.forEach { (profile, sessions) ->
                    item(key = "kicker-${profile.name}") {
                        SectionKicker(
                            profile.title,
                            modifier = Modifier.padding(
                                horizontal = space.lg, vertical = space.xs,
                            ),
                        )
                    }
                    items(sessions, key = { "${profile.name}:${it.id}" }) { s ->
                        AgencyRow(
                            title = s.label,
                            subtitle = s.preview.lineSequence().firstOrNull() ?: "",
                            meta = relativeTime(s.startedAt),
                            onClick = { onOpenSession(profile.name, s.id) },
                            modifier = Modifier.padding(horizontal = space.sm),
                            leading = {
                                BotAvatar(
                                    name = profile.title,
                                    color = botAvatarColor(profile.name, profile.isDefault),
                                )
                            },
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun JobsScreen() {
    Column(Modifier.fillMaxSize()) {
        ScreenTitle("Jobs")
        EmptyState("No jobs yet", "Scheduled work lands here in a later milestone")
    }
}

@Composable
private fun ScreenTitle(title: String) {
    val colors = AgencyTokens.colors
    Text(
        text = title,
        style = MaterialTheme.typography.displayLarge,
        color = colors.wordmark,
        modifier = Modifier.padding(
            horizontal = AgencyTokens.space.lg,
            vertical = AgencyTokens.space.lg,
        ),
    )
}

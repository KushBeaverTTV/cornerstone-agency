package com.cornerstone.agency.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.cornerstone.agency.data.BotProfile
import com.cornerstone.agency.data.GatewayState
import com.cornerstone.agency.data.PendingRequests
import com.cornerstone.agency.data.SessionSummary
import com.cornerstone.agency.data.listProfileSessions
import com.cornerstone.agency.theme.AgencyTokens
import com.cornerstone.agency.ui.AppViewModel
import com.cornerstone.agency.ui.components.AgencyRow
import com.cornerstone.agency.ui.components.AgencyTextField
import com.cornerstone.agency.ui.components.BotAvatar
import com.cornerstone.agency.ui.components.EmptyState
import com.cornerstone.agency.ui.components.EyeIcon
import com.cornerstone.agency.ui.components.SectionKicker
import com.cornerstone.agency.ui.components.botAvatarColor
import com.cornerstone.agency.ui.components.relativeTime
import kotlinx.coroutines.launch

@Composable
fun BotsScreen(
    vm: AppViewModel,
    onOpenChat: (String) -> Unit,
    onOpenBot: (String) -> Unit = {},
    onNewAgent: () -> Unit = {},
) {
    val ui by vm.ui.collectAsState()
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space
    var query by remember { mutableStateOf("") }
    val openProfiles by PendingRequests.openProfiles.collectAsState()

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
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Bots",
                            style = MaterialTheme.typography.displayLarge,
                            color = colors.wordmark,
                            modifier = Modifier.weight(1f),
                        )
                        IconButton(onClick = onNewAgent) {
                            Icon(
                                Icons.Filled.Add,
                                contentDescription = "New agent",
                                tint = colors.secondary,
                            )
                        }
                    }
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
                        onLongClick = { onOpenBot(profile.name) },
                        leading = {
                            BotAvatar(
                                name = profile.title,
                                color = botAvatarColor(profile.name, profile.isDefault),
                            )
                        },
                        trailing = if (profile.name in openProfiles) {
                            { NeedsDot() }
                        } else null,
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
    val hideCronChatter by vm.hideCronChatter.collectAsState()
    val openSessions by PendingRequests.openSessions.collectAsState()
    val scope = rememberCoroutineScope()

    // Sessions grouped by bot, in roster order.
    var groups by remember {
        mutableStateOf<List<Pair<BotProfile, List<SessionSummary>>>>(emptyList())
    }
    var loading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf("") }
    // Cron job names for the chatter filter (display name + raw [bot:x] name).
    var cronNames by remember { mutableStateOf<Set<String>>(emptySet()) }

    LaunchedEffect(Unit) {
        cronNames = runCatching { vm.repo.cronJobs() }
            .getOrDefault(emptyList())
            .flatMap { listOf(it.name, it.displayName) }
            .toSet()
    }

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

    val visibleGroups = if (!hideCronChatter) groups else groups.map { (p, sessions) ->
        p to sessions.filterNot { s ->
            val hay = "${s.title}\n${s.preview}"
            val title = s.title.trim()
            hay.contains("running as a scheduled cron job", ignoreCase = true) ||
                cronNames.any { title == it || title.startsWith("$it ·") }
        }
    }.filter { it.second.isNotEmpty() }

    Column(Modifier.fillMaxSize()) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ScreenTitle("Chats", Modifier.weight(1f))
            IconButton(onClick = { vm.setHideCronChatter(!hideCronChatter) }) {
                Icon(
                    EyeIcon,
                    contentDescription = "Hide cron chatter",
                    tint = if (hideCronChatter) colors.gold else colors.tertiary,
                    modifier = Modifier.padding(end = space.lg),
                )
            }
        }
        when {
            loading && visibleGroups.isEmpty() -> Box(Modifier.fillMaxSize(), Alignment.Center) {
                CircularProgressIndicator(
                    color = colors.gold, strokeWidth = 2.dp,
                    modifier = Modifier.size(28.dp),
                )
            }

            visibleGroups.isEmpty() -> EmptyState(
                if (error.isEmpty()) "No chats yet" else "Couldn't load chats",
                if (error.isEmpty()) {
                    if (hideCronChatter && groups.isNotEmpty()) "All visible sessions are cron chatter"
                    else "Open a bot to start a conversation"
                } else error,
            )

            else -> LazyColumn(Modifier.fillMaxSize()) {
                visibleGroups.forEach { (profile, sessions) ->
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
                            meta = "",
                            onClick = { onOpenSession(profile.name, s.id) },
                            modifier = Modifier.padding(horizontal = space.sm),
                            leading = {
                                BotAvatar(
                                    name = profile.title,
                                    color = botAvatarColor(profile.name, profile.isDefault),
                                )
                            },
                            trailing = {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    if (s.id in openSessions) NeedsYouPill()
                                    val t = relativeTime(s.startedAt)
                                    if (t.isNotEmpty()) {
                                        Spacer(Modifier.width(space.xs))
                                        Text(
                                            text = t,
                                            style = MaterialTheme.typography.labelMedium,
                                            color = colors.tertiary,
                                        )
                                    }
                                }
                            },
                        )
                    }
                }
            }
        }
    }
}

/** Gold "needs you" pill for session rows with an unanswered request card. */
@Composable
private fun NeedsYouPill() {
    val colors = AgencyTokens.colors
    Text(
        text = "needs you",
        style = MaterialTheme.typography.labelSmall,
        color = colors.gold,
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(colors.gold.copy(alpha = 0.15f))
            .padding(horizontal = AgencyTokens.space.sm, vertical = 2.dp),
    )
}

/** Small gold dot on a bot row when one of its sessions needs input. */
@Composable
private fun NeedsDot() {
    Box(
        Modifier
            .size(8.dp)
            .clip(RoundedCornerShape(999.dp))
            .background(AgencyTokens.colors.gold),
    )
}

@Composable
private fun ScreenTitle(title: String, modifier: Modifier = Modifier) {
    val colors = AgencyTokens.colors
    Text(
        text = title,
        style = MaterialTheme.typography.displayLarge,
        color = colors.wordmark,
        modifier = modifier.padding(
            horizontal = AgencyTokens.space.lg,
            vertical = AgencyTokens.space.lg,
        ),
    )
}

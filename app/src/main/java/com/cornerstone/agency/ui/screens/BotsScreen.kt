package com.cornerstone.agency.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.cornerstone.agency.data.BotProfile
import com.cornerstone.agency.theme.AgencyTokens
import com.cornerstone.agency.ui.AppViewModel
import com.cornerstone.agency.ui.components.AgencyRow
import com.cornerstone.agency.ui.components.AgencyTextField
import com.cornerstone.agency.ui.components.BotAvatar
import com.cornerstone.agency.ui.components.EmptyState
import com.cornerstone.agency.ui.components.SectionKicker

private val AVATAR_COLORS = listOf(
    Color(0xFFFFD24A), // gold
    Color(0xFF8B5CF6), // violet
    Color(0xFF0053FD), // primary
    Color(0xFFCF806D), // warm
    Color(0xFF55A583), // success
    Color(0xFFE75E78), // destructive
)

private fun avatarColor(profile: BotProfile): Color {
    // Stable per-bot color; Kaori (default) takes gold.
    if (profile.isDefault || profile.name == "default") return AVATAR_COLORS[0]
    val idx = kotlin.math.abs(profile.name.hashCode()) % (AVATAR_COLORS.size - 1) + 1
    return AVATAR_COLORS[idx]
}

@Composable
fun BotsScreen(vm: AppViewModel) {
    val ui by vm.ui.collectAsState()
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space
    val context = LocalContext.current
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
                        onClick = {
                            Toast.makeText(
                                context,
                                "${profile.title} — detail view lands in a later milestone",
                                Toast.LENGTH_SHORT,
                            ).show()
                        },
                        leading = {
                            BotAvatar(name = profile.title, color = avatarColor(profile))
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
fun ChatsScreen() {
    Column(Modifier.fillMaxSize()) {
        ScreenTitle("Chats")
        EmptyState("No chats yet", "Conversations land here in a later milestone")
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

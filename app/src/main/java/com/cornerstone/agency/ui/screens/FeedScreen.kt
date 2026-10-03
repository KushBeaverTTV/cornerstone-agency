package com.cornerstone.agency.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.cornerstone.agency.data.FeedPost
import com.cornerstone.agency.theme.AgencyTokens
import com.cornerstone.agency.ui.AppViewModel
import com.cornerstone.agency.ui.components.AgencyCard
import com.cornerstone.agency.ui.components.AgencyTextField
import com.cornerstone.agency.ui.components.EmptyState
import com.cornerstone.agency.ui.components.SectionKicker
import com.cornerstone.agency.ui.components.relativeTime

@Composable
fun FeedScreen(vm: AppViewModel) {
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space
    val state by vm.companion.state.collectAsState()

    var editing by remember { mutableStateOf(false) }
    var draft by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize()) {
        Column(Modifier.padding(horizontal = space.lg)) {
            Spacer(Modifier.height(space.lg))
            Text("Feed", style = MaterialTheme.typography.displayLarge)
            Spacer(Modifier.height(space.md))
            AgencyCard(Modifier.fillMaxWidth()) {
                SectionKicker("Feed instructions")
                Spacer(Modifier.height(space.xs))
                Text(
                    state.feedInstructions,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colors.secondary,
                )
                Spacer(Modifier.height(space.sm))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    TextButton(
                        onClick = {
                            draft = state.feedInstructions
                            editing = true
                        },
                    ) { Text("Edit", color = colors.gold) }
                }
            }
            Spacer(Modifier.height(space.lg))
        }
        if (state.posts.isEmpty()) {
            EmptyState(
                "No posts yet",
                "Your Hermes agent will publish feed posts here once feed writing " +
                    "is set up on the gateway.",
                modifier = Modifier.fillMaxWidth(),
            )
        } else {
            LazyColumn(Modifier.fillMaxSize()) {
                items(state.posts, key = { it.id }) { post ->
                    Column(Modifier.padding(horizontal = space.lg)) {
                        PostCard(post, onOpen = { vm.companion.markPostRead(post.id) })
                        Spacer(Modifier.height(space.sm))
                    }
                }
            }
        }
    }

    if (editing) {
        AlertDialog(
            onDismissRequest = { editing = false },
            title = { Text("Feed instructions") },
            text = {
                AgencyTextField(
                    value = draft,
                    onValueChange = { draft = it },
                    placeholder = "What should your feed cover?",
                    singleLine = false,
                    modifier = Modifier.fillMaxWidth().height(220.dp),
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        vm.companion.saveFeedInstructions(draft)
                        editing = false
                    },
                ) { Text("Save", color = colors.gold) }
            },
            dismissButton = {
                TextButton(onClick = { editing = false }) { Text("Cancel") }
            },
        )
    }
}

@Composable
private fun PostCard(post: FeedPost, onOpen: () -> Unit) {
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space
    AgencyCard(
        Modifier.fillMaxWidth().clickable { onOpen() },
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (!post.read) {
                Box(
                    Modifier
                        .padding(end = space.sm)
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(colors.gold),
                )
            }
            Text(
                post.title,
                style = MaterialTheme.typography.titleMedium,
                color = colors.foreground,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f),
            )
        }
        if (post.body.isNotBlank()) {
            Spacer(Modifier.height(space.xs))
            Text(
                post.body,
                style = MaterialTheme.typography.bodyMedium,
                color = colors.secondary,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Spacer(Modifier.height(space.xs))
        Text(
            relativeTime(post.publishedAt / 1000.0),
            style = MaterialTheme.typography.labelSmall,
            color = colors.tertiary,
        )
    }
}

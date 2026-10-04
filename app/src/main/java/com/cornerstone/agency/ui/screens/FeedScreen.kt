package com.cornerstone.agency.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornerstone.agency.data.FeedPost
import com.cornerstone.agency.data.FeedSchedule
import com.cornerstone.agency.data.FeedWriter
import com.cornerstone.agency.data.groupPostsByDay
import com.cornerstone.agency.theme.AgencyTokens
import com.cornerstone.agency.ui.AppViewModel
import com.cornerstone.agency.ui.components.AgencyIcons
import com.cornerstone.agency.ui.components.AgencyTextField
import com.cornerstone.agency.ui.components.KeystoneMark
import com.cornerstone.agency.ui.components.KeystoneState
import com.cornerstone.agency.ui.components.PageHeader
import com.cornerstone.agency.ui.components.PrimaryButton
import com.cornerstone.agency.ui.components.SectionKicker
import com.cornerstone.agency.ui.components.botAvatarColor
import com.cornerstone.agency.ui.components.relativeTime
import com.cornerstone.agency.ui.isCompactHeight

/**
 * Feed (Muse): a personal paper written by your agent from a one-paragraph brief.
 * Empty state invites the first issue; after that, posts group by day with a topic kicker.
 */
@Composable
fun FeedScreen(vm: AppViewModel) {
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space
    val state by vm.companion.state.collectAsState()
    val writer by vm.feedWriter.state.collectAsState()
    val ui by vm.ui.collectAsState()
    val context = LocalContext.current
    val writing = writer is FeedWriter.State.Writing
    val botName = botDisplayName(ui.profiles, "default")
    val botColor = botAvatarColor("default", true)

    var editing by remember { mutableStateOf(false) }
    var draft by remember { mutableStateOf("") }
    var menu by remember { mutableStateOf(false) }
    var clearing by remember { mutableStateOf(false) }
    var scheduling by remember { mutableStateOf(false) }

    // Done/Failed are transient; clear them once the user has had a look.
    LaunchedEffect(writer) {
        if (writer is FeedWriter.State.Done) {
            kotlinx.coroutines.delay(2500)
            vm.feedWriter.dismiss()
        }
    }

    fun open(post: FeedPost) {
        vm.companion.markPostRead(post.id)
        if (post.link.startsWith("http")) {
            runCatching { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(post.link))) }
        }
    }

    Column(Modifier.fillMaxSize()) {
        PageHeader("Feed") {
            if (writing) {
                // The spinner is also the stop button — tap to cancel the run.
                IconButton(onClick = { vm.feedWriter.cancel() }) {
                    Box(contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(Modifier.size(22.dp), color = colors.gold, strokeWidth = 2.dp)
                        Box(Modifier.size(8.dp).background(colors.gold))
                    }
                }
            } else if (state.posts.isNotEmpty()) {
                IconButton(onClick = { vm.writeFeed() }) {
                    Icon(AgencyIcons.Spark, contentDescription = "Write a new issue", tint = colors.gold)
                }
            }
            Box {
                IconButton(onClick = { menu = true }) {
                    Icon(Icons.Filled.MoreVert, contentDescription = "Feed options", tint = colors.foreground)
                }
                DropdownMenu(expanded = menu, onDismissRequest = { menu = false }) {
                    DropdownMenuItem(
                        text = { Text("Edit the brief") },
                        onClick = { menu = false; draft = state.feedInstructions; editing = true },
                    )
                    DropdownMenuItem(
                        text = {
                            Text(
                                if (state.feedSchedule.enabled) "Daily issue · ${state.feedSchedule.label()}"
                                else "Daily issue · Off",
                            )
                        },
                        onClick = { menu = false; scheduling = true },
                    )
                    if (state.posts.any { !it.read }) {
                        DropdownMenuItem(
                            text = { Text("Mark all read") },
                            onClick = { menu = false; vm.companion.markAllPostsRead() },
                        )
                    }
                    if (state.posts.isNotEmpty()) {
                        DropdownMenuItem(
                            text = { Text("Clear feed") },
                            onClick = { menu = false; clearing = true },
                        )
                    }
                }
            }
        }

        // Writer status strip — sits under the header while a run is live or just finished.
        when (val w = writer) {
            is FeedWriter.State.Writing -> if (state.posts.isNotEmpty()) {
                StatusStrip("$botName · ${w.status}", colors.gold, action = "Stop", onDismiss = { vm.feedWriter.cancel() })
            }
            is FeedWriter.State.Failed -> StatusStrip(w.message, colors.destructive, onDismiss = { vm.feedWriter.dismiss() })
            is FeedWriter.State.Done -> StatusStrip(
                if (w.added == 0) "Nothing new since last time" else "$botName added ${w.added} ${if (w.added == 1) "post" else "posts"}",
                colors.success,
            )
            FeedWriter.State.Idle -> Unit
        }

        if (state.posts.isEmpty()) {
            EmptyFeed(
                botName = botName,
                botColor = botColor,
                brief = state.feedInstructions,
                schedule = state.feedSchedule,
                writing = writing,
                status = (writer as? FeedWriter.State.Writing)?.status,
                onWrite = { vm.writeFeed() },
                onCancel = { vm.feedWriter.cancel() },
                onEditBrief = { draft = state.feedInstructions; editing = true },
            )
        } else {
            val groups = remember(state.posts) { groupPostsByDay(state.posts) }
            LazyColumn(
                Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = space.xl),
            ) {
                groups.forEach { (day, posts) ->
                    item(key = "day-$day") {
                        SectionKicker(
                            day,
                            modifier = Modifier.padding(start = space.lg, top = space.md, bottom = space.xs),
                        )
                    }
                    items(posts, key = { it.id }) { post ->
                        PostRow(post, onOpen = { open(post) })
                    }
                }
            }
        }
    }

    if (editing) {
        AlertDialog(
            onDismissRequest = { editing = false },
            title = { Text("What should the feed cover?") },
            text = {
                AgencyTextField(
                    value = draft,
                    onValueChange = { draft = it },
                    placeholder = "Interests, tone, what to skip…",
                    singleLine = false,
                    modifier = Modifier.fillMaxWidth().heightIn(min = 120.dp, max = 260.dp),
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

    if (scheduling) {
        ScheduleDialog(
            current = state.feedSchedule,
            botName = botName,
            onDismiss = { scheduling = false },
            onSave = { vm.setFeedSchedule(it); scheduling = false },
        )
    }

    if (clearing) {
        AlertDialog(
            onDismissRequest = { clearing = false },
            title = { Text("Clear the feed?") },
            text = { Text("Removes every post. The brief stays; the next issue starts fresh.") },
            confirmButton = {
                TextButton(onClick = { vm.companion.clearPosts(); clearing = false }) {
                    Text("Clear", color = colors.destructive)
                }
            },
            dismissButton = { TextButton(onClick = { clearing = false }) { Text("Cancel") } },
        )
    }
}

/** Daily issue: on/off plus a time. Saving re-arms the scheduler. */
@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
private fun ScheduleDialog(
    current: FeedSchedule,
    botName: String,
    onDismiss: () -> Unit,
    onSave: (FeedSchedule) -> Unit,
) {
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space
    var enabled by remember { mutableStateOf(current.enabled) }
    val time = androidx.compose.material3.rememberTimePickerState(
        initialHour = current.hour, initialMinute = current.minute, is24Hour = false,
    )
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Daily issue") },
        text = {
            Column {
                Row(
                    Modifier.fillMaxWidth().heightIn(min = 48.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column(Modifier.weight(1f)) {
                        Text("Write every morning", style = MaterialTheme.typography.bodyLarge, color = colors.foreground)
                        Text(
                            "$botName writes while you sleep; you get a quiet notification.",
                            style = MaterialTheme.typography.bodySmall, color = colors.secondary,
                        )
                    }
                    Spacer(Modifier.width(space.md))
                    androidx.compose.material3.Switch(
                        checked = enabled, onCheckedChange = { enabled = it },
                        colors = androidx.compose.material3.SwitchDefaults.colors(
                            checkedThumbColor = colors.onPrimary, checkedTrackColor = colors.gold,
                        ),
                    )
                }
                if (enabled) {
                    Spacer(Modifier.height(space.md))
                    androidx.compose.material3.TimeInput(
                        state = time,
                        modifier = Modifier.fillMaxWidth(),
                        colors = androidx.compose.material3.TimePickerDefaults.colors(
                            timeSelectorSelectedContainerColor = colors.gold.copy(alpha = 0.18f),
                            timeSelectorSelectedContentColor = colors.foreground,
                            timeSelectorUnselectedContainerColor = colors.card,
                            timeSelectorUnselectedContentColor = colors.foreground,
                            periodSelectorSelectedContainerColor = colors.gold.copy(alpha = 0.18f),
                            periodSelectorSelectedContentColor = colors.foreground,
                            periodSelectorUnselectedContentColor = colors.secondary,
                            periodSelectorBorderColor = colors.stroke,
                        ),
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = { onSave(FeedSchedule(enabled, time.hour, time.minute)) }) {
                Text("Save", color = colors.gold)
            }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } },
    )
}

@Composable
private fun StatusStrip(
    text: String,
    tone: androidx.compose.ui.graphics.Color,
    action: String = "Dismiss",
    onDismiss: (() -> Unit)? = null,
) {
    val space = AgencyTokens.space
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = space.lg, vertical = space.xs)
            .clip(CircleShape)
            .background(tone.copy(alpha = 0.12f))
            .then(if (onDismiss != null) Modifier.clickable(onClick = onDismiss) else Modifier)
            .padding(horizontal = space.lg, vertical = space.sm),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text, style = MaterialTheme.typography.labelMedium, color = tone,
            maxLines = 2, overflow = TextOverflow.Ellipsis, modifier = Modifier.weight(1f),
        )
        if (onDismiss != null) {
            Text(action, style = MaterialTheme.typography.labelMedium, color = tone)
        }
    }
}

@Composable
private fun EmptyFeed(
    botName: String,
    botColor: androidx.compose.ui.graphics.Color,
    brief: String,
    schedule: FeedSchedule,
    writing: Boolean,
    status: String?,
    onWrite: () -> Unit,
    onCancel: () -> Unit,
    onEditBrief: () -> Unit,
) {
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 28.dp, vertical = space.lg),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        KeystoneMark(
            state = if (writing) KeystoneState.WORKING else KeystoneState.IDLE,
            size = if (isCompactHeight()) 72.dp else 120.dp,
            color = botColor,
        )
        Spacer(Modifier.height(space.lg))
        Text(
            if (writing) "$botName is writing" else "Your paper, by $botName",
            style = MaterialTheme.typography.displaySmall,
            textAlign = TextAlign.Center,
        )
        Spacer(Modifier.height(space.sm))
        Text(
            if (writing) (status ?: "Working…")
            else "A short, skimmable issue on what you care about. Written on request, read in a minute.",
            style = MaterialTheme.typography.bodyMedium,
            color = colors.secondary,
            textAlign = TextAlign.Center,
            modifier = Modifier.widthIn(max = 320.dp),
        )
        if (writing) {
            Spacer(Modifier.height(space.lg))
            TextButton(onClick = onCancel) { Text("Stop", color = colors.secondary) }
        } else {
            Spacer(Modifier.height(space.xl))
            PrimaryButton(
                label = "Write today's issue",
                onClick = onWrite,
                modifier = Modifier.widthIn(min = 220.dp),
            )
            if (schedule.enabled) {
                Spacer(Modifier.height(space.sm))
                Text(
                    "Next issue arrives at ${schedule.label()}",
                    style = MaterialTheme.typography.labelMedium, color = colors.tertiary,
                )
            }
            Spacer(Modifier.height(space.lg))
            Column(
                Modifier
                    .widthIn(max = 360.dp)
                    .clip(RoundedCornerShape(AgencyTokens.shapes.md))
                    .clickable(role = Role.Button, onClickLabel = "Edit the brief", onClick = onEditBrief)
                    .padding(space.md),
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    SectionKicker("The brief", Modifier.weight(1f))
                    Icon(AgencyIcons.Edit, contentDescription = null, tint = colors.tertiary, modifier = Modifier.size(16.dp))
                }
                Spacer(Modifier.height(space.xs))
                Text(
                    brief,
                    style = MaterialTheme.typography.bodySmall,
                    color = colors.tertiary,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

/** One story: topic kicker + time, headline in the display face, a line or three, source. */
@Composable
private fun PostRow(post: FeedPost, onOpen: () -> Unit) {
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = space.sm)
            .clip(RoundedCornerShape(AgencyTokens.shapes.md))
            .clickable(onClick = onOpen)
            .padding(horizontal = space.sm, vertical = space.md),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (!post.read) {
                Box(Modifier.size(7.dp).clip(CircleShape).background(colors.gold))
                Spacer(Modifier.width(space.sm))
            }
            SectionKicker(post.topic.ifBlank { "Feed" }, Modifier.weight(1f))
            Text(
                relativeTime(post.publishedAt / 1000.0),
                style = MaterialTheme.typography.labelSmall,
                color = colors.tertiary,
            )
        }
        Spacer(Modifier.height(6.dp))
        Text(
            post.title,
            style = MaterialTheme.typography.headlineSmall,
            color = if (post.read) colors.secondary else colors.foreground,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
        )
        if (post.body.isNotBlank()) {
            Spacer(Modifier.height(6.dp))
            Text(
                post.body,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp, lineHeight = 20.sp),
                color = colors.secondary,
                maxLines = 6,
                overflow = TextOverflow.Ellipsis,
            )
        }
        if (post.link.startsWith("http")) {
            Spacer(Modifier.height(space.sm))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    runCatching { Uri.parse(post.link).host?.removePrefix("www.") }.getOrNull() ?: "Source",
                    style = MaterialTheme.typography.labelMedium,
                    color = colors.gold,
                )
                Spacer(Modifier.width(4.dp))
                Icon(AgencyIcons.OpenLink, contentDescription = "Open source", tint = colors.gold, modifier = Modifier.size(14.dp))
            }
        }
    }
}

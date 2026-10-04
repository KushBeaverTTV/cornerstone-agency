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
import com.cornerstone.agency.data.FeedWriter
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
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

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
    val compact = isCompactHeight()
    val context = LocalContext.current
    val writing = writer is FeedWriter.State.Writing
    val botName = botDisplayName(ui.profiles, "default")
    val botColor = botAvatarColor("default", true)

    var editing by remember { mutableStateOf(false) }
    var draft by remember { mutableStateOf("") }
    var menu by remember { mutableStateOf(false) }

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
        if (!compact) {
            PageHeader("Feed") {
                if (writing) {
                    CircularProgressIndicator(
                        Modifier.padding(horizontal = 12.dp).size(20.dp),
                        color = colors.gold, strokeWidth = 2.dp,
                    )
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
                        if (state.posts.any { !it.read }) {
                            DropdownMenuItem(
                                text = { Text("Mark all read") },
                                onClick = { menu = false; vm.companion.markAllPostsRead() },
                            )
                        }
                        if (state.posts.isNotEmpty()) {
                            DropdownMenuItem(
                                text = { Text("Clear feed") },
                                onClick = { menu = false; vm.companion.clearPosts() },
                            )
                        }
                    }
                }
            }
        }

        // Writer status strip — sits under the header while a run is live or just finished.
        when (val w = writer) {
            is FeedWriter.State.Writing -> if (state.posts.isNotEmpty()) StatusStrip("$botName · ${w.status}", colors.gold)
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
                writing = writing,
                status = (writer as? FeedWriter.State.Writing)?.status,
                onWrite = { vm.writeFeed() },
                onEditBrief = { draft = state.feedInstructions; editing = true },
            )
        } else {
            val groups = remember(state.posts) { groupByDay(state.posts) }
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
}

@Composable
private fun StatusStrip(text: String, tone: androidx.compose.ui.graphics.Color, onDismiss: (() -> Unit)? = null) {
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
            Text("Dismiss", style = MaterialTheme.typography.labelMedium, color = tone)
        }
    }
}

@Composable
private fun EmptyFeed(
    botName: String,
    botColor: androidx.compose.ui.graphics.Color,
    brief: String,
    writing: Boolean,
    status: String?,
    onWrite: () -> Unit,
    onEditBrief: () -> Unit,
) {
    val colors = AgencyTokens.colors
    val space = AgencyTokens.space
    val compact = isCompactHeight()
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
            size = if (compact) 72.dp else 120.dp,
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
        if (!writing) {
            Spacer(Modifier.height(space.xl))
            PrimaryButton(
                label = "Write today's issue",
                onClick = onWrite,
                modifier = Modifier.widthIn(min = 220.dp),
            )
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

/** "Today" / "Yesterday" / "Mon, Sep 29" buckets, newest first. */
private fun groupByDay(posts: List<FeedPost>): List<Pair<String, List<FeedPost>>> {
    val fmt = SimpleDateFormat("EEE, MMM d", Locale.US)
    val today = Calendar.getInstance()
    fun dayKey(ms: Long): String {
        val c = Calendar.getInstance().apply { timeInMillis = ms }
        val sameYear = c.get(Calendar.YEAR) == today.get(Calendar.YEAR)
        val dayDiff = today.get(Calendar.DAY_OF_YEAR) - c.get(Calendar.DAY_OF_YEAR)
        return when {
            sameYear && dayDiff == 0 -> "Today"
            sameYear && dayDiff == 1 -> "Yesterday"
            else -> fmt.format(Date(ms))
        }
    }
    return posts.sortedByDescending { it.publishedAt }
        .groupBy { dayKey(it.publishedAt) }
        .map { it.key to it.value }
}

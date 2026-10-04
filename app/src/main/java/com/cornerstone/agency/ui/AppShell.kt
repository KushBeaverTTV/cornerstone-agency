package com.cornerstone.agency.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.cornerstone.agency.theme.AgencyTokens
import com.cornerstone.agency.ui.components.AgencyIcons
import com.cornerstone.agency.ui.components.AgencyScaffold
import com.cornerstone.agency.ui.components.StatusBanner
import com.cornerstone.agency.ui.screens.ArtifactsScreen
import com.cornerstone.agency.ui.screens.ChatsScreen
import com.cornerstone.agency.ui.screens.FeedScreen
import com.cornerstone.agency.ui.screens.GoalsScreen
import com.cornerstone.agency.ui.screens.MoreScreen

/** Muse-style five: a surface per thing you do, no nested chips. Routines live under Me. */
private enum class Tab(val label: String, val icon: ImageVector) {
    CHATS("Chats", AgencyIcons.Chats),
    FEED("Feed", AgencyIcons.Feed),
    GOALS("Goals", AgencyIcons.Goals),
    FILES("Files", AgencyIcons.Files),
    ME("Me", AgencyIcons.Me),
}

/** Material3 Compact height class = window height < 480dp (cover displays, landscape). */
@Composable
fun isCompactHeight(): Boolean =
    androidx.compose.ui.platform.LocalConfiguration.current.screenHeightDp < 480

@Composable
fun AppShell(
    vm: AppViewModel,
    onOpenChat: (profile: String) -> Unit = {},
    onOpenSession: (profile: String, sessionId: String?) -> Unit = { _, _ -> },
    onOpenBot: (profile: String) -> Unit = {},
    onNewAgent: () -> Unit = {},
    onOpenJobs: () -> Unit = {},
    onOpenSystem: () -> Unit = {},
    onOpenSkills: () -> Unit = {},
    onOpenPrima: () -> Unit = {},
    onOpenPrimaSession: (sessionId: String?) -> Unit = {},
) {
    val ui by vm.ui.collectAsState()
    val colors = AgencyTokens.colors
    var tab by rememberSaveable { mutableIntStateOf(Tab.CHATS.ordinal) }
    val needsAttention by com.cornerstone.agency.data.PendingRequests.anyOpen.collectAsState()
    val companion by vm.companion.state.collectAsState()
    val unreadPosts = companion.posts.count { !it.read }

    // Android 13+: heads-up request/message notifications need the runtime grant.
    val context = androidx.compose.ui.platform.LocalContext.current
    val notifPerm = androidx.activity.compose.rememberLauncherForActivityResult(
        androidx.activity.result.contract.ActivityResultContracts.RequestPermission(),
    ) {}
    androidx.compose.runtime.LaunchedEffect(Unit) {
        if (
            android.os.Build.VERSION.SDK_INT >= 33 &&
            context.checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) !=
                android.content.pm.PackageManager.PERMISSION_GRANTED
        ) {
            notifPerm.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    val compact = isCompactHeight()
    fun badge(t: Tab): Boolean = when (t) {
        Tab.CHATS -> needsAttention
        Tab.FEED -> unreadPosts > 0
        else -> false
    }

    AgencyScaffold(
        topBar = {
            if (compact) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .background(colors.sidebar)
                        .statusBarsPadding()
                        .padding(horizontal = 8.dp, vertical = 2.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Tab.entries.forEach { t ->
                        TabButton(
                            tab = t, selected = tab == t.ordinal, badge = badge(t),
                            showLabel = false, onClick = { tab = t.ordinal },
                        )
                    }
                }
            }
        },
        bottomBar = {
            if (!compact) {
                Column(Modifier.fillMaxWidth().background(colors.sidebar)) {
                    Box(Modifier.fillMaxWidth().height(1.dp).background(colors.hairline))
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .navigationBarsPadding()
                            .padding(horizontal = 8.dp, vertical = 6.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Tab.entries.forEach { t ->
                            TabButton(
                                tab = t, selected = tab == t.ordinal, badge = badge(t),
                                showLabel = true, onClick = { tab = t.ordinal },
                                modifier = Modifier.weight(1f),
                            )
                        }
                    }
                }
            }
        },
    ) { padding ->
        Column(padding.fillMaxSize()) {
            if (!ui.reachable) {
                StatusBanner("Dashboard unreachable — check the URL or VPN")
            }
            Box(Modifier.weight(1f).fillMaxWidth()) {
                when (Tab.entries[tab]) {
                    Tab.CHATS -> ChatsScreen(
                        vm,
                        onOpenSession = onOpenSession,
                        onOpenChat = onOpenChat,
                        onOpenBot = onOpenBot,
                        onNewAgent = onNewAgent,
                        onOpenPrima = onOpenPrima,
                        onOpenPrimaSession = onOpenPrimaSession,
                    )
                    Tab.FEED -> FeedScreen(vm)
                    Tab.GOALS -> GoalsScreen(vm)
                    Tab.FILES -> ArtifactsScreen(vm)
                    Tab.ME -> MoreScreen(vm, onOpenSystem, onOpenSkills, onOpenJobs = onOpenJobs)
                }
            }
        }
    }
}

/**
 * One tab: outlined glyph, label, and a short gold bar underneath when selected.
 * No pill indicator — the bar reads as a chapter mark, in keeping with the keystone.
 */
@Composable
private fun TabButton(
    tab: Tab,
    selected: Boolean,
    badge: Boolean,
    showLabel: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = AgencyTokens.colors
    val tint by animateColorAsState(
        if (selected) colors.gold else colors.tertiary, tween(180), label = "tabTint",
    )
    val bar by animateDpAsState(if (selected) 18.dp else 0.dp, tween(220), label = "tabBar")
    val interaction = remember { MutableInteractionSource() }
    Column(
        modifier
            .clip(CircleShape)
            .clickable(
                interactionSource = interaction,
                indication = null,
                role = Role.Tab,
                onClick = onClick,
            )
            .semantics { this.selected = selected; contentDescription = tab.label }
            .heightIn(min = 48.dp)
            .padding(vertical = if (showLabel) 6.dp else 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Box {
            Icon(tab.icon, contentDescription = null, tint = tint, modifier = Modifier.size(24.dp))
            if (badge) {
                Box(
                    Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 1.dp)
                        .size(7.dp)
                        .clip(CircleShape)
                        .background(colors.gold),
                )
            }
        }
        if (showLabel) {
            Spacer(Modifier.height(4.dp))
            Text(
                tab.label,
                style = MaterialTheme.typography.labelMedium,
                color = tint,
                maxLines = 1,
            )
            Spacer(Modifier.height(3.dp))
            Box(
                Modifier
                    .height(2.dp)
                    .width(bar)
                    .clip(CircleShape)
                    .background(if (bar > 0.dp) colors.gold else Color.Transparent),
            )
        }
    }
}

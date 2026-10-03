package com.cornerstone.agency.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Person
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.cornerstone.agency.theme.AgencyTokens
import com.cornerstone.agency.ui.components.AgencyScaffold
import com.cornerstone.agency.ui.components.FolderIcon
import com.cornerstone.agency.ui.components.StatusBanner
import com.cornerstone.agency.ui.screens.BotsScreen
import com.cornerstone.agency.ui.screens.ChatsScreen
import com.cornerstone.agency.ui.screens.JobsScreen
import com.cornerstone.agency.ui.screens.MoreScreen
import com.cornerstone.agency.ui.screens.FeedScreen
import com.cornerstone.agency.ui.screens.GoalsScreen
import com.cornerstone.agency.ui.screens.ArtifactsScreen

private enum class Tab(val label: String, val icon: ImageVector) {
    CHATS("Chats", Icons.Outlined.MailOutline),
    ACTIVITY("Activity", Icons.Outlined.List),
    ME("Me", Icons.Outlined.Person),
}

private enum class ActivityPane(val label: String) { FEED("Feed"), GOALS("Goals"), FILES("Files"), JOBS("Jobs") }

@Composable
private fun ActivityScreen(vm: AppViewModel, pane: Int, onPane: (Int) -> Unit, onOpenJob: (String) -> Unit) {
    val colors = AgencyTokens.colors
    Column(Modifier.fillMaxSize()) {
        androidx.compose.foundation.layout.Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp),
        ) {
            ActivityPane.entries.forEach { p ->
                val sel = pane == p.ordinal
                Box(
                    Modifier
                        .weight(1f)
                        .heightIn(min = 48.dp)
                        .clip(CircleShape)
                        .background(if (sel) colors.primarySoft else colors.card)
                        .clickable { onPane(p.ordinal) },
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        p.label,
                        style = androidx.compose.material3.MaterialTheme.typography.labelLarge,
                        color = if (sel) colors.gold else colors.secondary,
                    )
                }
            }
        }
        Box(Modifier.weight(1f).fillMaxWidth()) {
            when (ActivityPane.entries[pane]) {
                ActivityPane.FEED -> FeedScreen(vm)
                ActivityPane.GOALS -> GoalsScreen(vm)
                ActivityPane.FILES -> ArtifactsScreen(vm)
                ActivityPane.JOBS -> JobsScreen(vm, onOpenJob)
            }
        }
    }
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
    onOpenJob: (jobId: String) -> Unit = {},
    onOpenSystem: () -> Unit = {},
    onOpenSkills: () -> Unit = {},
    onOpenPrima: () -> Unit = {},
    onOpenPrimaSessions: () -> Unit = {},
    onOpenPrimaSession: (sessionId: String?) -> Unit = {},
) {
    val ui by vm.ui.collectAsState()
    val colors = AgencyTokens.colors
    var tab by rememberSaveable { mutableIntStateOf(Tab.CHATS.ordinal) }
    var showBots by rememberSaveable { mutableStateOf(false) }
    var activityPane by rememberSaveable { mutableIntStateOf(0) }
    val needsAttention by com.cornerstone.agency.data.PendingRequests.anyOpen.collectAsState()

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

    AgencyScaffold(
        topBar = {
            if (compact) {
                androidx.compose.foundation.layout.Row(
                    Modifier
                        .fillMaxWidth()
                        .background(colors.sidebar)
                        .padding(horizontal = 8.dp, vertical = 2.dp),
                    horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Tab.entries.forEach { t ->
                        Box(
                            Modifier
                                .clip(CircleShape)
                                .background(
                                    if (tab == t.ordinal) colors.primarySoft
                                    else androidx.compose.ui.graphics.Color.Transparent,
                                )
                                .clickable { tab = t.ordinal; showBots = false }
                                .padding(8.dp),
                        ) {
                            Box {
                                Icon(
                                    t.icon,
                                    contentDescription = t.label,
                                    tint = if (tab == t.ordinal) colors.foreground else colors.tertiary,
                                    modifier = Modifier.size(28.dp),
                                )
                                if (t == Tab.CHATS && needsAttention) {
                                    Box(
                                        Modifier
                                            .align(Alignment.TopEnd)
                                            .size(7.dp)
                                            .clip(CircleShape)
                                            .background(colors.gold),
                                    )
                                }
                            }
                        }
                    }
                }
            }
        },
        bottomBar = {
            if (!compact) {
                NavigationBar(containerColor = colors.sidebar) {
                Tab.entries.forEach { t ->
                    NavigationBarItem(
                        selected = tab == t.ordinal,
                        onClick = { tab = t.ordinal; showBots = false },
                        icon = {
                            Box {
                                Icon(t.icon, contentDescription = t.label)
                                if (t == Tab.CHATS && needsAttention) {
                                    Box(
                                        Modifier
                                            .align(Alignment.TopEnd)
                                            .size(7.dp)
                                            .clip(CircleShape)
                                            .background(colors.gold),
                                    )
                                }
                            }
                        },
                        label = { Text(t.label) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = colors.gold,
                            selectedTextColor = colors.gold,
                            indicatorColor = colors.primarySoft,
                            unselectedIconColor = colors.tertiary,
                            unselectedTextColor = colors.tertiary,
                        ),
                    )
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
                if (showBots) {
                    androidx.activity.compose.BackHandler { showBots = false }
                    Column(Modifier.fillMaxSize()) {
                        androidx.compose.material3.IconButton(onClick = { showBots = false }) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                "Back", tint = colors.foreground,
                            )
                        }
                        BotsScreen(
                            vm, onOpenChat, onOpenBot, onNewAgent,
                            onOpenPrima = onOpenPrima,
                            onOpenPrimaSessions = onOpenPrimaSessions,
                            onOpenSession = onOpenSession,
                            onOpenPrimaSession = onOpenPrimaSession,
                        )
                    }
                } else {
                    when (Tab.entries[tab]) {
                        Tab.CHATS -> ChatsScreen(
                            vm, onOpenSession, onOpenPrimaSession,
                            onOpenBots = { showBots = true },
                            onOpenBot = onOpenChat,
                        )
                        Tab.ACTIVITY -> ActivityScreen(vm, activityPane, { activityPane = it }, onOpenJob)
                        Tab.ME -> MoreScreen(vm, onOpenSystem, onOpenSkills, onOpenJobs = { activityPane = ActivityPane.JOBS.ordinal; tab = Tab.ACTIVITY.ordinal })
                    }
                }
            }
        }
    }
}

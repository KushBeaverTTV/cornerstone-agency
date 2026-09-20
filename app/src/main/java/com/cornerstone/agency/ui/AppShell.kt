package com.cornerstone.agency.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.cornerstone.agency.theme.AgencyTokens
import com.cornerstone.agency.ui.components.AgencyScaffold
import com.cornerstone.agency.ui.components.StatusBanner
import com.cornerstone.agency.ui.screens.BotsScreen
import com.cornerstone.agency.ui.screens.ChatsScreen
import com.cornerstone.agency.ui.screens.JobsScreen
import com.cornerstone.agency.ui.screens.MoreScreen

private enum class Tab(val label: String, val icon: ImageVector) {
    BOTS("Bots", Icons.Outlined.Face),
    CHATS("Chats", Icons.Outlined.MailOutline),
    JOBS("Jobs", Icons.Outlined.DateRange),
    MORE("More", Icons.Outlined.MoreVert),
}

@Composable
fun AppShell(
    vm: AppViewModel,
    onOpenChat: (profile: String) -> Unit = {},
    onOpenSession: (profile: String, sessionId: String) -> Unit = { _, _ -> },
    onOpenBot: (profile: String) -> Unit = {},
    onNewAgent: () -> Unit = {},
    onOpenJob: (jobId: String) -> Unit = {},
    onOpenSystem: () -> Unit = {},
    onOpenSkills: () -> Unit = {},
) {
    val ui by vm.ui.collectAsState()
    val colors = AgencyTokens.colors
    var tab by rememberSaveable { mutableIntStateOf(Tab.BOTS.ordinal) }
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

    AgencyScaffold(
        bottomBar = {
            NavigationBar(containerColor = colors.sidebar) {
                Tab.entries.forEach { t ->
                    NavigationBarItem(
                        selected = tab == t.ordinal,
                        onClick = { tab = t.ordinal },
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
                            selectedIconColor = colors.foreground,
                            selectedTextColor = colors.foreground,
                            indicatorColor = colors.primarySoft,
                            unselectedIconColor = colors.tertiary,
                            unselectedTextColor = colors.tertiary,
                        ),
                    )
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
                    Tab.BOTS -> BotsScreen(vm, onOpenChat, onOpenBot, onNewAgent)
                    Tab.CHATS -> ChatsScreen(vm, onOpenSession)
                    Tab.JOBS -> JobsScreen(vm, onOpenJob)
                    Tab.MORE -> MoreScreen(vm, onOpenSystem, onOpenSkills)
                }
            }
        }
    }
}

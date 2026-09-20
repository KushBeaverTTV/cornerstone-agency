package com.cornerstone.agency.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import android.net.Uri
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cornerstone.agency.theme.AgencyTokens
import com.cornerstone.agency.ui.screens.BotDetailScreen
import com.cornerstone.agency.ui.screens.ChatScreen
import com.cornerstone.agency.ui.screens.ConnectScreen
import com.cornerstone.agency.ui.screens.NewAgentScreen
import com.cornerstone.agency.ui.screens.SessionsScreen

object Routes {
    const val CONNECT = "connect"
    const val SHELL = "shell"
    const val CHAT = "chat/{profile}?session={session}"
    const val SESSIONS = "sessions/{profile}"
    const val BOT = "bot/{profile}"
    const val BOT_NEW = "bot/new"

    fun chat(profile: String, session: String? = null): String =
        "chat/${Uri.encode(profile)}" +
            (session?.let { "?session=${Uri.encode(it)}" } ?: "")

    fun sessions(profile: String): String = "sessions/${Uri.encode(profile)}"

    fun bot(profile: String): String = "bot/${Uri.encode(profile)}"
}

@Composable
fun AppNav(vm: AppViewModel, navController: NavHostController = rememberNavController()) {
    val ui by vm.ui.collectAsState()

    LaunchedEffect(ui.connected, ui.booting) {
        if (ui.booting) return@LaunchedEffect
        if (ui.connected) {
            navController.navigate(Routes.SHELL) {
                popUpTo(Routes.CONNECT) { inclusive = true }
            }
        } else {
            navController.navigate(Routes.CONNECT) {
                popUpTo(Routes.SHELL) { inclusive = true }
            }
        }
    }

    NavHost(navController = navController, startDestination = Routes.CONNECT) {
        composable(Routes.CONNECT) {
            if (ui.booting) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = AgencyTokens.colors.gold)
                }
            } else {
                ConnectScreen(vm)
            }
        }
        composable(Routes.SHELL) {
            AppShell(
                vm,
                onOpenChat = { profile ->
                    navController.navigate(Routes.chat(profile))
                },
                onOpenSession = { profile, sessionId ->
                    navController.navigate(Routes.chat(profile, sessionId))
                },
                onOpenBot = { profile ->
                    navController.navigate(Routes.bot(profile))
                },
                onNewAgent = { navController.navigate(Routes.BOT_NEW) },
            )
        }
        composable(
            route = Routes.CHAT,
            arguments = listOf(
                navArgument("profile") { type = NavType.StringType },
                navArgument("session") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                },
            ),
        ) { entry ->
            val profile = entry.arguments?.getString("profile") ?: return@composable
            ChatScreen(
                vm = vm,
                profileName = profile,
                sessionId = entry.arguments?.getString("session"),
                onBack = { navController.popBackStack() },
                onOpenSessions = { navController.navigate(Routes.sessions(profile)) },
                onOpenBotSettings = { navController.navigate(Routes.bot(profile)) },
            )
        }
        composable(
            route = Routes.SESSIONS,
            arguments = listOf(navArgument("profile") { type = NavType.StringType }),
        ) { entry ->
            val profile = entry.arguments?.getString("profile") ?: return@composable
            SessionsScreen(
                vm = vm,
                profileName = profile,
                onBack = { navController.popBackStack() },
                onOpenSession = { sessionId ->
                    navController.navigate(Routes.chat(profile, sessionId))
                },
            )
        }
        composable(Routes.BOT_NEW) {
            NewAgentScreen(
                vm = vm,
                onBack = { navController.popBackStack() },
                onCreated = { name ->
                    navController.navigate(Routes.bot(name)) {
                        popUpTo(Routes.SHELL)
                    }
                },
            )
        }
        composable(
            route = Routes.BOT,
            arguments = listOf(navArgument("profile") { type = NavType.StringType }),
        ) { entry ->
            val profile = entry.arguments?.getString("profile") ?: return@composable
            BotDetailScreen(
                vm = vm,
                profileName = profile,
                onBack = { navController.popBackStack() },
                onOpenSessions = { navController.navigate(Routes.sessions(profile)) },
                onDeleted = { navController.popBackStack(Routes.SHELL, inclusive = false) },
            )
        }
    }
}

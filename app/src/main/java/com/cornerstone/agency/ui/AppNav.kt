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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cornerstone.agency.theme.AgencyTokens
import com.cornerstone.agency.ui.screens.ConnectScreen

object Routes {
    const val CONNECT = "connect"
    const val SHELL = "shell"
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
            AppShell(vm)
        }
    }
}

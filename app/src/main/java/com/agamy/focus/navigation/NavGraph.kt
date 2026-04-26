package com.agamy.focus.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.agamy.focus.domain.Routes
import com.agamy.focus.presentation.PomodoroViewModel
import com.agamy.focus.presentation.screen.PomodoroScreen
import com.agamy.focus.presentation.screen.Splash

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Splash.route
    ) {
        composable(Routes.Splash.route) {
            Splash(navController = navController)
        }
        composable(Routes.Home.route) {
            val viewModel: PomodoroViewModel = viewModel()
            PomodoroScreen(viewModel)
        }
    }
}
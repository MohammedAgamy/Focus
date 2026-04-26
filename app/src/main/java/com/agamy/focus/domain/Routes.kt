package com.agamy.focus.domain

sealed class Routes(val route : String) {
    object Splash : Routes("splash")
    object Home   : Routes("home")
}
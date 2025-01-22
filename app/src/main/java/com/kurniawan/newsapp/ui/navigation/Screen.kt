package com.kurniawan.newsapp.ui.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Bookmark : Screen("bookmark")
    data object Profile : Screen("profile")
    data object Detail : Screen("detail")
}
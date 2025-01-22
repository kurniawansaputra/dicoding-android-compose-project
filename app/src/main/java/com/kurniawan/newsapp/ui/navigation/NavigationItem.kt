package com.kurniawan.newsapp.ui.navigation

import androidx.compose.ui.graphics.painter.Painter

data class NavigationItem (
    val title: String,
    val selectedIcon: Painter,
    val unselectedIcon: Painter,
    val screen: Screen
)
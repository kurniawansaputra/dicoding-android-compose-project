package com.kurniawan.newsapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kurniawan.newsapp.data.source.remote.response.ArticlesItem
import com.kurniawan.newsapp.ui.navigation.NavigationItem
import com.kurniawan.newsapp.ui.navigation.Screen
import com.kurniawan.newsapp.ui.screen.bookmark.BookmarkScreen
import com.kurniawan.newsapp.ui.screen.detailnews.DetailScreen
import com.kurniawan.newsapp.ui.screen.home.HomeScreen
import com.kurniawan.newsapp.ui.screen.profile.ProfileScreen

@Composable
fun NewsApp(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
) {
   Scaffold(
        modifier = modifier,
        bottomBar = {
            val currentRoute = navHostController.currentBackStackEntryAsState().value?.destination?.route
            if (currentRoute == Screen.Home.route || currentRoute == Screen.Bookmark.route) {
                BottomBar(navHostController = navHostController)
            }
        }
    ) { innerPadding ->
       NavHost(
           navController = navHostController,
           startDestination = Screen.Home.route,
           modifier = Modifier.padding(innerPadding)
       ) {
           composable(Screen.Home.route) {
              HomeScreen(navHostController, navigateToDetail = { articlesItem ->
                  navHostController.currentBackStackEntry?.savedStateHandle?.set("article", articlesItem)
                  navHostController.navigate(Screen.Detail.route)
              })
           }
           composable(Screen.Bookmark.route) {
               BookmarkScreen()
           }
           composable(Screen.Profile.route) {
               ProfileScreen(navHostController)
           }
           composable("detail") {
               val article = navHostController.previousBackStackEntry?.savedStateHandle?.get<ArticlesItem>("article")
               if (article != null) {
                   DetailScreen(navHostController, article)
               }
           }
       }
    }
}

@Composable
private fun BottomBar(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier,
    ) {
        val navBackStackEntry by navHostController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.home),
                selectedIcon = painterResource(R.drawable.ic_home_selected),
                unselectedIcon = painterResource(R.drawable.ic_home_unselected),
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.bookmark),
                selectedIcon = painterResource(R.drawable.ic_bookmark_selected),
                unselectedIcon = painterResource(R.drawable.ic_bookmark_unselected),
                screen = Screen.Bookmark
            ),
        )
        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = if (currentRoute == item.screen.route) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.title,
                    )
                },
                label = { Text(item.title) },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navHostController.navigate(item.screen.route) {
                        popUpTo(navHostController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

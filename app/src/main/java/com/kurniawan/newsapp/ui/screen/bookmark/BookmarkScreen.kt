package com.kurniawan.newsapp.ui.screen.bookmark

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kurniawan.newsapp.ui.components.InfoMessage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Bookmark") },
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            InfoMessage(
                title = "No Bookmark",
                description = "You don't have any bookmarked articles yet."
            )
        }
    }
}

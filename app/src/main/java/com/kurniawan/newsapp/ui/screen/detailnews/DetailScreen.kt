package com.kurniawan.newsapp.ui.screen.detailnews

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.kurniawan.newsapp.R
import com.kurniawan.newsapp.data.source.remote.response.ArticlesItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navHostController: NavHostController, article: ArticlesItem) {
    var isBookmarked by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        isBookmarked = !isBookmarked

                    }) {
                        Icon(
                            painter = painterResource(
                                id = if (isBookmarked) R.drawable.ic_bookmark_selected
                                else R.drawable.ic_bookmark_unselected
                            ),
                            contentDescription = if (isBookmarked) "Bookmarked" else "Bookmark"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        BodyOfDetail(
            modifier = Modifier.padding(innerPadding),
            article = article
        )
    }
}
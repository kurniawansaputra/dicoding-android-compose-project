package com.kurniawan.newsapp.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kurniawan.newsapp.BuildConfig
import com.kurniawan.newsapp.R
import com.kurniawan.newsapp.data.source.remote.response.ArticlesItem
import com.kurniawan.newsapp.ui.common.UiState
import com.kurniawan.newsapp.ui.components.ArticleCard
import com.kurniawan.newsapp.ui.components.InfoMessage
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navHostController: NavHostController, navigateToDetail: (ArticlesItem) -> Unit) {
    val viewModel: HomeViewModel = koinViewModel()

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState) {
        if (uiState is UiState.Loading) {
            viewModel.getTopHeadlines(source = "bbc-news", apiKey = BuildConfig.API_KEY)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("News App") },
            actions = {
                IconButton(onClick = {
                    navHostController.navigate("profile")
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_profile),
                        contentDescription = "about_page"
                    )
                }
            }
        )

        when (uiState) {
            is UiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            is UiState.Success -> {
                val articles = (uiState as UiState.Success<List<ArticlesItem>>).data
                ArticleList(articles, navigateToDetail)
            }
            is UiState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    InfoMessage(
                        title = "Oops!",
                        description = "An error occurred while loading data. Please try again later.",
                    )
                }
            }
        }
    }
}

@Composable
fun ArticleList(
    articles: List<ArticlesItem>,
    navigateToDetail: (ArticlesItem) -> Unit,
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(articles) { index, article ->
            val paddingTop = if (index == 0) 16.dp else 8.dp
            val paddingBottom = if (index == articles.lastIndex) 16.dp else 8.dp

            ArticleCard(
                navigateToDetail = navigateToDetail,
                article = article,
                paddingTop = paddingTop,
                paddingBottom = paddingBottom,
            )
        }
    }
}






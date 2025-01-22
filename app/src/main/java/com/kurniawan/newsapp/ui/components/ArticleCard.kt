package com.kurniawan.newsapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kurniawan.newsapp.data.source.remote.response.ArticlesItem

@Composable
fun ArticleCard(
    navigateToDetail: (ArticlesItem) -> Unit,
    article: ArticlesItem,
    paddingTop: Dp,
    paddingBottom: Dp
) {
    Card( modifier = Modifier
        .padding(
            top = paddingTop,
            bottom = paddingBottom,
            start = 16.dp,
            end = 16.dp)
        .clickable {
            navigateToDetail(article)
        }
    ) {
        Column {
            AsyncImage(
                model = article.urlToImage,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(160.dp)
            )
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
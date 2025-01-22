package com.kurniawan.newsapp.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class NewsResponse(
	val totalResults: Int,
	val articles: List<ArticlesItem>,
	val status: String
)

@Parcelize
data class ArticlesItem(
	val publishedAt: String,
	val author: String,
	val urlToImage: String,
	val description: String,
	val title: String,
	val url: String,
	val content: String
): Parcelable
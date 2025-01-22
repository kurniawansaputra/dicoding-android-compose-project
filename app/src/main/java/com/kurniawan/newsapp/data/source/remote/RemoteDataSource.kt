package com.kurniawan.newsapp.data.source.remote

import com.kurniawan.newsapp.data.source.remote.network.ApiService

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getNews(source: String, apiKey: String) = apiService.getNews(
        sources = source,
        apiKey = apiKey
    )
}
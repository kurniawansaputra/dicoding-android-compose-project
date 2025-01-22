package com.kurniawan.newsapp.data.source.remote

class NewsRepository(private val remoteDataSource: RemoteDataSource) {
    suspend fun getNews(source: String, apiKey: String) = remoteDataSource.getNews(source, apiKey)
}
package com.kurniawan.newsapp.data.source.remote.network

import com.kurniawan.newsapp.data.source.remote.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines")
    suspend fun getNews(
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String
    ): NewsResponse
}
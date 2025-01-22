package com.kurniawan.newsapp.di

import com.kurniawan.newsapp.data.source.remote.RemoteDataSource
import com.kurniawan.newsapp.data.source.remote.network.ApiConfig
import org.koin.dsl.module

val appModule = module {
    single {
        ApiConfig.getApiService()
    }

    single {
       RemoteDataSource(get())
    }
}
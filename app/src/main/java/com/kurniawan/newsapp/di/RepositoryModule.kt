package com.kurniawan.newsapp.di

import com.kurniawan.newsapp.data.source.remote.NewsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single {
        NewsRepository(get())
    }
}
package com.kurniawan.newsapp

import android.app.Application
import com.kurniawan.newsapp.di.appModule
import com.kurniawan.newsapp.di.repositoryModule
import com.kurniawan.newsapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin


class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@MyApplication)
            modules(listOf(appModule, viewModelModule, repositoryModule))
        }
    }
}
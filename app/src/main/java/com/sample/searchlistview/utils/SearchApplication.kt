package com.sample.searchlistview.utils

import android.app.Application
import com.sample.searchlistview.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SearchApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SearchApplication)
            modules(provideDependency())
        }
    }

    private fun provideDependency() = appComponent
}
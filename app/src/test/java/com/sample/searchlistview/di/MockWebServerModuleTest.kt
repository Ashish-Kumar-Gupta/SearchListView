package com.sample.searchlistview.di

import okhttp3.mockwebserver.MockWebServer
import org.koin.dsl.module

val mockWebServerModuleTest = module {
    factory {
        MockWebServer()
    }
}
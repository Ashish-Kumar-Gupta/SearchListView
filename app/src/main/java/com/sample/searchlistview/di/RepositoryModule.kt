package com.sample.searchlistview.di

import com.sample.searchlistview.network.RemoteSourceImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory {
        RemoteSourceImpl()
    }
}
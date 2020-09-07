package com.sample.searchlistview.di

import com.sample.searchlistview.network.UseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        UseCase()
    }
}
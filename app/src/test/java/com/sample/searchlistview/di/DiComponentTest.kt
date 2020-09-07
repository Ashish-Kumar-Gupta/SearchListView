package com.sample.searchlistview.di

fun configureTestAppComponent(baseApi: String) = listOf (
    mockWebServerModuleTest,
    configureNetworkModuleTest(baseApi),
    useCaseModule,
    repositoryModule
)
package com.sample.searchlistview.di

import com.sample.searchlistview.network.ApiInterface
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

fun configureNetworkModuleTest(baseApi: String) = module {
    single {
        Retrofit.Builder()
            .baseUrl(baseApi)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
    factory {
        get<Retrofit>().create(ApiInterface::class.java)
    }
}
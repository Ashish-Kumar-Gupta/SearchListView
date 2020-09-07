package com.sample.searchlistview.di

import com.sample.searchlistview.network.ApiInterface
import com.sample.searchlistview.utils.DOMAIN_URL_KEY
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(DOMAIN_URL_KEY)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
    factory {
        get<Retrofit>().create(ApiInterface::class.java)
    }
}
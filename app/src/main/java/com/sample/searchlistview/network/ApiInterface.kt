package com.sample.searchlistview.network

import com.sample.searchlistview.model.Albums
import com.sample.searchlistview.utils.BASE_URL_KEY
import com.sample.searchlistview.utils.GET_ALBUMS_KEY
import org.koin.core.KoinComponent
import retrofit2.http.GET

interface ApiInterface: KoinComponent {
    @GET(BASE_URL_KEY + GET_ALBUMS_KEY)
    suspend fun getAlbums(): Albums
}
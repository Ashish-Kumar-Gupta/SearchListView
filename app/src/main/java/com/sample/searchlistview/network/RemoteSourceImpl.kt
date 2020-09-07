package com.sample.searchlistview.network

import com.sample.searchlistview.model.Albums
import org.koin.core.KoinComponent
import org.koin.core.inject

class RemoteSourceImpl(): RemoteSource, KoinComponent {
    private val apiInterface: ApiInterface by inject()

    override suspend fun getAlbums(): Albums {
        return apiInterface.getAlbums()
    }
}
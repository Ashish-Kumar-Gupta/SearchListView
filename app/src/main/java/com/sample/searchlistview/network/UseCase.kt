package com.sample.searchlistview.network

import com.sample.searchlistview.model.Albums
import org.koin.core.KoinComponent
import org.koin.core.inject

class UseCase: KoinComponent {
    private val remoteSourceImpl: RemoteSourceImpl by inject()

    suspend fun getAlbums(): Albums {
        return remoteSourceImpl.getAlbums()
    }
}
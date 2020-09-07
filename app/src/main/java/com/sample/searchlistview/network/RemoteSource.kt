package com.sample.searchlistview.network

import com.sample.searchlistview.model.Albums

interface RemoteSource {
    suspend fun getAlbums(): Albums
}
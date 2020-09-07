package com.sample.searchlistview.model

import com.squareup.moshi.Json

data class Album(
    @Json(name = "artistName") val artistName: String? = null,
    @Json(name = "collectionName") val collectionName: String? = null,
    @Json(name = "trackName") val trackName: String? = null,
    @Json(name = "artworkUrl30") val artworkUrl30: String? = null,
    @Json(name = "collectionPrice") val collectionPrice: Float? = null,
    @Json(name = "releaseDate") val releaseDate: String? = null
)
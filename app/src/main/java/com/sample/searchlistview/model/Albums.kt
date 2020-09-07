package com.sample.searchlistview.model

import com.squareup.moshi.Json

data class Albums(
    @Json(name = "resultCount") val resultCount: Int,
    @Json(name = "results") val results: List<Album>
)
package com.example.newsapp.network

import com.squareup.moshi.Json

@Json(name = "fields")
data class Fields(
    val headline: String,
    val byline: String,
    val thumbnail: String
)
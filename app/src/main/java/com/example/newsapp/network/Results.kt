package com.example.newsapp.network

import com.squareup.moshi.Json

@Json(name = "results")
data class Results(
    val sectionName: String,
    val webPublicationDate: String,
    val webTitle: String,
    val webUrl: String,
    val fields: Fields
)
package com.example.newsapp.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


data class Response(
    val status: String ,
    val userTier: String,
    val total: Long ,
    val startIndex: Long,
    val pageSize: Long,
    val currentPage: Long,
    val pages: Long,
    val orderBy: String,
    val results: List<Results>

)








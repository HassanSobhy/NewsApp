package com.example.newsapp.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

enum class NewsApiSection(val value: String) {
    SECTION_MUSIC("music"),
    SECTION_FILM("film"),
    SECTION_BUSINESS("business")
}


private const val BASE_URL = "https://content.guardianapis.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */

private val retrofit = Retrofit.Builder()
    //Converter Factory that allows Retrofit to return the server response in a useful format (Kotlin Object because Moshi Convertor)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface NewsApiService {

    @GET("search")
    fun getAllNewsResponse(
        @Query("api-key") apiKey: String,
        @Query("show-fields") fields: String
    ):
    // The Coroutine Call Adapter allows us to return a Deferred, a Job with a result
            Deferred<NewsResponse>

    @GET("search")
    fun getNewsBySectionResponse(
        @Query("api-key") apiKey: String,
        @Query("section") section: String="sport",
        @Query("show-fields") fields: String
    ):
    // The Coroutine Call Adapter allows us to return a Deferred, a Job with a result
            Deferred<NewsResponse>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object NewsApi {
    val retrofitService: NewsApiService by lazy { retrofit.create(NewsApiService::class.java) }
}
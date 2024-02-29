package com.example.amphibians.data

import com.example.amphibians.network.AmpApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val ampCardRepository: AmpCardRepository
}
class DefaultAppContainer : AppContainer {
    private val baseUrl =
        "https://android-kotlin-fun-mars-server.appspot.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: AmpApiService by lazy {
        retrofit.create(AmpApiService::class.java)
    }

    override val ampCardRepository: AmpCardRepository by lazy {
        NetworkAmpCardRepository(retrofitService)
    }
}
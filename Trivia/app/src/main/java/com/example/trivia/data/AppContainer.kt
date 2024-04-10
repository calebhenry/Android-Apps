package com.example.trivia.data

import com.example.trivia.network.TriviaApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit


interface AppContainer {
    val triviaRepository: TriviaRepository

    class DefaultAppContainer : AppContainer {
        private val baseUrl =
            "https://opentdb.com/"

        private val json = Json { ignoreUnknownKeys = true }

        private val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory("application/json".toMediaType()))
            .baseUrl(baseUrl)
            .build()

        private val retrofitService: TriviaApiService by lazy {
            retrofit.create(TriviaApiService::class.java)
        }

        override val triviaRepository: NetworkTriviaRepository by lazy {
            NetworkTriviaRepository(retrofitService)
        }
    }

}
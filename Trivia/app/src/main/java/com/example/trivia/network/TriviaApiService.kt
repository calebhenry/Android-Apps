package com.example.trivia.network

import com.example.trivia.model.TriviaQuestion
import com.example.trivia.model.TriviaResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TriviaApiService {
    @GET("api.php?amount=10")
    suspend fun getTriviaResponse(): TriviaResponse
}

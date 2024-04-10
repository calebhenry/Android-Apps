package com.example.trivia.data

import android.util.Log
import com.example.trivia.model.TriviaQuestion
import com.example.trivia.network.TriviaApiService

interface TriviaRepository {
    suspend fun getQuestions(): List<TriviaQuestion>
}

class NetworkTriviaRepository(
    private val triviaApiService: TriviaApiService
) : TriviaRepository {
    override suspend fun getQuestions(): List<TriviaQuestion> {
        return triviaApiService.getTriviaResponse().results
    }
}
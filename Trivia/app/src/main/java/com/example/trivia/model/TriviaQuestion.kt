package com.example.trivia.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TriviaResponse(
    @SerialName(value = "results")
    val results: List<TriviaQuestion>
)

@Serializable
data class TriviaQuestion(
    @SerialName(value = "question")
    val question: String,
    @SerialName(value = "correct_answer")val correctAnswer: String,
    @SerialName(value = "incorrect_answers") val incorrectAnswers: List<String>
)
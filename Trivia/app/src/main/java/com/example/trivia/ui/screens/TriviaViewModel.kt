package com.example.trivia.ui.screens

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.trivia.TriviaApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import com.example.trivia.data.AppContainer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TriviaViewModel(
    private val container: AppContainer
) : ViewModel() {


    private val _triviaState = MutableLiveData<TriviaState>()
    val triviaState: LiveData<TriviaState> = _triviaState

    init {
        fetchTrivia()
    }

    private fun fetchTrivia() {
        viewModelScope.launch {
            try {
                val response = container.triviaRepository.getQuestions()
                val triviaQuestion = response.first()
                val answers = mutableListOf(triviaQuestion.correctAnswer)
                answers.addAll(triviaQuestion.incorrectAnswers)
                answers.shuffle()
                val triviaState = TriviaState(
                    question = triviaQuestion.question,
                    answers = answers
                )
                _triviaState.postValue(triviaState)
            } catch (e: Exception) {
                Log.e("test", e.toString())
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as TriviaApplication
                TriviaViewModel(application.container)
            }
        }
    }
}

data class TriviaState(
    val question: String,
    val answers: List<String>
)

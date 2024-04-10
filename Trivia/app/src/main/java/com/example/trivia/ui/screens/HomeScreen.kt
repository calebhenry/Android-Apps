package com.example.trivia.ui.screens

import android.os.Debug
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun TriviaApp(viewModel: TriviaViewModel = viewModel(
    factory = TriviaViewModel.Factory
)) {
    TriviaScreen(viewModel.triviaState)
}

@Composable
fun TriviaScreen(triviaStateData: LiveData<TriviaState>) {
    val triviaState = triviaStateData.value
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (triviaState != null) {
            Text(text = triviaState.question)
        }
        triviaState?.answers?.forEach { answer ->
            Button(onClick = { /* Handle answer click */ }) {
                Text(text = answer)
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    Text(text = "Loading...")
}

@Composable
fun ErrorScreen() {
    Text(text = "Error fetching trivia questions")
}

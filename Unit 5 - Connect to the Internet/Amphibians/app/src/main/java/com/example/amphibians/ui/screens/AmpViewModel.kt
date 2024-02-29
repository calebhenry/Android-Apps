package com.example.amphibians.ui.screens

import retrofit2.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibians.AmpApplication
import com.example.amphibians.data.AmpCardRepository
import com.example.amphibians.model.AmphibianCard
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import kotlinx.coroutines.launch
import okio.IOException

sealed interface AmpUiState {
    data class Success(val amphibians: List<AmphibianCard>) : AmpUiState
    object Error : AmpUiState
    object Loading : AmpUiState
}

class AmpViewModel(private val ampCardRepository: AmpCardRepository) : ViewModel() {
    var ampUiState: AmpUiState by mutableStateOf(AmpUiState.Loading)
        private set

    init {
        getAmpCards()
    }

    fun getAmpCards() {
        viewModelScope.launch {
            ampUiState = AmpUiState.Loading
            ampUiState = try {
                AmpUiState.Success(ampCardRepository.getAmpCards())
            } catch (e: IOException) {
                AmpUiState.Error
            } catch (e: HttpException) {
                AmpUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmpApplication)
                val ampCardRepository = application.container.ampCardRepository
                AmpViewModel(ampCardRepository = ampCardRepository)
            }
        }
    }
}
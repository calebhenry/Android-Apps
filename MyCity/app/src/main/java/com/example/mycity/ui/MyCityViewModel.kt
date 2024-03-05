package com.example.mycity.ui

import androidx.lifecycle.ViewModel
import com.example.mycity.data.DataSource
import com.example.mycity.model.Category
import com.example.mycity.model.Destination
import com.example.mycity.model.MyCityUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MyCityViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(MyCityUiState(
        categoriesList = DataSource.catagories
    ))
    val uiState: StateFlow<MyCityUiState> = _uiState.asStateFlow()

    fun updateCategory(category: Category) {
        _uiState.update { currentState ->
            currentState.copy(
                category = category,
                destination = currentState.destination
            )
        }
    }

    fun updateDestination(destination: Destination) {
        _uiState.update { currentState ->
            currentState.copy(
                category = currentState.category,
                destination = destination
            )
        }
    }


}
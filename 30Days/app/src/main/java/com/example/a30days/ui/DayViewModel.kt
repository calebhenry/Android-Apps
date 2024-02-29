package com.example.a30days.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.a30days.data.DayRepository
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import com.example.a30days.ThirtyDaysApplication

class DayViewModel(
    private val dayRepository: DayRepository
) : ViewModel() {
    val dayList = dayRepository.DayList

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as ThirtyDaysApplication
                DayViewModel(application.dayRepository)
            }
        }
    }
}
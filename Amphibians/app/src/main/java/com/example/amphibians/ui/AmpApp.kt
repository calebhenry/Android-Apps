package com.example.amphibians.ui

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.amphibians.ui.screens.AmpViewModel
import com.example.amphibians.ui.screens.HomeScreen

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun AmpApp() {
    val ampViewModel: AmpViewModel = viewModel(factory = AmpViewModel.Factory)
    HomeScreen(ampUiState = ampViewModel.ampUiState)
}
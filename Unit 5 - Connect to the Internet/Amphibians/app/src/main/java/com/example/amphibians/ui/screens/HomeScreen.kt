package com.example.amphibians.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibians.model.AmphibianCard
import androidx.compose.foundation.lazy.items

@Composable
fun HomeScreen(
    ampUiState: AmpUiState,
    modifier: Modifier = Modifier
) {
    when (ampUiState) {
        is AmpUiState.Success -> AmpColumnScreen(cards = ampUiState.amphibians, modifier)
        is AmpUiState.Error -> ErrorScreen(modifier = modifier.fillMaxSize())
        is AmpUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Text(text = "Loading lmao", modifier = modifier)
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Text(text = "Failed :(", modifier = modifier)
}

@Composable
fun AmpCard(card: AmphibianCard, modifier: Modifier = Modifier) {
    Card {
        Text(text = card.name)
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(card.imgSrc)
                .crossfade(true)
                .build(),
            contentDescription = "Stuff",
            contentScale = ContentScale.Crop,
            modifier = modifier.fillMaxSize()
        )
        Text(text = card.type)
        Text(text = card.description)
    }
}

@Composable
fun AmpColumnScreen(
    cards: List<AmphibianCard>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(cards) {card ->
            AmpCard(card = card, modifier = modifier)
        }
    }
}
package com.example.a30days

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a30days.model.Day
import com.example.a30days.ui.DayViewModel

@Composable
fun HomeScreen(
    dayViewModel: DayViewModel = viewModel(
    factory = DayViewModel.Factory
    ),
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        contentPadding = contentPadding
    ) {
        items(dayViewModel.dayList) { item: Day ->
            DayCard(day = item)
        }
    }
}

@Composable
fun DayCard(day: Day, modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier.padding(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            Text(
                text = stringResource(day.dayRes),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(8.dp, 8.dp, 8.dp, 0.dp)
            )
            Text(
                text = stringResource(day.titleRes),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(8.dp, 8.dp, 8.dp, 0.dp)
            )
            Image(
                painter = painterResource(day.photoRes),
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .heightIn(100.dp, 300.dp),
                contentScale = ContentScale.Crop

            )
            Text(
                text = stringResource(day.descriptionRes),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
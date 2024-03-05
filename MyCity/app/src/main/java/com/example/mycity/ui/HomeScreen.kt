package com.example.mycity.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mycity.model.Category

@Composable
fun HomeScreen(
    onCategoryClick: (Category) -> Unit,
    categories: List<Category>,
    modifier: Modifier = Modifier
) {
    LazyColumn{
        items(categories) { category ->
            CategoryCard(
                category = category,
                onCategoryClick = onCategoryClick
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryCard(
    category: Category,
    onCategoryClick: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    Card (
        elevation = CardDefaults.cardElevation(5.dp),
        onClick = { onCategoryClick(category) },
        modifier = Modifier.padding(8.dp)
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            Text(
                text = category.name,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = category.description,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(
                    start = 8.dp,
                    end = 8.dp,
                    bottom = 8.dp
                )
            )
        }
    }
}
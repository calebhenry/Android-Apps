package com.example.mycity.model

data class MyCityUiState(
    val category: Category? = null,
    val destination: Destination? = null,
    val categoriesList: List<Category>? = null
) {
    val destinationsList = category?.destinations
}
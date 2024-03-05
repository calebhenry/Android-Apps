package com.example.mycity.model

data class Category(
    val name: String,
    val description: String,
    val destinations: List<Destination>
)

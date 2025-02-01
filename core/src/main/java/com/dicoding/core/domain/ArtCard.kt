package com.dicoding.core.domain

data class ArtCard(
    val id: String,
    val imageUrl: String,
    val title: String,
    val artis: String,
    val year: Int,
    val isFavorite: Boolean = false
)

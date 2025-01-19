package com.dicoding.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "simple_art_data")
data class ArtCard(
    @PrimaryKey
    val id: String,
    val imageUrl: String,
    val title: String,
    val artis: String,
    val year: Int,
    val isFavorite: Boolean = false
) {
    companion object {
        fun parse(data: DataItem, imageResolve: (String) -> String): ArtCard = ArtCard(
            id = data.id.toString(),
            imageUrl = imageResolve(data.imageId),
            title = data.title,
            artis = data.artistTitle ?: "Unknown",
            year = data.dateEnd ?: 0,
        )
    }
}

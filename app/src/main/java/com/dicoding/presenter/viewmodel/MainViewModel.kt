package com.dicoding.presenter.viewmodel

import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.lifecycle.ViewModel
import com.dicoding.domain.ArtCardDTO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    private val _arts = MutableStateFlow<List<ArtCardDTO>>(emptyList())
    val arts = _arts.asStateFlow()

    private val _favoriteArts = MutableStateFlow<List<ArtCardDTO>>(emptyList())
    val favoriteArts = _favoriteArts.asStateFlow()

    val favoriteListState = LazyStaggeredGridState()
    val mainListState = LazyStaggeredGridState()

    init {
        @Suppress("SpellCheckingInspection")
        val artCards = listOf(
            ArtCardDTO(
                imageUrl = "https://example.com/image1.jpg",
                title = "Starry Night",
                artis = "Vincent van Gogh",
                year = 1889,
                id = "1"
            ),
            ArtCardDTO(
                imageUrl = "https://example.com/image2.jpg",
                title = "Mona Lisa",
                artis = "Leonardo da Vinci",
                year = 1503,
                id = "2"
            ),
            ArtCardDTO(
                imageUrl = "https://example.com/image3.jpg",
                title = "The Persistence of Memory",
                artis = "Salvador Dalí",
                year = 1931,
                id = "3"
            ),
            ArtCardDTO(
                imageUrl = "https://example.com/image4.jpg",
                title = "The Scream",
                artis = "Edvard Munch",
                year = 1893,
                id = "4"
            ),
            ArtCardDTO(
                imageUrl = "https://example.com/image5.jpg",
                title = "Girl with a Pearl Earring",
                artis = "Johannes Vermeer",
                year = 1665,
                id = "5"
            ),
            ArtCardDTO(
                imageUrl = "https://example.com/image6.jpg",
                title = "The Last Supper",
                artis = "Leonardo da Vinci",
                year = 1498,
                id = "6"
            ),
            ArtCardDTO(
                imageUrl = "https://example.com/image7.jpg",
                title = "The Birth of Venus",
                artis = "Sandro Botticelli",
                year = 1486,
                id = "7"
            ),
            ArtCardDTO(
                imageUrl = "https://example.com/image8.jpg",
                title = "Guernica",
                artis = "Pablo Picasso",
                year = 1937,
                id = "8"
            ),
            ArtCardDTO(
                imageUrl = "https://example.com/image9.jpg",
                title = "The Night Watch",
                artis = "Rembrandt van Rijn",
                year = 1642,
                id = "9"
            ),
            ArtCardDTO(
                imageUrl = "https://example.com/image10.jpg",
                title = "Water Lilies",
                artis = "Claude Monet",
                year = 1916,
                id = "10"
            )
        )
        _arts.value = artCards.shuffled()
        _favoriteArts.value = artCards.shuffled()
    }
}
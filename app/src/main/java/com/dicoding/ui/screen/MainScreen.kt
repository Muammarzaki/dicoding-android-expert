package com.dicoding.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.domain.ArtCardDTO
import com.dicoding.ui.component.ImageVerticalStaggeredCard
import com.dicoding.ui.theme.ColArtsTheme

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    MainContent()
}

@Composable
private fun MainContent(modifier: Modifier = Modifier, arts: List<ArtCardDTO> = emptyList()) {
    ImageVerticalStaggeredCard(modifier, arts)
}

@Suppress("SpellCheckingInspection")
@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    val artCards = listOf(
        ArtCardDTO(
            imageUrl = "https://example.com/image1.jpg",
            title = "Starry Night",
            artis = "Vincent van Gogh",
            year = 1889
        ),
        ArtCardDTO(
            imageUrl = "https://example.com/image2.jpg",
            title = "Mona Lisa",
            artis = "Leonardo da Vinci",
            year = 1503
        ),
        ArtCardDTO(
            imageUrl = "https://example.com/image3.jpg",
            title = "The Persistence of Memory",
            artis = "Salvador Dalí",
            year = 1931
        ),
        ArtCardDTO(
            imageUrl = "https://example.com/image4.jpg",
            title = "The Scream",
            artis = "Edvard Munch",
            year = 1893
        ),
        ArtCardDTO(
            imageUrl = "https://example.com/image5.jpg",
            title = "Girl with a Pearl Earring",
            artis = "Johannes Vermeer",
            year = 1665
        ),
        ArtCardDTO(
            imageUrl = "https://example.com/image6.jpg",
            title = "The Last Supper",
            artis = "Leonardo da Vinci",
            year = 1498
        ),
        ArtCardDTO(
            imageUrl = "https://example.com/image7.jpg",
            title = "The Birth of Venus",
            artis = "Sandro Botticelli",
            year = 1486
        ),
        ArtCardDTO(
            imageUrl = "https://example.com/image8.jpg",
            title = "Guernica",
            artis = "Pablo Picasso",
            year = 1937
        ),
        ArtCardDTO(
            imageUrl = "https://example.com/image9.jpg",
            title = "The Night Watch",
            artis = "Rembrandt van Rijn",
            year = 1642
        ),
        ArtCardDTO(
            imageUrl = "https://example.com/image10.jpg",
            title = "Water Lilies",
            artis = "Claude Monet",
            year = 1916
        )
    )
    ColArtsTheme {
        MainContent(
            arts = artCards,
            modifier = Modifier.padding(16.dp)
        )
    }
}
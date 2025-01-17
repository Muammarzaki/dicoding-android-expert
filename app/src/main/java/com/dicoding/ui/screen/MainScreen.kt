package com.dicoding.ui.screen

import android.content.Intent
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.domain.ArtCardDTO
import com.dicoding.presenter.DetailActivity
import com.dicoding.presenter.viewmodel.MainViewModel
import com.dicoding.ui.component.ArtCardVerticalStaggeredCard
import com.dicoding.ui.theme.ColArtsTheme

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
) {
    val context = LocalContext.current
    val arts by viewModel.arts.collectAsState()
    MainContent(
        modifier = modifier,
        arts = arts,
        listState = viewModel.mainListState
    ) { artId ->
        Intent(context, DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_ID, artId)
        }.run {
            context.startActivity(this)
        }
    }
}

@Composable
private fun MainContent(
    modifier: Modifier = Modifier,
    arts: List<ArtCardDTO> = emptyList(),
    listState: LazyStaggeredGridState,
    onCardClick: (String) -> Unit = {}
) {
    ArtCardVerticalStaggeredCard(
        modifier,
        arts,
        listState = listState,
        onClick = { onCardClick(it) })
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
    ColArtsTheme {
        MainContent(
            modifier = Modifier.padding(8.dp),
            arts = artCards,
            listState = LazyStaggeredGridState()
        )
    }
}
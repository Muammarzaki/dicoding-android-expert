package com.dicoding.favorite.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.dicoding.R
import com.dicoding.core.domain.ArtCard
import com.dicoding.favorite.ui.viewmodel.FavoriteViewModel
import com.dicoding.ui.component.ArtCardVerticalStaggeredCard
import com.dicoding.ui.theme.ColArtsTheme
import kotlinx.coroutines.flow.flowOf

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel,
    onCardClick: (String, Boolean) -> Unit,
    moveToMain: () -> Unit
) {
    val arts = viewModel.favoriteArts.collectAsLazyPagingItems()
    FavoriteContent(
        modifier = modifier,
        arts = arts,
        listState = viewModel.favoriteListState,
        onCardClick = onCardClick,
        moveToMain = moveToMain
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteContent(
    modifier: Modifier = Modifier,
    arts: LazyPagingItems<ArtCard>,
    listState: LazyStaggeredGridState,
    onCardClick: (String, Boolean) -> Unit,
    moveToMain: () -> Unit = {}
) {
    Scaffold(topBar = {
        TopAppBar(
            modifier = Modifier,
            title = {
                Text(
                    text = "Favorite Arts",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                )
            },
            colors = TopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                scrolledContainerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            actions = {
                IconButton(
                    colors = IconButtonColors(
                        containerColor = MaterialTheme.colorScheme.inversePrimary,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        disabledContainerColor = Color.LightGray,
                        disabledContentColor = Color.DarkGray
                    ),
                    onClick = moveToMain,

                    ) {
                    Icon(
                        imageVector = Icons.Outlined.Home,
                        contentDescription = stringResource(R.string.favorite_button),
                    )
                }
            }
        )
    }) {
        ArtCardVerticalStaggeredCard(
            modifier = Modifier.padding(it),
            arts = arts,
            listState = listState,
            onClick = { id, isFavorite -> onCardClick(id, isFavorite) })
    }
}

@Preview(showBackground = true)
@Composable
private fun FavoriteScreenPreview() {
    val mockData = List(10) {
        ArtCard(
            id = "id_$it",
            imageUrl = "https://example.com/image$it.jpg",
            title = "Art Title $it",
            artis = "Artist $it",
            year = 2000 + it
        )
    }
    ColArtsTheme {
        FavoriteContent(
            arts = flowOf(PagingData.from(mockData)).collectAsLazyPagingItems(),
            listState = LazyStaggeredGridState(),
            onCardClick = { _, _ -> },
        )
    }

}
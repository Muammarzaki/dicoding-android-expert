package com.dicoding.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
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
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.dicoding.R
import com.dicoding.core.domain.ArtCard
import com.dicoding.ui.component.ArtCardVerticalStaggeredCard
import com.dicoding.ui.theme.ColArtsTheme
import com.dicoding.ui.viewmodel.MainViewModel
import kotlinx.coroutines.flow.flowOf

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    onCardClick: (String, Boolean) -> Unit,
    moveToFavorite: () -> Unit,
) {
    val arts = viewModel.arts.collectAsLazyPagingItems()
    MainContent(
        modifier = modifier,
        arts = arts,
        listState = viewModel.mainListState,
        onCardClick = onCardClick,
        moveToFavorite = moveToFavorite
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainContent(
    modifier: Modifier = Modifier,
    arts: LazyPagingItems<ArtCard>,
    listState: LazyStaggeredGridState,
    onCardClick: (String, Boolean) -> Unit,
    moveToFavorite: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier,
                title = {
                    Text(
                        text = "ColArts",
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
                        onClick = moveToFavorite,

                        ) {
                        Icon(
                            imageVector = Icons.Outlined.Favorite,
                            contentDescription = stringResource(R.string.favorite_button),
                        )
                    }
                }
            )
        }
    )
    { contentPadding ->
        ArtCardVerticalStaggeredCard(
            modifier.padding(contentPadding),
            arts,
            listState = listState,
            onClick = { id, isFavorite -> onCardClick(id, isFavorite) })
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
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
        MainContent(
            modifier = Modifier.padding(8.dp),
            arts = flowOf(PagingData.from(mockData)).collectAsLazyPagingItems(),
            listState = LazyStaggeredGridState(),
            onCardClick = { _, _ -> }, {}
        )
    }
}
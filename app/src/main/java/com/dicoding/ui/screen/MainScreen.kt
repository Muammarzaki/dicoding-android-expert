package com.dicoding.ui.screen

import android.content.Intent
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.dicoding.domain.ArtCard
import com.dicoding.presenter.DetailActivity
import com.dicoding.presenter.viewmodel.MainViewModel
import com.dicoding.ui.component.ArtCardVerticalStaggeredCard
import com.dicoding.ui.theme.ColArtsTheme
import kotlinx.coroutines.flow.flowOf

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
) {
    val context = LocalContext.current
    val arts = viewModel.arts.collectAsLazyPagingItems()
    MainContent(
        modifier = modifier,
        arts = arts,
        listState = viewModel.mainListState
    ) { artId, isFavorite ->
        Intent(context, DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_ID, artId)
            putExtra(DetailActivity.IS_FAVORITE, isFavorite)
        }.run {
            context.startActivity(this)
        }
    }
}

@Composable
private fun MainContent(
    modifier: Modifier = Modifier,
    arts: LazyPagingItems<ArtCard>,
    listState: LazyStaggeredGridState,
    onCardClick: (String, Boolean) -> Unit
) {
    ArtCardVerticalStaggeredCard(
        modifier,
        arts,
        listState = listState,
        onClick = { id, isFavorite -> onCardClick(id, isFavorite) })
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
            onCardClick = { _, _ -> }
        )
    }
}
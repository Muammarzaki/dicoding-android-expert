package com.dicoding.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.dicoding.domain.ArtCard
import com.dicoding.data.SimpleArt
import com.dicoding.ui.theme.ColArtsTheme
import kotlinx.coroutines.flow.flowOf


@Composable
fun ArtCardVerticalStaggeredCard(
    modifier: Modifier,
    arts: LazyPagingItems<ArtCard>,
    listState: LazyStaggeredGridState,
    onClick: (String,Boolean) -> Unit,
) {
    LazyVerticalStaggeredGrid(
        modifier = modifier,
        state = listState,
        columns = StaggeredGridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalItemSpacing = 4.dp,
    ) {
        items(arts.itemCount, key = arts.itemKey { it.id }) { index ->
            val art = arts[index] ?: return@items
            ArtCard(
                modifier = Modifier
                    .shadow(
                        elevation = 10.dp,
                        shape = MaterialTheme.shapes.medium,
                        spotColor = MaterialTheme.colorScheme.onSurface,
                        ambientColor = MaterialTheme.colorScheme.onSurface,
                        clip = false
                    )
                    .clip(MaterialTheme.shapes.small)
                    .background(MaterialTheme.colorScheme.background)
                    .clickable { onClick(art.id,art.isFavorite) },
                imageUrl = art.imageUrl,
                title = art.title,
                artis = art.artis,
                year = art.year.toString(),
                titleStyle = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Preview
@Composable
private fun ArtCardVerticalStaggeredCardPreview() {
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
        ArtCardVerticalStaggeredCard(
            modifier = Modifier.padding(8.dp),
            arts = flowOf(PagingData.from(mockData)).collectAsLazyPagingItems(),
            listState = LazyStaggeredGridState(),
            onClick = { _, _ -> }
        )
    }
}


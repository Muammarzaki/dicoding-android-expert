package com.dicoding.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.dicoding.domain.ArtCardDTO

@Composable
fun ArtCardVerticalStaggeredCard(
    modifier: Modifier,
    arts: List<ArtCardDTO>,
    listState: LazyStaggeredGridState,
    onClick: (String) -> Unit,
) {
    LazyVerticalStaggeredGrid(
        modifier = modifier,
        state = listState,
        columns = StaggeredGridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalItemSpacing = 4.dp,
    ) {
        items(arts, key = { it.title.hashCode() }) {
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
                    .clickable { onClick(it.id) },
                imageUrl = it.imageUrl,
                title = it.title,
                artis = it.artis,
                year = it.year.toString(),
                titleStyle = MaterialTheme.typography.titleMedium
            )
        }
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

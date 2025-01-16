package com.dicoding.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
fun ImageVerticalStaggeredCard(
    modifier: Modifier,
    arts: List<ArtCardDTO>
) {
    LazyVerticalStaggeredGrid(
        modifier = modifier,
        columns = StaggeredGridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalItemSpacing = 4.dp
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
                    .background(MaterialTheme.colorScheme.background),
                imageUrl = it.imageUrl,
                title = it.title,
                artis = it.artis,
                year = it.year.toString(),
                titleStyle = MaterialTheme.typography.titleMedium
            )
        }
    }
}
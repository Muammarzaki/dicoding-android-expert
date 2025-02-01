package com.dicoding.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.dicoding.R
import com.dicoding.ui.theme.ColArtsTheme


@Composable
fun TitleAuthorAndYear(
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    fontWeight: FontWeight? = null,
    artis: String,
    time: String,
    title: String,
    onFavoriteClick: () -> Unit,
    isFavorite: Boolean = false
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.testTag("TitleText"),
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 5.dp)
                .fillMaxWidth()
        ) {
            Column(modifier = Modifier.weight(3.5f)) {
                Text(
                    text = "Artis: $artis",
                    style = style,
                    fontWeight = fontWeight,
                    modifier = Modifier.testTag("AuthorText"),
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "Year: $time",
                    style = style,
                    fontWeight = fontWeight,
                    modifier = Modifier.testTag("YearText"),
                )
            }

            Column(
                modifier = Modifier.weight(0.9f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(
                    modifier = Modifier
                        .size(50.dp),
                    colors = IconButtonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        disabledContainerColor = Color.LightGray,
                        disabledContentColor = Color.Gray,
                    ),
                    onClick = onFavoriteClick
                ) {
                    Icon(
                        imageVector = if (!isFavorite) Icons.Outlined.FavoriteBorder else Icons.Outlined.Favorite,
                        contentDescription = stringResource(R.string.favorite_button),
                        tint = Color.Red
                    )
                }
            }
        }
    }
}

@Composable
fun DetailTop(
    modifier: Modifier = Modifier,
    imageUrl: String,
    title: String,
    artis: String,
    time: String,
    aspectRation: Float,
    shape: Shape = RectangleShape,
    isFavorite: Boolean = false,
    onFavoriteClick: () -> Unit,
) {
    Column(modifier = modifier) {
        AsyncImage(
            model = imageUrl,
            placeholder = painterResource(R.drawable.placeholder_image),
            error = painterResource(R.drawable.placeholder_image),
            contentDescription = stringResource(R.string.art_image),
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .aspectRatio(aspectRation)
                .clip(shape)
                .testTag("ArtThumbnail")
        )
        Spacer(Modifier.height(24.dp))
        TitleAuthorAndYear(
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium,
            artis = artis,
            time = time,
            title = title,
            onFavoriteClick = onFavoriteClick,
            isFavorite = isFavorite
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailTopPreviewUnFavorite() {

    ColArtsTheme {
        DetailTop(
            modifier = Modifier.padding(16.dp),
            imageUrl = "https://awsimages.detik.net.id/community/media/visual/2018/03/01/7c6217e5-b9eb-4ac8-88a0-26f097e6506c.jpeg?w=600&q=90",
            title = "Chocolate Starfish and the Hot Dog Flavored Water",
            artis = "Pablo Picasso",
            time = 2023.toString(),
            aspectRation = 4f / 3f,
            onFavoriteClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailTopPreviewFavorite() {

    ColArtsTheme {
        DetailTop(
            modifier = Modifier.padding(16.dp),
            imageUrl = "https://awsimages.detik.net.id/community/media/visual/2018/03/01/7c6217e5-b9eb-4ac8-88a0-26f097e6506c.jpeg?w=600&q=90",
            title = "Chocolate Starfish and the Hot Dog Flavored Water",
            artis = "Pablo Picasso",
            time = 2023.toString(),
            aspectRation = 4f / 3f,
            isFavorite = true,
            onFavoriteClick = {}
        )
    }
}


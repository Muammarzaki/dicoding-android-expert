package com.dicoding.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.dicoding.R
import com.dicoding.ui.theme.ColArtsTheme

@Composable
fun ArtCard(
    modifier: Modifier = Modifier,
    imageUrl: String,
    title: String,
    artis: String,
    year: String,
    contentDescription: String = stringResource(R.string.art_image),
    titleStyle: TextStyle = MaterialTheme.typography.titleLarge,
) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .padding(8.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = contentDescription,
            placeholder = painterResource(id = R.drawable.placeholder_image),
            error = painterResource(id = R.drawable.placeholder_image),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .testTag("ArtThumbnail")
                .clip(MaterialTheme.shapes.small)
                .align(Alignment.CenterHorizontally),
        )
            Column {
                Text(
                    modifier = Modifier
                        .testTag("TitleText")
                        .padding(top = 12.dp),
                    text = title,
                    style = titleStyle,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier
                            .testTag("AuthorText")
                            .weight(3f),
                        text = artis,
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(
                        modifier = Modifier
                            .testTag("YearText")
                            .weight(1.4f),
                        text = year,
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.bodyLarge,
                        fontStyle = FontStyle.Italic,
                    )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ArtCardPreview() {
    ColArtsTheme {
        Surface {
            ArtCard(
                imageUrl = "https://awsimages.detik.net.id/community/media/visual/2018/03/01/7c6217e5-b9eb-4ac8-88a0-26f097e6506c.jpeg?w=90&q=90",
                title = "Example Art Of Fiction in the 21st Century",
                artis = "Pablo Picasso",
                year = 2023.toString(),
                contentDescription = stringResource(R.string.art_image),
                titleStyle = MaterialTheme.typography.titleLarge,
            )
        }
    }
}
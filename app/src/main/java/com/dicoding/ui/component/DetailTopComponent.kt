package com.dicoding.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.testTag("TitleText"),
        )
        Spacer(Modifier.height(16.dp))
        Column {
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
    shape: Shape = RectangleShape
) {
    Column(modifier = modifier) {
        AsyncImage(
            model = imageUrl,
            placeholder = painterResource(R.drawable.placeholder_image),
            error = painterResource(R.drawable.placeholder_image),
            contentDescription = stringResource(R.string.art_image),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .aspectRatio(aspectRation)
                .clip(shape)
                .testTag("ArtThumbnail")
        )
        Spacer(Modifier.height(16.dp))
        TitleAuthorAndYear(
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium,
            title = title,
            artis = artis,
            time = time,
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DetailTopPreview() {

    ColArtsTheme {
        DetailTop(
            modifier = Modifier.padding(16.dp),
            imageUrl = "https://awsimages.detik.net.id/community/media/visual/2018/03/01/7c6217e5-b9eb-4ac8-88a0-26f097e6506c.jpeg?w=600&q=90",
            title = "Chocolate Starfish and the Hot Dog Flavored Water",
            artis = "Pablo Picasso",
            time = 2023.toString(),
            aspectRation = 4f / 3f,
        )
    }
}


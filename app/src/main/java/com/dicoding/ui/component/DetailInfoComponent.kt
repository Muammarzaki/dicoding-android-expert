package com.dicoding.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dicoding.ui.theme.ColArtsTheme

@Composable
fun TableCell(
    modifier: Modifier = Modifier,
    value: String,
    fontWeight: FontWeight? = null,
) {
    Text(
        modifier = modifier,
        text = value,
        style = MaterialTheme.typography.labelLarge,
        fontWeight = fontWeight
    )
}

@Preview(showBackground = true)
@Composable
private fun FieldInRowPreview() {
    ColArtsTheme {
        Column {
            Row {
                TableCell(modifier = Modifier.weight(1f), value = "Title")
                TableCell(modifier = Modifier.weight(3f), value = "2023")
            }
            Row {
                TableCell(modifier = Modifier.weight(1f), value = "Artist")
                TableCell(modifier = Modifier.weight(3f), value = "Pablo Picasso")
            }
            Row {
                TableCell(modifier = Modifier.weight(1f), value = "Year")
                TableCell(modifier = Modifier.weight(3f), value = "2023")
            }
        }
    }
}

@Composable
fun FieldInRow(
    modifier: Modifier = Modifier,
    weightLeft: Float,
    weightRight: Float,
    label: String,
    value: String,
    space: Dp
) {
    Row(
        modifier = modifier,
    ) {
        TableCell(
            modifier = Modifier.weight(weightLeft),
            value = label,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(space))
        TableCell(modifier = Modifier.weight(weightRight), value = value)
    }
}

@Composable
fun DetailInfoTable(
    modifier: Modifier = Modifier,
    data: List<Pair<String, String>>,
    lineColor: Color = Color.Black,
    lineWidth: Float = 2f
) {
    Column(modifier = modifier.padding(vertical = 1.dp)) {
        data.forEachIndexed { index, it ->
            FieldInRow(
                modifier = Modifier
                    .drawBehind {
                        if (index == 0) {
                            drawLine(
                                color = lineColor,
                                start = Offset.Zero,
                                end = Offset(x = size.width, y = 0f),
                                strokeWidth = lineWidth
                            )
                        }
                        drawLine(
                            color = lineColor,
                            start = Offset(x = 0f, y = size.height),
                            end = Offset(x = size.width, y = size.height),
                            strokeWidth = lineWidth
                        )
                    }
                    .padding(vertical = 5.dp)
                    .testTag("FieldInRow"),
                weightLeft = 1.2f,
                weightRight = 2f,
                label = it.first,
                value = it.second,
                space = 2.dp
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DetailInfoPreview() {
    ColArtsTheme {
        DetailInfoTable(
            modifier = Modifier.padding(top = 16.dp),
            data = listOf(
                "id" to "1",
                "title" to "Title",
                "altTitles" to "null",
                "thumbnail" to "null",
                "artistDisplay" to "Pablo Picasso",
                "dateDisplay" to "2023",
                "placeOfOrigin" to "null",
                "dimensions" to "null",
                "mediumDisplay" to "null",
                "artworkTypeTitle" to "null",
                "isOnView" to "true",
                "galleryTitle" to "null",
                "onLoanDisplay" to "null",
                "isPublicDomain" to "true",
                "copyrightNotice" to "null",
                "inscriptions" to "null",
                "provenanceText" to "null",
                "publicationHistory" to "null",
                "exhibitionHistory" to "null",
                "colorfulness" to "null",
                "latitude" to "null",
                "longitude" to "null",
                "description" to "null",
            )
        )
    }
}

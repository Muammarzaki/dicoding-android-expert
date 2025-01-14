package com.dicoding.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.ui.theme.ColArtsTheme

@Composable
fun Capsule(
    modifier: Modifier = Modifier,
    text: String,
    backgroundColor:
    Color = Color.Unspecified,
    fontColor: Color = Color.Unspecified,
) {
    Box(
        modifier = modifier
            .wrapContentSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            modifier = Modifier
                .align(Alignment.Center)
                .clip(MaterialTheme.shapes.small)
                .background(backgroundColor)
                .padding(5.dp),
            style = MaterialTheme.typography.bodySmall,
            color = fontColor,
        )
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    widthDp = 100,
    heightDp = 100
)
@Composable
private fun CapsulePreview() {
    ColArtsTheme {
        Surface {
            Capsule(text = "Caustic", backgroundColor = Color.Red)
        }
    }
}
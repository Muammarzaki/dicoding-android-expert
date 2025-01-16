package com.dicoding.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.domain.Artwork
import com.dicoding.toPropertyList
import com.dicoding.ui.component.DetailInfoTable
import com.dicoding.ui.component.DetailTop
import com.dicoding.ui.component.TextHtml
import com.dicoding.ui.theme.ColArtsTheme

@Composable
fun DetailScreen(modifier: Modifier = Modifier, details: Artwork) {
    val scrollState = rememberScrollState()
    Scaffold(modifier = modifier.padding(16.dp)) { contentPadding ->
        Column(
            modifier = modifier
                .padding(contentPadding)
                .verticalScroll(state = scrollState)
        ) {
            DetailTop(
                modifier = Modifier,
                title = details.title,
                artis = details.artistDisplay ?: "Unknown",
                time = details.dateDisplay ?: "--",
                aspectRation = 4f / 3f,
                imageUrl = details.thumbnail ?: "",
                shape = MaterialTheme.shapes.medium
            )
            details.description?.let {
                if (it.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(16.dp))
                    TextHtml(it) { text ->
                        Text(
                            text = text.trim(),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Additional Information",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 12.dp),
                fontWeight = FontWeight.Bold
            )
            DetailInfoTable(
                modifier = Modifier,
                data = details
                    .toPropertyList(
                        "id", "title", "artistDisplay", "dateDisplay", "thumbnail", "description"
                    )
                    .map {
                        Pair(
                            it.first,
                            if (it.second is List<*>) (it.second as List<*>).joinToString("\n") else it.second.toString()
                        )
                    }
            )
        }
    }
}

@Suppress("SpellCheckingInspection")
@Preview(showBackground = true)
@Composable
private fun DetailScreenPreview() {
    ColArtsTheme {
        DetailScreen(
            details = Artwork(
                id = 1,
                title = "Chocolate Starfish and the Hot Dog Flavored Water",
                altTitles = listOf(
                    "El Guernica, La Guernica",
                    "El Guernica, La Guernica",
                ),
                thumbnail = "https://awsimages.detik.net.id/community/media/visual/2018/03/01/7c6217e5-b9eb-4ac8-88a0-26f097e6506c.jpeg?w=600&q=90",
                artistDisplay = "Pablo Picasso",
                dateDisplay = "1937",
                placeOfOrigin = "Spain",
                dimensions = "349 cm × 776 cm (137.4 in × 305.5 in)",
                mediumDisplay = "Oil on canvas",
                artworkTypeTitle = "Painting",
                isOnView = true,
                galleryTitle = "Museo Reina Sofía",
                onLoanDisplay = "Not on loan",
                isPublicDomain = true,
                copyrightNotice = "Public domain",
                inscriptions = "No inscriptions",
                provenanceText = "The painting was created in response to the bombing of the Spanish town of Guernica during the Spanish Civil War. It was exhibited around the world and eventually displayed at the Museum of Modern Art in New York before being moved to the Museo Reina Sofía in Madrid in 1992.",
                publicationHistory = "Guernica was first shown in the Spanish Pavilion at the 1937 Paris International Exposition and has been widely discussed in books, articles, and documentaries ever since.",
                exhibitionHistory = "It has been part of many major exhibitions, including the Tate Modern and the Museum of Modern Art.",
                colorfulness = 1f,
                latitude = 40.4093, // Latitude for Madrid
                longitude = -3.7118, // Longitude for Madrid
                description = "<p>In his best-known and largest painting, Georges Seurat depicted people from different social classes strolling and relaxing in a park just west of Paris on La Grande Jatte, an island in the Seine River. Although he took his subject from modern life, Seurat sought to evoke the sense of timelessness associated with ancient art, especially Egyptian and Greek sculpture. He once wrote, “I want to make modern people, in their essential traits, move about as they do on those friezes, and place them on canvases organized by harmonies of color.”</p>\n<p>Seurat painted <em>A Sunday on La Grande Jatte—1884</em> using pointillism, a highly systematic and scientific technique based on the hypothesis that closely positioned points of pure color mix together in the viewer’s eye. He began work on the canvas in 1884 (and included this date in the title) with a layer of small, horizontal brushstrokes in complementary colors. He next added a series of dots that coalesce into solid and luminous forms when seen from a distance. Sometime before 1889 Seurat added a border of blue, orange, and red dots that provide a visual transition between the painting’s interior and the specially designed white frame, <a href=\"https://www.artic.edu/articles/969/la-grande-jatte-frame-by-frame\">which has been re-created at the Art Institute</a>.</p>\n"
            )
        )
    }
}
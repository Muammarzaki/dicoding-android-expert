package com.dicoding.ui.component

import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import com.dicoding.ui.theme.ColArtsTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ArtCardComponentKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val url =
        "https://awsimages.detik.net.id/community/media/visual/2018/03/01/7c6217e5-b9eb-4ac8-88a0-26f097e6506c.jpeg?w=600&q=90"

    private val title = "Example Art Of Fiction in the 21st Century"

    private val artis = "Picasso"

    private val year = 2023.toString()

    private val description = "image"

    @Before
    fun setUp() {
        composeTestRule.setContent {
            ColArtsTheme {
                ArtCard(
                    imageUrl = url,
                    title = title,
                    artis = artis,
                    year = year,
                    contentDescription = description,
                )
            }
        }
    }

    @Test
    fun should_load_image_from_url_and_have_some_content_description() {
        composeTestRule.onNodeWithTag("AsyncImage")
            .assertExists()
            .assertIsDisplayed()
            .assertContentDescriptionEquals(description)
    }

    @Test
    fun should_have_title_artis_year_with_same_value_as_provided() {
        composeTestRule.onNodeWithTag("TitleText")
            .assertExists().assertIsDisplayed()
            .assertTextEquals(title)

        composeTestRule.onNodeWithTag("AuthorText")
            .assertExists().assertIsDisplayed()
            .assertTextEquals(artis)

        composeTestRule.onNodeWithTag("YearText")
            .assertExists().assertIsDisplayed()
            .assertTextEquals(year)
    }

    @Test
    fun root_should_not_clickable() {
        composeTestRule.onRoot()
            .assertExists()
            .assertHasNoClickAction()
    }

    @Test
    fun root_should_has_4_child() {
        composeTestRule.onRoot(useUnmergedTree = true)
            .onChildren()
            .assertCountEquals(4)
    }
}
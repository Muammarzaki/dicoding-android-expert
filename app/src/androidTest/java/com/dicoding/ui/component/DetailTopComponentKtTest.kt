package com.dicoding.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import androidx.compose.ui.unit.dp
import androidx.test.platform.app.InstrumentationRegistry
import com.dicoding.R
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailTopComponentKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private val url =
        "https://awsimages.detik.net.id/community/media/visual/2018/03/01/7c6217e5-b9eb-4ac8-88a0-26f097e6506c.jpeg?w=600&q=90"

    private val title = "Chocolate Starfish and the Hot Dog Flavored Water"

    private val artis = "Pablo Picasso"

    private val year = 2023

    private val aspectRatio = 3f / 4f

    @Before
    fun setUp() {
        composeTestRule.setContent {
            DetailTop(
                modifier = Modifier.padding(16.dp),
                imageUrl = url,
                title = title,
                artis = artis,
                time = year.toString(),
                aspectRation = aspectRatio
            )
        }
    }

    @After
    fun tearDown() {
        composeTestRule.onRoot(useUnmergedTree = true).printToLog("TREE")
    }

    @Test
    fun testDetailTop() {
        composeTestRule.onNodeWithTag("ArtThumbnail").assertIsDisplayed()
        composeTestRule.onNodeWithTag("TitleText").assertIsDisplayed()
        composeTestRule.onNodeWithTag("AuthorText").assertIsDisplayed()
        composeTestRule.onNodeWithTag("YearText").assertIsDisplayed()
    }

    @Test
    fun testImageWithContentDescription() {
        composeTestRule.onNodeWithContentDescription(context.resources.getString(R.string.art_image))
            .assertIsDisplayed()
    }

    @Test
    fun testImageShowTheImage() {
        composeTestRule.onNodeWithTag("ArtThumbnail").captureToImage()
    }

    @Test
    fun testTitleShowTheTitle() {
        composeTestRule.onNodeWithText(title).assertIsDisplayed()
        composeTestRule.onNodeWithTag("TitleText").assertTextEquals(title)
    }

    @Test
    fun testAuthorShowTheAuthor() {
        composeTestRule.onNode(
            hasText(
                artis,
                substring = true
            )
        ).assertIsDisplayed()
        composeTestRule.onNodeWithTag("AuthorText").assertTextContains(artis, substring = true)
    }

    @Test
    fun testYearShowTheYear() {
        composeTestRule.onNode(
            hasText(
                year.toString(),
                substring = true
            )
        ).assertIsDisplayed()
        composeTestRule.onNodeWithTag("YearText")
            .assertTextContains(year.toString(), substring = true)
    }
}
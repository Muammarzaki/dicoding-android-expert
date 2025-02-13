package com.dicoding.component

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import com.dicoding.core.domain.ArtWork
import com.dicoding.core.toPropertyList
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailInfoComponentKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val pairList = ArtWork(
        id = "1",
        title = "Title",
        altTitles = null,
        thumbnail = null,
        artistDisplay = "Pablo Picasso",
        dateDisplay = "2023",
        placeOfOrigin = null,
        dimensions = null,
        mediumDisplay = null,
        artworkTypeTitle = null,
        isOnView = true,
        galleryTitle = null,
        onLoanDisplay = null,
        isPublicDomain = true,
        copyrightNotice = null,
        inscriptions = null,
        provenanceText = null,
        publicationHistory = null,
        exhibitionHistory = null,
        colorfulness = null,
        latitude = null,
        longitude = null,
        description = ""
    ).toPropertyList("id", "title", "artistDisplay", "dateDisplay").map {
        Pair(it.first, it.second.toString())
    }

    @Before
    fun setUp() {
        composeTestRule.setContent {
            com.dicoding.ui.theme.ColArtsTheme {
                com.dicoding.ui.component.DetailInfoTable(
                    data = pairList
                )
            }
        }
    }

    @Test
    fun test_has_same_field_with_pair_list() {
        composeTestRule.onAllNodesWithTag("FieldInRow").assertCountEquals(pairList.size)
    }
}
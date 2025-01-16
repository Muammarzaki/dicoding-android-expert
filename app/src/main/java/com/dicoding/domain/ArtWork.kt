package com.dicoding.domain

import com.dicoding.PropertyNotIncluding

data class Artwork(
    @PropertyNotIncluding
    val id: Int,
    val title: String,
    val altTitles: List<String>?,
    val thumbnail: String?,
    val artistDisplay: String?,
    val dateDisplay: String?,
    val placeOfOrigin: String?,
    val dimensions: String?,
    val mediumDisplay: String?,
    val artworkTypeTitle: String?,
    val isOnView: Boolean,
    val galleryTitle: String?,
    val onLoanDisplay: String?,
    val isPublicDomain: Boolean,
    val copyrightNotice: String?,
    val inscriptions: String?,
    val provenanceText: String?,
    val publicationHistory: String?,
    val exhibitionHistory: String?,
    val colorfulness: Float?,
    val latitude: Double?,
    val longitude: Double?,
    val description: String?,
) {
}


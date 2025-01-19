package com.dicoding.domain

data class ArtWork(
    val id: String,
    val title: String,
    val altTitles: List<String>?,
    val thumbnail: String?,
    val artistDisplay: String?,
    val dateDisplay: String?,
    val placeOfOrigin: String?,
    val dimensions: String?,
    val mediumDisplay: String?,
    val artworkTypeTitle: String?,
    val isOnView: Boolean?,
    val galleryTitle: String?,
    val onLoanDisplay: String?,
    val isPublicDomain: Boolean?,
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
    companion object {
        fun parse(data: DataItem): ArtWork {
            return with(data) {
                ArtWork(
                    id = id,
                    title = title,
                    altTitles = altTitles,
                    thumbnail = id.toString(),
                    artistDisplay = artistTitle,
                    dateDisplay = dateDisplay,
                    placeOfOrigin = placeOfOrigin,
                    dimensions = dimensions,
                    mediumDisplay = mediumDisplay,
                    artworkTypeTitle = artworkTypeTitle,
                    isOnView = isOnView,
                    galleryTitle = galleryTitle,
                    onLoanDisplay = onLoanDisplay,
                    isPublicDomain = isPublicDomain,
                    copyrightNotice = copyrightNotice,
                    inscriptions = inscriptions,
                    provenanceText = provenanceText,
                    publicationHistory = publicationHistory,
                    exhibitionHistory = exhibitionHistory,
                    colorfulness = colorfulness,
                    latitude = latitude,
                    longitude = longitude,
                    description = description,
                )
            }
        }
    }
}


@file:Suppress("SpellCheckingInspection")

package com.dicoding.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AICArtWorksResponse(

    @field:SerializedName("pagination")
    val pagination: Pagination,

    @field:SerializedName("data")
    val data: List<DataItem> = emptyList(),

    @field:SerializedName("config")
    val config: Config? = null,

    @field:SerializedName("info")
    val info: Info? = null
) : Parcelable

@Parcelize
data class AICArtWorkResponse(
    @field:SerializedName("data")
    val data: DataItem,
) : Parcelable

@Parcelize
data class SuggestAutocompleteAllItem(

    @field:SerializedName("input")
    val input: List<String?>? = null,

    @field:SerializedName("weight")
    val weight: Int? = null,

    @field:SerializedName("contexts")
    val contexts: Contexts? = null
) : Parcelable

@Parcelize
data class DataItem(

    @field:SerializedName("category_titles")
    val categoryTitles: List<String?>? = null,

    @field:SerializedName("classification_titles")
    val classificationTitles: List<String?>? = null,

    @field:SerializedName("alt_style_ids")
    val altStyleIds: List<String?>? = null,

    @field:SerializedName("publication_history")
    val publicationHistory: String? = null,

    @field:SerializedName("alt_technique_ids")
    val altTechniqueIds: List<String?>? = null,

    @field:SerializedName("copyright_notice")
    val copyrightNotice: String? = null,

    @field:SerializedName("fiscal_year")
    val fiscalYear: String? = null,

    @field:SerializedName("section_ids")
    val sectionIds: List<String?>? = null,

    @field:SerializedName("date_qualifier_id")
    val dateQualifierId: String? = null,

    @field:SerializedName("term_titles")
    val termTitles: List<String?>? = null,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("main_reference_number")
    val mainReferenceNumber: String? = null,

    @field:SerializedName("material_titles")
    val materialTitles: List<String?>? = null,

    @field:SerializedName("classification_title")
    val classificationTitle: String? = null,

    @field:SerializedName("section_titles")
    val sectionTitles: List<String?>? = null,

    @field:SerializedName("alt_classification_ids")
    val altClassificationIds: List<String?>? = null,

    @field:SerializedName("is_boosted")
    val isBoosted: Boolean? = null,

    @field:SerializedName("alt_artist_ids")
    val altArtistIds: List<Int?>? = null,

    @field:SerializedName("artist_display")
    val artistDisplay: String? = null,

    @field:SerializedName("date_end")
    val dateEnd: Int? = null,

    @field:SerializedName("artist_id")
    val artistId: Int? = null,

    @field:SerializedName("suggest_autocomplete_boosted")
    val suggestAutocompleteBoosted: String? = null,

    @field:SerializedName("subject_ids")
    val subjectIds: List<String?>? = null,

    @field:SerializedName("publishing_verification_level")
    val publishingVerificationLevel: String? = null,

    @field:SerializedName("fiscal_year_deaccession")
    val fiscalYearDeaccession: String? = null,

    @field:SerializedName("dimensions_detail")
    val dimensionsDetail: List<DimensionsDetailItem?>? = null,

    @field:SerializedName("short_description")
    val shortDescription: String? = null,

    @field:SerializedName("is_public_domain")
    val isPublicDomain: Boolean? = null,

    @field:SerializedName("latitude")
    val latitude: Double? = null,

    @field:SerializedName("source_updated_at")
    val sourceUpdatedAt: String? = null,

    @field:SerializedName("on_loan_display")
    val onLoanDisplay: String? = null,

    @field:SerializedName("exhibition_history")
    val exhibitionHistory: String? = null,

    @field:SerializedName("provenance_text")
    val provenanceText: String? = null,

    @field:SerializedName("material_ids")
    val materialIds: List<String?>? = null,

    @field:SerializedName("place_of_origin")
    val placeOfOrigin: String? = null,

    @field:SerializedName("artist_ids")
    val artistIds: List<Int?>? = null,

    @field:SerializedName("style_titles")
    val styleTitles: List<String?>? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("has_not_been_viewed_much")
    val hasNotBeenViewedMuch: Boolean? = null,

    @field:SerializedName("api_link")
    val apiLink: String? = null,

    @field:SerializedName("technique_titles")
    val techniqueTitles: List<String?>? = null,

    @field:SerializedName("gallery_id")
    val galleryId: String? = null,

    @field:SerializedName("timestamp")
    val timestamp: String? = null,

    @field:SerializedName("department_id")
    val departmentId: String? = null,

    @field:SerializedName("inscriptions")
    val inscriptions: String? = null,

    @field:SerializedName("has_educational_resources")
    val hasEducationalResources: Boolean? = null,

    @field:SerializedName("alt_titles")
    val altTitles: List<String> = emptyList(),

    @field:SerializedName("gallery_title")
    val galleryTitle: String? = null,

    @field:SerializedName("catalogue_display")
    val catalogueDisplay: String? = null,

    @field:SerializedName("boost_rank")
    val boostRank: String? = null,

    @field:SerializedName("artwork_type_title")
    val artworkTypeTitle: String? = null,

    @field:SerializedName("sound_ids")
    val soundIds: List<String?>? = null,

    @field:SerializedName("max_zoom_window_size")
    val maxZoomWindowSize: Int? = null,

    @field:SerializedName("material_id")
    val materialId: String? = null,

    @field:SerializedName("style_id")
    val styleId: String? = null,

    @field:SerializedName("dimensions")
    val dimensions: String? = null,

    @field:SerializedName("subject_titles")
    val subjectTitles: List<String?>? = null,

    @field:SerializedName("site_ids")
    val siteIds: List<String?>? = null,

    @field:SerializedName("nomisma_id")
    val nomismaId: String? = null,

    @field:SerializedName("suggest_autocomplete_all")
    val suggestAutocompleteAll: List<SuggestAutocompleteAllItem?>? = null,

    @field:SerializedName("video_ids")
    val videoIds: List<String?>? = null,

    @field:SerializedName("classification_ids")
    val classificationIds: List<String?>? = null,

    @field:SerializedName("credit_line")
    val creditLine: String? = null,

    @field:SerializedName("latlon")
    val latlon: String? = null,

    @field:SerializedName("text_ids")
    val textIds: List<String?>? = null,

    @field:SerializedName("is_on_view")
    val isOnView: Boolean? = null,

    @field:SerializedName("alt_image_ids")
    val altImageIds: List<String?>? = null,

    @field:SerializedName("classification_id")
    val classificationId: String? = null,

    @field:SerializedName("category_ids")
    val categoryIds: List<String?>? = null,

    @field:SerializedName("longitude")
    val longitude: Double? = null,

    @field:SerializedName("alt_subject_ids")
    val altSubjectIds: List<String?>? = null,

    @field:SerializedName("internal_department_id")
    val internalDepartmentId: Int? = null,

    @field:SerializedName("document_ids")
    val documentIds: List<String?>? = null,

    @field:SerializedName("thumbnail")
    val thumbnail: Thumbnail? = null,

    @field:SerializedName("alt_material_ids")
    val altMaterialIds: List<String?>? = null,

    @field:SerializedName("style_title")
    val styleTitle: String? = null,

    @field:SerializedName("date_start")
    val dateStart: Int? = null,

    @field:SerializedName("artist_titles")
    val artistTitles: List<String?>? = null,

    @field:SerializedName("style_ids")
    val styleIds: List<String?>? = null,

    @field:SerializedName("date_display")
    val dateDisplay: String? = null,

    @field:SerializedName("has_multimedia_resources")
    val hasMultimediaResources: Boolean? = null,

    @field:SerializedName("image_id")
    val imageId: String,

    @field:SerializedName("subject_id")
    val subjectId: String? = null,

    @field:SerializedName("medium_display")
    val mediumDisplay: String? = null,

    @field:SerializedName("colorfulness")
    val colorfulness: Float? = null,

    @field:SerializedName("color")
    val color: Color? = null,

    @field:SerializedName("is_zoomable")
    val isZoomable: Boolean? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("edition")
    val edition: String? = null,

    @field:SerializedName("artist_title")
    val artistTitle: String? = null,

    @field:SerializedName("technique_ids")
    val techniqueIds: List<String?>? = null,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("department_title")
    val departmentTitle: String? = null,

    @field:SerializedName("theme_titles")
    val themeTitles: List<String?>? = null,

    @field:SerializedName("api_model")
    val apiModel: String? = null,

    @field:SerializedName("date_qualifier_title")
    val dateQualifierTitle: String? = null,

    @field:SerializedName("technique_id")
    val techniqueId: String? = null,

    @field:SerializedName("has_advanced_imaging")
    val hasAdvancedImaging: Boolean? = null,

    @field:SerializedName("artwork_type_id")
    val artworkTypeId: Int? = null
) : Parcelable

@Parcelize
data class Thumbnail(

    @field:SerializedName("alt_text")
    val altText: String? = null,

    @field:SerializedName("width")
    val width: Int? = null,

    @field:SerializedName("lqip")
    val lqip: String? = null,

    @field:SerializedName("height")
    val height: Int? = null
) : Parcelable

@Parcelize
data class Info(

    @field:SerializedName("license_text")
    val licenseText: String? = null,

    @field:SerializedName("license_links")
    val licenseLinks: List<String?>? = null,

    @field:SerializedName("version")
    val version: String? = null
) : Parcelable

@Parcelize
data class Color(

    @field:SerializedName("s")
    val s: Int? = null,

    @field:SerializedName("percentage")
    val percentage: String? = null,

    @field:SerializedName("h")
    val h: Int? = null,

    @field:SerializedName("l")
    val l: Int? = null,

    @field:SerializedName("population")
    val population: Int? = null
) : Parcelable

@Parcelize
data class Pagination(

    @field:SerializedName("total")
    val total: Int? = null,

    @field:SerializedName("offset")
    val offset: Int? = null,

    @field:SerializedName("limit")
    val limit: Int? = null,

    @field:SerializedName("next_url")
    val nextUrl: String? = null,

    @field:SerializedName("total_pages")
    val totalPages: Int ,

    @field:SerializedName("current_page")
    val currentPage: Int,
) : Parcelable

@Parcelize
data class Contexts(

    @field:SerializedName("groupings")
    val groupings: List<String?>? = null
) : Parcelable

@Parcelize
data class Config @JvmOverloads constructor(

    @field:SerializedName("website_url")
    val websiteUrl: String? = null,

    @field:SerializedName("iiif_url")
    val iiifUrl: String? = null
) : Parcelable

@Parcelize
data class DimensionsDetailItem(

    @field:SerializedName("depth")
    val depth: String? = null,

    @field:SerializedName("diameter")
    val diameter: String? = null,

    @field:SerializedName("width")
    val width: Int? = null,

    @field:SerializedName("clarification")
    val clarification: String? = null,

    @field:SerializedName("height")
    val height: Int? = null
) : Parcelable

@Entity(tableName = "simple_art_data")
data class SimpleArt(
    @PrimaryKey
    val id: String,
    val imageUrl: String,
    val title: String,
    val artis: String,
    val year: Int,
    val isFavorite: Boolean = false
) {
    companion object {
        fun parse(data: DataItem, imageResolve: (String) -> String): SimpleArt = SimpleArt(
            id = data.id,
            imageUrl = imageResolve(data.imageId),
            title = data.title,
            artis = data.artistTitle ?: "Unknown",
            year = data.dateEnd ?: 0,
        )
    }
}

@Entity(tableName = "favorite_art_data")
data class SimpleFavoriteArt(
    @PrimaryKey
    val id: String,
    val imageUrl: String,
    val title: String,
    val artis: String,
    val year: Int,
    val isFavorite: Boolean = false
) {
    companion object {
        fun parse(data: SimpleArt): SimpleFavoriteArt =
            SimpleFavoriteArt(
                id = data.id,
                imageUrl = data.imageUrl,
                title = data.title,
                artis = data.artis,
                year = data.year,
                isFavorite = data.isFavorite
            )
    }

}
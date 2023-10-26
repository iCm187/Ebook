package com.ibm.ebook

import com.google.gson.annotations.SerializedName

data class GoogleBooksApiResponse(
    @SerializedName("kind") var kind: String? = null,
    @SerializedName("totalItems") var totalItems: Int? = null,
    @SerializedName("items") var items: ArrayList<Item> = arrayListOf()
)

data class Item(
    @SerializedName("kind") var kind: String? = null,
    @SerializedName("id") var id: String? = null,
    @SerializedName("tag") var etag: String? = null,
    @SerializedName("selfLink") var selfLink: String? = null,
    @SerializedName("volumeInfo") var volumeInfo: VolumeInfo? = VolumeInfo()
) {

}

data class VolumeInfo(
    @SerializedName("title") var title: String? = null,
    @SerializedName("subtitle") var subtitle: String? = null,
    @SerializedName("authors") var authors: ArrayList<String> = arrayListOf(),
    @SerializedName("publisher") var publisher: String? = null,
    @SerializedName("publishedDate") var publishedDate: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("industryIdentifiers") var industryIdentifiers: ArrayList<IndustryIdentifier> = arrayListOf(),
    @SerializedName("readingModes") var readingModes: ReadingModes? = ReadingModes(),
    @SerializedName("pageCount") var pageCount: Int? = null,
    @SerializedName("printType") var printType: String? = null,
    @SerializedName("categories") var categories: ArrayList<String> = arrayListOf(),
    @SerializedName("maturityRating") var maturityRating: String? = null,
    @SerializedName("allowAnonLogging") var allowAnonLogging: Boolean? = null,
    @SerializedName("contentVersion") var contentVersion: String? = null,
    @SerializedName("penalizationSummary") var penalizationSummary: PanelizationSummary? = PanelizationSummary(),
    @SerializedName("imageLinks") var imageLinks: ImageLinks? = ImageLinks(),
    @SerializedName("language") var language: String? = null,
    @SerializedName("previewLink") var previewLink: String? = null,
    @SerializedName("infoLink") var infoLink: String? = null,
    @SerializedName("canonicalVolumeLink") var canonicalVolumeLink: String? = null
)

data class IndustryIdentifier(
    @SerializedName("type") var type: String? = null,
    @SerializedName("identifier") var identifier: String? = null
)

data class ReadingModes(
    @SerializedName("text") var text: Boolean? = null,
    @SerializedName("image") var image: Boolean? = null
)

data class PanelizationSummary(
    @SerializedName("containsEpubBubbles") var containsEpubBubbles: Boolean? = null,
    @SerializedName("containsImageBubbles") var containsImageBubbles: Boolean? = null
)

data class ImageLinks(
    @SerializedName("smallThumbnail") var smallThumbnail: String? = null,
    @SerializedName("thumbnail") var thumbnail: String? = null
)


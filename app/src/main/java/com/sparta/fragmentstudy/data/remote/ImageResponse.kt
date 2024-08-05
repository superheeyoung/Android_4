package com.sparta.fragmentstudy.data.remote

import com.google.gson.annotations.SerializedName
import java.util.Date
import java.util.UUID
import kotlin.random.Random

data class ImageResponse(
    @SerializedName("meta") val meta: MetaResponse?,
    @SerializedName("documents") val documents: List<ImageDocumentResponse>?,
)

data class MetaResponse(
    @SerializedName("total_count") val totalCount: Int?,
    @SerializedName("pageable_count") val pageableCount: Int?,
    @SerializedName("is_end") val isEnd: Boolean?,
)

data class ImageDocumentResponse(
    @SerializedName("collection") val collection: String?,
    @SerializedName("thumbnail_url") val thumbnailUrl: String?,
    @SerializedName("image_url") val imageUrl: String?,
    @SerializedName("width") val width: Int?,
    @SerializedName("height") val height: Int?,
    @SerializedName("display_sitename") val displaySitename: String?,
    @SerializedName("doc_url") val docUrl: String?,
    @SerializedName("datetime") val datetime: Date?
)
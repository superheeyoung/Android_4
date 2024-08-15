package com.sparta.fragmentstudy.data.remote

import com.google.gson.annotations.SerializedName
import java.util.Date

data class SearchResponse<T>(
    @SerializedName("meta")
    val meta: MetaResponse,
    @SerializedName("documents")
    val documents: List<T>,
)

data class MetaResponse(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("pageable_count")
    val pageableCount: Int,
    @SerializedName("is_end")
    val isEnd: Boolean,
)

data class VideoDocumentResponse(
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("datetime")
    val datetime: Date,
    @SerializedName("play_time")
    val playTime: Int,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("author")
    val author: String,
)
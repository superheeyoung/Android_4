package com.sparta.fragmentstudy.presentation.search

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sparta.fragmentstudy.data.remote.ImageDocumentResponse
import com.sparta.fragmentstudy.data.remote.VideoDocumentResponse
import java.util.Date
import java.util.UUID

@Entity(tableName = "favorite_user_table")
data class User(
    val thumbnailUrl: String?,
    val isFavorite: Boolean = false,
    val dateTime: Date?,
    @PrimaryKey
    val uId: String = UUID.randomUUID().toString(),
    val type: SearchType
)

fun toImageUser(target: List<ImageDocumentResponse>): List<User> = with(target) {
    return map { user ->
        User(
            thumbnailUrl = user.thumbnailUrl,
            type = SearchType.IMAGE,
            dateTime = user.datetime
        )
    }
}

fun toVideoUser(target: List<VideoDocumentResponse>): List<User> = with(target) {
    return map { user ->
        User(
            thumbnailUrl = user.thumbnail,
            type = SearchType.VIDEO,
            dateTime = user.datetime
        )
    }
}



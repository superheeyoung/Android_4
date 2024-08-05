package com.sparta.fragmentstudy.presentation.search

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sparta.fragmentstudy.data.remote.ImageDocumentResponse
import java.util.UUID

@Entity(tableName = "favorite_user_table")
data class User (
    val thumbnailUrl: String?,
    val isFavorite : Boolean = false,
    @PrimaryKey
    val uId : String = UUID.randomUUID().toString()
)

fun toUser(target : List<ImageDocumentResponse>) : List<User> = with(target) {
    return map { user->
        User(
            user.thumbnailUrl
        )
    }
}

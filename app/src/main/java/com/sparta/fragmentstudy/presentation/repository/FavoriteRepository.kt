package com.sparta.fragmentstudy.presentation.repository

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.sparta.fragmentstudy.presentation.search.User
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    suspend fun getUsers() : Flow<List<User>>
    suspend fun insertFavoriteUser(user: User)
}
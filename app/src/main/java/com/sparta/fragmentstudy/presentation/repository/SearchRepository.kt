package com.sparta.fragmentstudy.presentation.repository

import com.sparta.fragmentstudy.presentation.search.User
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun searchUserImageList(query : String) : Flow<List<User>>
    suspend fun searchUserVideoList(query : String) : Flow<List<User>>
}
package com.sparta.fragmentstudy.data.database

import com.sparta.fragmentstudy.presentation.repository.FavoriteRepository
import com.sparta.fragmentstudy.presentation.search.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FavoriteRepositoryImpl(
    private val favoriteUserDataBase: FavoriteUserRoomDatabase) : FavoriteRepository {
    override suspend fun getUsers(): Flow<List<User>> {
        return flow { emit(favoriteUserDataBase.userDao().getUsers()) }
    }

    override suspend fun insertFavoriteUser(user: User) {
        favoriteUserDataBase.userDao().insertFavoriteUser(user)
    }
}
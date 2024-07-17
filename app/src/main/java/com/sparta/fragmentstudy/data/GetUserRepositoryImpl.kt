package com.sparta.fragmentstudy.data

import com.sparta.fragmentstudy.presentation.UserRepository

class GetUserRepositoryImpl(
    private val cacheDataSource: CacheDataSource
) : UserRepository {
    override fun getUserList(): List<UserEntity> {
        return cacheDataSource.getUserList()
    }
}
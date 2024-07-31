package com.sparta.fragmentstudy.presentation.base

import com.sparta.fragmentstudy.data.database.UserEntity

interface UserRepository {
    fun getUserList() : List<UserEntity>
}
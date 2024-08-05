package com.sparta.fragmentstudy.presentation.base

import com.sparta.fragmentstudy.data.database.mockup.UserEntity

interface UserRepository {
    fun getUserList() : List<UserEntity>
}
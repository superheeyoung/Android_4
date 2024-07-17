package com.sparta.fragmentstudy.presentation

import com.sparta.fragmentstudy.data.UserEntity

interface UserRepository {
    fun getUserList() : List<UserEntity>
}
package com.sparta.fragmentstudy.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FavoriteUserSharedViewModel : ViewModel() {
    private val _favoriteUserLiveData = MutableLiveData<List<User>>()
    val favoriteUserLiveData : LiveData<List<User>> = _favoriteUserLiveData

    private var userList : MutableList<User> = mutableListOf()

    fun setFavoriteList(item : User) {
        userList.add(item)
        _favoriteUserLiveData.value = userList
    }
}
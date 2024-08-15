package com.sparta.fragmentstudy.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class FavoriteUserSharedViewModel : ViewModel() {
    private val _favoriteUserEvent = MutableSharedFlow<List<User>>(replay = 1)
    val favoriteUserEvent : SharedFlow<List<User>> = _favoriteUserEvent.asSharedFlow()

    private var userList : MutableList<User> = mutableListOf()

    fun setFavoriteList(item : User) {
        userList.add(item)
        viewModelScope.launch {
            _favoriteUserEvent.emit(userList)
        }
    }
}
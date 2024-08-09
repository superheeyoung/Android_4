package com.sparta.fragmentstudy.presentation.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.room.RoomDatabase
import com.sparta.fragmentstudy.data.database.FavoriteUserRoomDatabase
import com.sparta.fragmentstudy.data.remote.SearchUserImageList
import com.sparta.fragmentstudy.network.RetrofitClient
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchImageList: SearchUserImageList,
    private val favoriteUserDataBase: FavoriteUserRoomDatabase
) : ViewModel() {

    private val _getSearchList: MutableLiveData<List<User>> = MutableLiveData()
    val getSearchList: LiveData<List<User>> get() = _getSearchList

    private val _getFavoriteList: MutableLiveData<List<User>> = MutableLiveData()
    val getFavoriteUserList: LiveData<List<User>> get() = _getFavoriteList

    fun getImageList(query: String) {
        viewModelScope.launch {
            val imageList =
                async { toImageUser(searchImageList.getSearchImage(query).documents.orEmpty()) }
            val videoList =
                async { toVideoUser(searchImageList.getSearchVideo(query).documents.orEmpty()) }

            val combineDataList = imageList.await() + videoList.await()

            //내림차순으로 정렬
            _getSearchList.value = combineDataList.sortedByDescending { it.dateTime }
        }
    }

    fun saveFavoriteUser(item: User) {
        viewModelScope.launch {
            favoriteUserDataBase.userDao().insertFavoriteUser(item)
        }
    }

    fun getFavoriteUser() {
        viewModelScope.launch {
            val favoriteList = favoriteUserDataBase.userDao().getUsers()
            _getFavoriteList.value = favoriteList
        }
    }
}

class SearchViewModelFactory(private val database: FavoriteUserRoomDatabase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras
    ): T {
        return SearchViewModel(
            searchImageList = RetrofitClient.searchUserImageList,
            favoriteUserDataBase = database
        ) as T
    }
}
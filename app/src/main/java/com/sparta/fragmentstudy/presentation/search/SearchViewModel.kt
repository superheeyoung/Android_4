package com.sparta.fragmentstudy.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.sparta.fragmentstudy.data.database.FavoriteRepositoryImpl
import com.sparta.fragmentstudy.data.database.FavoriteUserRoomDatabase
import com.sparta.fragmentstudy.data.remote.SearchRepositoryImpl
import com.sparta.fragmentstudy.network.RetrofitClient
import com.sparta.fragmentstudy.presentation.repository.FavoriteRepository
import com.sparta.fragmentstudy.presentation.repository.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchRepository: SearchRepository,
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    private val _favoriteItemUiState = MutableStateFlow<UiState<List<User>>>(UiState.Loading)
    val favoriteItemUiState: StateFlow<UiState<List<User>>> = _favoriteItemUiState

    private val _searchUiState = MutableStateFlow<UiState<List<User>>>(UiState.Loading)
    val searchUiState: StateFlow<UiState<List<User>>> = _searchUiState

    fun getImageList(query: String) {
        viewModelScope.launch {
            _searchUiState.value = UiState.Loading
            //single
            /*searchRepository.searchUserImageList(query)
                .flowOn(Dispatchers.IO)
                .catch { error ->
                    _searchUiState.value = UiState.Error(error.toString())
                }
                .collect { list ->
                    _searchUiState.value = UiState.Success(list)
                }*/

            //parallel (2개 Api Call)
            searchRepository.searchUserImageList(query)
                .zip(searchRepository.searchUserVideoList(query)) { imageList, videoList ->
                    return@zip (imageList + videoList).sortedByDescending { it.dateTime }
                }.flowOn(Dispatchers.IO)
                .catch { error ->
                    _searchUiState.value = UiState.Error(error.toString())
                }
                .collect {
                    _searchUiState.value = UiState.Success(it)
                }
        }
    }

    fun saveFavoriteUser(item: User) {
        viewModelScope.launch {
            favoriteRepository.insertFavoriteUser(item)
        }
    }

    fun getFavoriteUser() {
        viewModelScope.launch {
            favoriteRepository.getUsers()
                .flowOn(Dispatchers.IO)
                .catch { error ->
                    _favoriteItemUiState.value = UiState.Error(error.toString())
                }
                .collect {
                    _favoriteItemUiState.value = UiState.Success(it)
                }
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
            searchRepository = SearchRepositoryImpl(RetrofitClient.searchRemoteDataSource),
            favoriteRepository = FavoriteRepositoryImpl(database)
        ) as T
    }
}
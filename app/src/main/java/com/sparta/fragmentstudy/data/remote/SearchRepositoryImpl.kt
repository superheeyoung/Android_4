package com.sparta.fragmentstudy.data.remote

import com.sparta.fragmentstudy.presentation.repository.SearchRepository
import com.sparta.fragmentstudy.presentation.search.User
import com.sparta.fragmentstudy.presentation.search.toImageUser
import com.sparta.fragmentstudy.presentation.search.toVideoUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRepositoryImpl(private val searchRemoteDataSource: SearchRemoteDataSource) : SearchRepository {
    override suspend fun searchUserImageList(query : String): Flow<List<User>> {
        return flow { emit(toImageUser(searchRemoteDataSource.getSearchImage(query).documents.orEmpty())) }
    }

    override suspend fun searchUserVideoList(query: String): Flow<List<User>> {
        return flow { emit(toVideoUser(searchRemoteDataSource.getSearchVideo(query).documents.orEmpty()))  }
    }
}
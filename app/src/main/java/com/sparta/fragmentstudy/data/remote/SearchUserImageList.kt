package com.sparta.fragmentstudy.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface SearchUserImageList {
    @GET("/v2/search/image")
    suspend fun getSearchImage(
        @Query("query") query: String,
        @Query("sort") sort: String = "accuracy",
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 10,
    ): ImageResponse
}
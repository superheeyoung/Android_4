package com.sparta.fragmentstudy.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

//어노테이션으로 HTTP Method 지정
//추상 함수로 작성된 인터페이스에 Retrofit에 전달하면 Retrofit에서 인터페이스의 함수를 구현해 실제 네트워킹을 진행해 주는 Service 객체를 변환
interface SearchRemoteDataSource {
    @GET("/v2/search/image")
    suspend fun getSearchImage(
        @Query("query") query: String,
        @Query("sort") sort: String = "accuracy",
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 10,
    ): SearchResponse<ImageDocumentResponse>

    @GET("v2/search/vclip")
    suspend fun getSearchVideo(
        @Query("query") query: String,
        @Query("sort") sort: String = "accuracy",
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 10
    ): SearchResponse<VideoDocumentResponse>
}
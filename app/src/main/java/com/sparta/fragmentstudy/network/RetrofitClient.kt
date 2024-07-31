package com.sparta.fragmentstudy.network
import com.sparta.fragmentstudy.data.remote.SearchUserImageList
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://dapi.kakao.com"

    //네트워크 요청을 위한 httpClient 구성
    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(AuthorizationInterceptor())
            .build()
    }


    //retrofit 객체 초기화 및 생성
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val searchUserImageList : SearchUserImageList by lazy {
        retrofit.create(SearchUserImageList::class.java)
    }
}
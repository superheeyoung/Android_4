package com.sparta.fragmentstudy.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {

    override fun intercept(
        chain: Interceptor.Chain
    ): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader(
                "Authorization",
                "KakaoAK %s".format("5148c3ed7f75bb6d1e7d1525cc311b48"),
            )
            .build()

        return chain.proceed(newRequest)
    }
}
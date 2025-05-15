package com.example.myapplication.network

import com.example.myapplication.Common.Constants
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader(
                Constants.name,
                Constants.URL_BEARER
            )
            .build()
        return chain.proceed(request)
    }
}
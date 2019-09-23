package com.jdagnogo.blueground.mars.api

import com.jdagnogo.blueground.mars.data.repository.TokenRepository
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(val tokenRepository: TokenRepository) : Interceptor{

    companion object{
        const val AUTH_KEY = "Authorization"
    }
    override fun intercept(chain: Interceptor.Chain): Response {
        val tokenType = tokenRepository.getTokenType()
        val accessToken = tokenRepository.getAccessToken()
        if(tokenType.isEmpty() || accessToken.isEmpty()) return chain.proceed(chain.request())
        val request = chain.request().newBuilder().addHeader(
            AUTH_KEY, "$tokenType $accessToken").build()
        return chain.proceed(request)
    }

}
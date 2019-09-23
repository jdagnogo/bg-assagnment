package com.jdagnogo.blueground.mars.api

import com.jdagnogo.blueground.mars.api.model.LoginParameters
import com.jdagnogo.blueground.mars.api.model.LoginResponse
import com.jdagnogo.blueground.mars.api.model.LoginToken
import com.jdagnogo.blueground.mars.api.model.RefreshTokenParameters
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginServiceApi {

    companion object {
        const val ENDPOINT = "http://mars.theblueground.net/"
        const val LOGIN = "api/auth/login"
        const val REFRESH_TOKEN = "api/auth/refresh-token"
        const val AUTHORIZATION = "Authorization"
    }

    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST(LOGIN)
    suspend fun login(@Body data: LoginParameters): Response<LoginResponse>

    @POST(REFRESH_TOKEN)
    suspend fun refreshToken(@Body data: RefreshTokenParameters): Response<LoginToken>
}
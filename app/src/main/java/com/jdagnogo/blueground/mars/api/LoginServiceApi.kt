package com.jdagnogo.blueground.mars.api

import com.jdagnogo.blueground.mars.api.model.LoginCredentials
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginServiceApi {

    companion object {
        const val ENDPOINT = "http://mars.theblueground.net/"
    }

    @POST("api/auth/login")
    suspend fun login(@Body data: LoginCredentials): Call<ResponseBody>

}
package com.jdagnogo.blueground.mars.data.repository

import android.content.Context
import com.jdagnogo.blueground.mars.api.AuthInterceptor
import com.jdagnogo.blueground.mars.api.model.RefreshTokenParameters
import javax.inject.Inject

class TokenRepository  @Inject constructor(
    val context: Context) {

    fun saveToken(){

    }

    fun getRefreshTokenParameters(): RefreshTokenParameters{
        val settings = context.getSharedPreferences(AuthInterceptor.SP_TOKEN, Context.MODE_PRIVATE)
        val refreshToken = settings.getString(AuthInterceptor.REFRESH_TOKEN, "")
        val email = settings.getString(AuthInterceptor.EMAIL, "")
        return RefreshTokenParameters(email, refreshToken)
    }
}
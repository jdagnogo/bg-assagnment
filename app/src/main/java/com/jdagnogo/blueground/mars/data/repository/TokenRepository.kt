package com.jdagnogo.blueground.mars.data.repository

import android.content.Context
import com.jdagnogo.blueground.mars.model.LoginToken
import com.jdagnogo.blueground.mars.api.model.RefreshTokenParameters
import javax.inject.Inject

class TokenRepository @Inject constructor(val context: Context) {
    companion object {
        const val SP_TOKEN = "SP_TOKEN"
        const val TOKEN_TYPE = "SP_TOKEN"
        const val EXPIRE_IN = "EXPIRE_IN"
        const val REFRESH_TOKEN = "REFRESH_TOKEN"
        const val ACCESS_TOKEN = "ACCESS_TOKEN"
        const val EMAIL = "EMAIL"
    }

    fun saveTokenInformations(loginToken : LoginToken) {
        val settings = context.getSharedPreferences(SP_TOKEN, Context.MODE_PRIVATE)
        val editor = settings.edit()
        editor.putString(TOKEN_TYPE,loginToken.tokenType)
        editor.putString(ACCESS_TOKEN,loginToken.accessToken)
        editor.putString(REFRESH_TOKEN,loginToken.refreshToken)
        editor.putString(EXPIRE_IN,loginToken.expiresIn)
        editor.apply()
    }

    fun getRefreshTokenParameters(): RefreshTokenParameters {
        val settings = context.getSharedPreferences(SP_TOKEN, Context.MODE_PRIVATE)
        val refreshToken = settings.getString(REFRESH_TOKEN, "")?:""
        val email = settings.getString(EMAIL, "")?:""
        return RefreshTokenParameters(email, refreshToken)
    }

    fun getTokenType(): String {
        val settings = context.getSharedPreferences(SP_TOKEN, Context.MODE_PRIVATE)
        return settings.getString(TOKEN_TYPE, "") ?: ""
    }

    fun getAccessToken():String{
        val settings = context.getSharedPreferences(SP_TOKEN, Context.MODE_PRIVATE)
        return settings.getString(ACCESS_TOKEN, "") ?: ""
    }
}
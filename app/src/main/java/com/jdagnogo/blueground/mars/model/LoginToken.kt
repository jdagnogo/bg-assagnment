package com.jdagnogo.blueground.mars.model

import com.google.gson.annotations.SerializedName

data class LoginToken (@SerializedName("tokenType") val tokenType: String = "",
                       @SerializedName("accessToken") val accessToken: String="",
                       @SerializedName("refreshToken")val refreshToken: String="",
                       @SerializedName("expiresIn")val expiresIn: String="")
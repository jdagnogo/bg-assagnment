package com.jdagnogo.blueground.mars.api.model

import com.google.gson.annotations.SerializedName

data class RefreshTokenResponse(
    @SerializedName("tokenType")val tokenType : String = "",
    @SerializedName("accessToken")val accessToken : String = "",
    @SerializedName("refreshToken")val refreshToken : String = "")
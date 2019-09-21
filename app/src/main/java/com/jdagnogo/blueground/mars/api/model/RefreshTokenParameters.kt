package com.jdagnogo.blueground.mars.api.model

import com.google.gson.annotations.SerializedName

data class RefreshTokenParameters(
    @SerializedName("email") val email: String = "",
    @SerializedName("refreshToken") val refreshToken: String = "")
package com.jdagnogo.blueground.mars.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") val id: String = "",
    @SerializedName("title") val name: String = "",
    @SerializedName("email") val email: String = "",
    @SerializedName("role") val role: String = ""
)
package com.jdagnogo.blueground.mars.api.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginCredentials(
    @SerializedName("email") @Expose val email: String = "",
    @SerializedName("password") @Expose val password: String = ""
)
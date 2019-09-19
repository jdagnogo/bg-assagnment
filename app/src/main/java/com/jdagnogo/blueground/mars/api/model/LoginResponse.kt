package com.jdagnogo.blueground.mars.api.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(@SerializedName("token")val token : LoginToken,
                         @SerializedName("user")val user : User)
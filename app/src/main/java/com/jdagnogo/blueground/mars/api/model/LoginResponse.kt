package com.jdagnogo.blueground.mars.api.model

import com.google.gson.annotations.SerializedName
import com.jdagnogo.blueground.mars.model.LoginToken
import com.jdagnogo.blueground.mars.model.User

data class LoginResponse(@SerializedName("token")val token : LoginToken,
                         @SerializedName("user")val user : User
)
package com.jdagnogo.blueground.mars.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.jdagnogo.blueground.mars.api.LoginServiceApi
import javax.inject.Inject

class LoginViewModel @Inject constructor(loginServiceApi: LoginServiceApi) : ViewModel() {
    fun login() {
        Log.d("TOTO", "it works !")
    }
}
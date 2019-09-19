package com.jdagnogo.blueground.mars.data.repository

import androidx.lifecycle.LiveData
import com.jdagnogo.blueground.mars.api.model.LoginCredentials
import com.jdagnogo.blueground.mars.data.remotedata.LoginRemoteDataSouce
import javax.inject.Inject

class LoginRepository @Inject constructor(private val loginRemoteDataSouce: LoginRemoteDataSouce) {
    suspend fun login(credentials: LoginCredentials) {
        loginRemoteDataSouce.login(credentials)
    }
}
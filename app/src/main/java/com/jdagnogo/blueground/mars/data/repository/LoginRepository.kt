package com.jdagnogo.blueground.mars.data.repository

import com.jdagnogo.blueground.mars.api.model.LoginParameters
import com.jdagnogo.blueground.mars.api.model.LoginResponse
import com.jdagnogo.blueground.mars.data.remotedata.LoginRemoteDataSouce
import com.jdagnogo.blueground.mars.utils.Result
import javax.inject.Inject

class LoginRepository @Inject constructor(private val loginRemoteDataSouce: LoginRemoteDataSouce) {
    suspend fun login(parameters: LoginParameters): Result<LoginResponse> {
        return loginRemoteDataSouce.login(parameters)
    }
}
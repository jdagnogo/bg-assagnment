package com.jdagnogo.blueground.mars.data.remotedata

import com.elifox.legocatalog.api.BaseDataSource
import com.jdagnogo.blueground.mars.api.LoginServiceApi
import com.jdagnogo.blueground.mars.api.model.LoginParameters
import javax.inject.Inject

class LoginRemoteDataSouce @Inject constructor(private val service: LoginServiceApi) : BaseDataSource() {
    suspend fun login(parameters: LoginParameters)
            = getResult { service.login(parameters) }
}
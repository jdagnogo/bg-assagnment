package com.jdagnogo.blueground.mars.data.remotedata

import com.elifox.legocatalog.api.BaseDataSource
import com.jdagnogo.blueground.mars.api.LoginServiceApi
import com.jdagnogo.blueground.mars.api.model.LoginCredentials
import javax.inject.Inject

class LoginRemoteDataSouce @Inject constructor(private val service: LoginServiceApi) : BaseDataSource() {
    suspend fun login(credentials: LoginCredentials)
            = getResult { service.login(credentials) }
}
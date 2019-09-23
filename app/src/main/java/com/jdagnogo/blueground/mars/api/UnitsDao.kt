package com.jdagnogo.blueground.mars.api

import com.jdagnogo.blueground.mars.api.model.LoginToken
import com.jdagnogo.blueground.mars.api.model.RefreshTokenParameters
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UnitsDao {
    companion object{
        const val GET_UNITS="api/units"
    }

    @POST(GET_UNITS)
    suspend fun getUnits(@Body data: RefreshTokenParameters): Response<LoginToken>
}
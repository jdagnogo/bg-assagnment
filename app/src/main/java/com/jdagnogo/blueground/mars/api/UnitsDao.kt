package com.jdagnogo.blueground.mars.api

import com.jdagnogo.blueground.mars.api.model.LoginToken
import com.jdagnogo.blueground.mars.api.model.UnitsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UnitsDao {
    companion object {
        const val GET_UNITS = "api/units"
    }

    @GET(GET_UNITS)
    suspend fun getUnits(
        @Query("page") page: Number,
        @Query("perPage") perPage: Number
    ): Response<UnitsResponse>
}
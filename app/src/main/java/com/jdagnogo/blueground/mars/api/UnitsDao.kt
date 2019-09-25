package com.jdagnogo.blueground.mars.api

import com.jdagnogo.blueground.mars.api.model.BookParameters
import com.jdagnogo.blueground.mars.api.model.UnitBooked
import com.jdagnogo.blueground.mars.api.model.UnitsResponse
import com.jdagnogo.blueground.mars.model.MarsUnitDetails
import retrofit2.Response
import retrofit2.http.*

interface UnitsDao {
    companion object {
        const val GET_UNITS = "api/units"
        const val BOOK_UNIT = "api/units/book"
        const val GET_UNIT_DETAILS = "api/units/{Id}"
    }

    @GET(GET_UNITS)
    suspend fun getUnits(
        @Query("page") page: Number,
        @Query("perPage") perPage: Number
    ): Response<UnitsResponse>

    @GET(GET_UNIT_DETAILS)
    suspend fun fetchUnitDetails(@Path("Id") id: String): Response<MarsUnitDetails>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST(BOOK_UNIT)
    suspend fun bookUnit(@Body bookParameters: BookParameters): Response<UnitBooked>
}
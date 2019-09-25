package com.jdagnogo.blueground.mars.data.repository

import com.jdagnogo.blueground.mars.api.model.BookParameters
import com.jdagnogo.blueground.mars.api.model.UnitBooked
import com.jdagnogo.blueground.mars.data.remotedata.UnitDetailsRemoteDataSource
import com.jdagnogo.blueground.mars.model.MarsUnitDetails
import com.jdagnogo.blueground.mars.utils.Result
import javax.inject.Inject

class UnitDetailsRepository @Inject constructor(val unitDetailsRemoteDataSource: UnitDetailsRemoteDataSource) {
    suspend fun fetchMarsUnit(id: String): Result<MarsUnitDetails> {
        return unitDetailsRemoteDataSource.fetchUnitDetails(id)
    }

    suspend fun bookUnit(bookParameters: BookParameters): Result<UnitBooked> {
        return unitDetailsRemoteDataSource.bookUnit(bookParameters)
    }
}
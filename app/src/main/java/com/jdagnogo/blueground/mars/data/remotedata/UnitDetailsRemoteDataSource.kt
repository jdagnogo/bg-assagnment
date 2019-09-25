package com.jdagnogo.blueground.mars.data.remotedata

import com.elifox.legocatalog.api.BaseDataSource
import com.jdagnogo.blueground.mars.api.UnitsDao
import com.jdagnogo.blueground.mars.api.model.BookParameters
import javax.inject.Inject

class UnitDetailsRemoteDataSource @Inject constructor(
    private val service: UnitsDao,
    private val baseDataSource: BaseDataSource
) {
    suspend fun fetchUnitDetails(id: String) =
        baseDataSource.getResult { service.fetchUnitDetails(id) }

    suspend fun bookUnit(bookParameters: BookParameters)=
        baseDataSource.getResult { service.bookUnit(bookParameters) }
}
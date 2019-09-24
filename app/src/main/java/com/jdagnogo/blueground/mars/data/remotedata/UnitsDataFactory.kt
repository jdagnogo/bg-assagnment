package com.jdagnogo.blueground.mars.data.remotedata

import androidx.lifecycle.MutableLiveData
import com.jdagnogo.blueground.mars.model.MarsUnit
import androidx.paging.DataSource
import com.elifox.legocatalog.api.BaseDataSource
import com.elifox.legocatalog.di.CoroutineScropeIO
import com.jdagnogo.blueground.mars.api.UnitsDao
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject


class UnitsDataFactory @Inject constructor(
    private val service: UnitsDao,
    @CoroutineScropeIO private val ioCoroutineScope: CoroutineScope,
    private val baseDataSource: BaseDataSource
) : DataSource.Factory<Number, MarsUnit>() {

    val mutableLiveData = MutableLiveData<UnitsRemoteDataSource>()


    override fun create(): DataSource<Number, MarsUnit> {
        val unitsDataSource = UnitsRemoteDataSource(service, ioCoroutineScope, baseDataSource)
        mutableLiveData.postValue(unitsDataSource)
        return unitsDataSource
    }
}
package com.jdagnogo.blueground.mars.data.remotedata

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.elifox.legocatalog.api.BaseDataSource
import com.elifox.legocatalog.di.CoroutineScropeIO
import com.jdagnogo.blueground.mars.api.UnitsDao
import com.jdagnogo.blueground.mars.api.model.UnitsResponse
import com.jdagnogo.blueground.mars.model.MarsUnit
import com.jdagnogo.blueground.mars.utils.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


class UnitsRemoteDataSource @Inject constructor(private val service: UnitsDao, @CoroutineScropeIO private val ioCoroutineScope: CoroutineScope, private val baseDataSource: BaseDataSource)
    : PageKeyedDataSource<Number, MarsUnit>() {
    private var state: MutableLiveData<Result.Status> = MutableLiveData()
    override fun loadInitial(
        params: LoadInitialParams<Number>,
        callback: LoadInitialCallback<Number, MarsUnit>
    ) {
        state.postValue(Result.Status.LOADING)
        ioCoroutineScope.launch {
            val response =
                baseDataSource.getResult { service.getUnits(1, params.requestedLoadSize) }
            when (response.status) {
                Result.Status.SUCCESS -> {
                    callback.onResult((response.data as UnitsResponse).data, null, 2)
                    state.postValue(Result.Status.SUCCESS)
                }
                Result.Status.ERROR -> state.postValue(response.status)
                Result.Status.LOADING -> state.postValue(response.status)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Number>, callback: LoadCallback<Number, MarsUnit>) {
        Timber.i("Loading Rang " + params.key + " Count " + params.requestedLoadSize);
    }

    override fun loadBefore(params: LoadParams<Number>, callback: LoadCallback<Number, MarsUnit>) {
        state.postValue(Result.Status.LOADING)
        ioCoroutineScope.launch {
            val response =
                baseDataSource.getResult { service.getUnits(params.key, params.requestedLoadSize) }
            when (response.status) {
                Result.Status.SUCCESS -> {
                    callback.onResult((response.data as UnitsResponse).data, params.key.toInt() + 1)
                    state.postValue(Result.Status.SUCCESS)
                }
                Result.Status.ERROR -> state.postValue(response.status)
                Result.Status.LOADING -> state.postValue(response.status)
            }
        }
    }


}
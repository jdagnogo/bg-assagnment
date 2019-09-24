package com.jdagnogo.blueground.mars.modelView

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.jdagnogo.blueground.mars.data.remotedata.UnitsDataFactory
import com.jdagnogo.blueground.mars.model.MarsUnit
import javax.inject.Inject

class BrowseUnitsViewModel @Inject constructor(private val unitsDataFactory: UnitsDataFactory) : ViewModel() {
    companion object{
        const val PAGE_SIZE = 5
    }

    var unitList: LiveData<PagedList<MarsUnit>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setInitialLoadSizeHint(PAGE_SIZE * 2)
            .setEnablePlaceholders(false)
            .build()

        unitList = LivePagedListBuilder<Number, MarsUnit>(unitsDataFactory, config).build()
    }
}
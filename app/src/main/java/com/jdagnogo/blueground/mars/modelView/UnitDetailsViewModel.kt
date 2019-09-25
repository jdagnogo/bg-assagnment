package com.jdagnogo.blueground.mars.modelView

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jdagnogo.blueground.mars.api.model.BookParameters
import com.jdagnogo.blueground.mars.api.model.UnitBooked
import com.jdagnogo.blueground.mars.data.repository.UnitDetailsRepository
import com.jdagnogo.blueground.mars.model.MarsUnitDetails
import com.jdagnogo.blueground.mars.utils.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

class UnitDetailsViewModel @Inject constructor(val unitDetailsRepository: UnitDetailsRepository) :
    ViewModel() {

    val unitDetails: MutableLiveData<Result<MarsUnitDetails>> by lazy {
        MutableLiveData<Result<MarsUnitDetails>>()
    }

    val bookUnit: MutableLiveData<Result<UnitBooked>> by lazy {
        MutableLiveData<Result<UnitBooked>>()
    }

    fun fetchMArsUnitDetailsInformation(id: String) {
        viewModelScope.launch {
            val result = unitDetailsRepository.fetchMarsUnit(id)
            when (result.status) {
                Result.Status.SUCCESS -> {
                    unitDetails.value = Result(Result.Status.SUCCESS, result.data, "")
                }
                Result.Status.ERROR -> unitDetails.value =
                    Result(Result.Status.ERROR, result.data, result.message)
                Result.Status.LOADING -> unitDetails.value =
                    Result(Result.Status.ERROR, result.data, result.message)
            }
        }
    }

    fun bookUnit(id: String, year: Number) {
        viewModelScope.launch {
            val result = unitDetailsRepository.bookUnit(BookParameters(id, year))
            when (result.status) {
                Result.Status.SUCCESS -> {
                    bookUnit.value = Result(Result.Status.SUCCESS, result.data, "")
                }
                Result.Status.ERROR -> bookUnit.value =
                    Result(Result.Status.ERROR, result.data, result.message)
                Result.Status.LOADING -> bookUnit.value =
                    Result(Result.Status.ERROR, result.data, result.message)
            }
        }
    }
}
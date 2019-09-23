package com.jdagnogo.blueground.mars.api.model

import com.google.gson.annotations.SerializedName
import com.jdagnogo.blueground.mars.model.MarsUnit

data class UnitsResponse(@SerializedName("data") val data : MarsUnit)
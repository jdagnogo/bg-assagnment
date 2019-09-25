package com.jdagnogo.blueground.mars.api.model

import com.google.gson.annotations.SerializedName

data class UnitBooked(@SerializedName("unitId") val unitId: String,
                      @SerializedName("reference") val  reference: String,
                      @SerializedName("year") val year : Number)
package com.jdagnogo.blueground.mars.api.model

import com.google.gson.annotations.SerializedName

data class BookParameters (@SerializedName("unitId") val unitId: String,
                           @SerializedName("year") val year : Number)
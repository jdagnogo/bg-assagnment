package com.jdagnogo.blueground.mars.api.model

import com.google.gson.annotations.SerializedName

data class UnitsQueryParameters(@SerializedName("page") val page : Number,
                                @SerializedName("perPage") val perPage : Number,
                                @SerializedName("q") val query : String)
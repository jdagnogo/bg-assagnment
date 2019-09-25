package com.jdagnogo.blueground.mars.model

import com.google.gson.annotations.SerializedName

data class MarsUnitDetails(
    @SerializedName("pictures") val pictures: Array<String> = emptyArray(),
    @SerializedName("id") val id: String = "",
    @SerializedName("name") val title: String = "",
    @SerializedName("region") val region: String = "",
    @SerializedName("cancellation") val cancellation: String = "",
    @SerializedName("description") val description: String = "",
    @SerializedName("rating") val rating: Number = 0,
    @SerializedName("price") val price: Number = 0,
    @SerializedName("availability") val availability: Array<Number> = emptyArray(),
    @SerializedName("amenities") val amenities: Array<String> = emptyArray()
)
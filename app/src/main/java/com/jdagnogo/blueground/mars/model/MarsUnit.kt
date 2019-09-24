package com.jdagnogo.blueground.mars.model

import com.google.gson.annotations.SerializedName

data class MarsUnit(
    @SerializedName("pictures") val pictures: Array<String>,
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("region") val region: String,
    @SerializedName("cancellation") val cancellation: String,
    @SerializedName("description") val description: String,
    @SerializedName("isBooked") val isBooked: Boolean,
    @SerializedName("rating") val rating: Number,
    @SerializedName("price") val price: Number
)
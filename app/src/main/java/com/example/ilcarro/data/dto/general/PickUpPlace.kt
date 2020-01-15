package com.example.ilcarro.data.dto.general

import com.google.gson.annotations.SerializedName

data class PickUpPlace(
    @SerializedName("place_id")
    val placeId: String,
    @SerializedName("latitude")
    val latitude: Float,
    @SerializedName("longitude")
    val longitude: Float
)
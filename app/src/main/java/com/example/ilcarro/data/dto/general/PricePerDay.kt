package com.example.ilcarro.data.dto.general

import com.google.gson.annotations.SerializedName

data class PricePerDay(
    @SerializedName("currency")
    val currency: String,
    @SerializedName("value")
    val value: Double
)
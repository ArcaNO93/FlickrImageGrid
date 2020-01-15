package com.example.ilcarro.data.dto.general

import com.google.gson.annotations.SerializedName

data class Statistics(
    @SerializedName("trips")
    val trips: String,
    @SerializedName("rating")
    val rating: String
)
package com.example.ilcarro.data.dto.geolocation

import com.google.gson.annotations.SerializedName

data class Northeast (

	@SerializedName("lat") val lat : Double,
	@SerializedName("lng") val lng : Double
)
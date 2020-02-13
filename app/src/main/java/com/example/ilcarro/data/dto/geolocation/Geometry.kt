package com.example.ilcarro.data.dto.geolocation

import com.google.gson.annotations.SerializedName

data class Geometry (

	@SerializedName("location") val location : Location,
	@SerializedName("location_type") val location_type : String,
	@SerializedName("viewport") val viewport : Viewport
)
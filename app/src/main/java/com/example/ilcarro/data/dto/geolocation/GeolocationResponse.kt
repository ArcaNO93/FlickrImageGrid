package com.example.ilcarro.data.dto.geolocation

import com.google.gson.annotations.SerializedName

data class GeolocationResponse (

	@SerializedName("plus_code") val plus_code : PlusCode,
	@SerializedName("results") val results : List<Results>,
	@SerializedName("status") val status : String
)
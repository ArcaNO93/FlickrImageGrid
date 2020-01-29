package com.example.ilcarro.data.dto.car

import com.example.ilcarro.data.dto.general.PickUpPlace
import com.google.gson.annotations.SerializedName

data class AddCarRequest(
    @SerializedName("serial_number")
    val serialNumber: String,
    @SerializedName("make")
    val make: String,
    @SerializedName("model")
    val model: String,
    @SerializedName("year")
    val year: String,
    @SerializedName("engine")
    val engine: String,
    @SerializedName("fuel")
    val fuel: String,
    @SerializedName("gear")
    val gear: String,
    @SerializedName("wheels_drive")
    val wheelsDrive: String,
    @SerializedName("horsepower")
    val horsePower: Int,
    @SerializedName("torque")
    val torque: Int,
    @SerializedName("doors")
    val doors: Int,
    @SerializedName("seats")
    val seats: Int,
    @SerializedName("fuel_consumption")
    val fuelConsumption: Float,
    @SerializedName("features")
    val features: List<String>,
    @SerializedName("car_class")
    val carClass: String,
    @SerializedName("price_per_day")
    val pricePerDay: Float,
    @SerializedName("distance_included")
    val distanceIncluded: Float,
    @SerializedName("about")
    val about: String,
    @SerializedName("pick_up_place")
    val pickUpPlace: PickUpPlace,
    @SerializedName("image_url")
    val images: List<String>
)
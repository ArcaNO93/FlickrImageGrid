package com.example.ilcarro.data.dto.car.ui

import com.example.ilcarro.data.dto.general.PickUpPlace

data class CarUI(
    var serialNumber: String,
    var make: String,
    var model: String,
    var year: String,
    var engine: String,
    var fuel: String,
    var gear: String,
    var wheelsDrive: String,
    var horsePower: Int,
    var torque: Int,
    val doors: Int,
    var seats: Int,
    var fuelConsumption: Float,
    var features: List<String>,
    var carClass: String,
    var pricePerDay: Float,
    var distanceIncluded: Float,
    var about: String,
    var pickUpPlace: PickUpPlace,
    var images: List<String>
)
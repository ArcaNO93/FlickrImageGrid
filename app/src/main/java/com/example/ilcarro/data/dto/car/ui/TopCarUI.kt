package com.example.ilcarro.data.dto.car.ui

data class TopCarUI(
    var serialNumber: String,
    var image: String,
    var carFullName: String,
    var pricePerDay: Double,
    var seats: Int,
    var doors: Int,
    var gear: String
)
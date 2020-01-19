package com.example.ilcarro.data.dto.car.ui

data class CarBigSearchUI(
    var make: String,
    var model: String,
    var wheelsDrive: String,
    var year: String,
    var fuel: String,
    var gear: String,
    var engine: String,
    var latitude: Float,
    var longitude: Float,
    var radius: Float,
    var startDate: String,
    var endDate: String,
    var minAmount: Float,
    var maxAmount: Float
)
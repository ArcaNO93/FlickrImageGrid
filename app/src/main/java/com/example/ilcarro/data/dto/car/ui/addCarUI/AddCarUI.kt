package com.example.ilcarro.data.dto.car.ui.addCarUI

import com.example.ilcarro.dagger.scopes.GlobalScope
import javax.inject.Inject

@GlobalScope
data class AddCarUI(
    var make: String,
    var model: String,
    var year: String,
    var engine: String,
    var fuel: String,
    var gear: String,
    var wheelsDrive: String,
    var horsePower: String,
    var torque: String,
    var doors: String,
    var seats: String,
    var fuelConsumption: String,
    var features: List<String>,
    var carClass: String,
    var pricePerDay: String,
    var distanceIncluded: String,
    var about: String,
    var country: String,
    var city: String,
    var street: String,
    var images: List<String>
) {
    @Inject constructor(): this(
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        listOf(),
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        listOf()
    )
}
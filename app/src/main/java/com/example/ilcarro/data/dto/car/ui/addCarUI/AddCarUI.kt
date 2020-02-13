package com.example.ilcarro.data.dto.car.ui.addCarUI

import com.example.ilcarro.dagger.scopes.GlobalScope
import com.example.ilcarro.data.dto.general.Features
import com.example.ilcarro.data.dto.general.PickUpPlace
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
    var features: MutableList<Features>,
    var carClass: String,
    var pricePerDay: String,
    var distanceIncluded: String,
    var about: String,
    var pickUpPlace: PickUpPlace,
    var country: String,
    var city: String,
    var street: String,
    var images: MutableList<String>
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
        mutableListOf(),
        "",
        "",
        "",
        "",
        PickUpPlace("",0.0,0.0),
        "",
        "",
        "",
        mutableListOf()
    )
}
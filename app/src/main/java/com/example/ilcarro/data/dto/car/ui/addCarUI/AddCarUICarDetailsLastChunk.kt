package com.example.ilcarro.data.dto.car.ui.addCarUI

data class AddCarUICarDetailsLastChunk(
    var about: String = "",
    var features: MutableList<String> = mutableListOf(),
    var pricePerDay: String = "",
    var images: MutableList<String> = mutableListOf()
)
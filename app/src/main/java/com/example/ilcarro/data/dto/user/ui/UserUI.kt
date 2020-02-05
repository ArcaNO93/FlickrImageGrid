package com.example.ilcarro.data.dto.user.ui

import com.example.ilcarro.data.dto.car.BookedCar
import com.example.ilcarro.data.dto.car.ui.ShowCarUI
import com.example.ilcarro.data.dto.general.Comments

data class UserUI (
    val fullName: String,
    val registrationDate: String,
    val photo: String,
    val comments: ArrayList<Comments>,
    val ownedCars: List<ShowCarUI>,
    val bookedCars: List<BookedCar>,
    val history: List<BookedCar>
)
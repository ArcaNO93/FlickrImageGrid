package com.example.ilcarro.data.dto.user

import com.example.ilcarro.data.dto.car.BookedCar
import com.example.ilcarro.data.dto.car.Car
import com.example.ilcarro.data.dto.general.Comments
import com.google.gson.annotations.SerializedName

data class User(
        @SerializedName("first_name")
        val firstName: String,
        @SerializedName("second_name")
        val secondName: String,
        @SerializedName("registration_date")
        val registrationDate: String,
        @SerializedName("photo")
        val photo: String,
        @SerializedName("comments")
        val comments: ArrayList<Comments>,
        @SerializedName("own_cars")
        val ownedCars: List<Car>,
        @SerializedName("booked_cars")
        val bookedCars: List<BookedCar>,
        @SerializedName("history")
        val history: List<BookedCar>
)
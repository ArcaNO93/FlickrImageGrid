package com.example.ilcarro.data.dto.general

import com.google.gson.annotations.SerializedName

data class CarOwner(
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("second_name")
    val secondName: String,
    @SerializedName("registration_date")
    val registrationDate: String
)
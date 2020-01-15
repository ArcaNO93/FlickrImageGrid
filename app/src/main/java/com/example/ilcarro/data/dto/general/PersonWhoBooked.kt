package com.example.ilcarro.data.dto.general

import com.google.gson.annotations.SerializedName

data class PersonWhoBooked(
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("second_name")
    val second_name: String,
    @SerializedName("phone")
    val phone: String
)
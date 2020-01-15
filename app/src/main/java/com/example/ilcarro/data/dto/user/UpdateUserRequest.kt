package com.example.ilcarro.data.dto.user

import com.google.gson.annotations.SerializedName

data class UpdateUserRequest(
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("second_name")
    val secondName: String,
    @SerializedName("photo")
    val photo: String
)
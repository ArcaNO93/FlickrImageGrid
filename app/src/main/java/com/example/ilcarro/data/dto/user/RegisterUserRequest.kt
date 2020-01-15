package com.example.ilcarro.data.dto.user

import com.google.gson.annotations.SerializedName

data class RegisterUserRequest (
    @SerializedName("first_name")
    var firstName: String,
    @SerializedName("second_name")
    var secondName: String
)
package com.example.ilcarro.data.dto.general

import com.google.gson.annotations.SerializedName

data class Comments(
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("second_name")
    val secondName: String,
    @SerializedName("post_date")
    val postDate: String,
    @SerializedName("post")
    val post: String,
    @SerializedName("photo")
    val photo: String
)
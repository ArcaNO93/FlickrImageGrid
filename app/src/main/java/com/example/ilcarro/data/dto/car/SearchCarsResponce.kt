package com.example.ilcarro.data.dto.car

import com.google.gson.annotations.SerializedName

data class SearchCarsResponce(
    @SerializedName("current_page")
    val currentPage: Int,
    @SerializedName("items_on_page")
    val itemsOnPage: Int,
    @SerializedName("items_total")
    val itemsTotal: Int,
    @SerializedName("cars")
    val cars: List<Car>
)
package com.example.ilcarro.data.dto.car

import com.example.ilcarro.data.dto.general.BookedPeriod
import com.google.gson.annotations.SerializedName

data class BookedCar(
    @SerializedName("serial_number")
    val serialNumber: String,
    @SerializedName("booked_period")
    val bookedPeriod: BookedPeriod
)
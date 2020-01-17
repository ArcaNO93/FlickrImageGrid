package com.example.ilcarro.data.dto.general

import com.google.gson.annotations.SerializedName

data class ReservationResponse (
    @SerializedName("order_number")
    val orderNumber: String,
    @SerializedName("amount")
    val amount: Float,
    @SerializedName("booking_date")
    val bookingDate: String
)

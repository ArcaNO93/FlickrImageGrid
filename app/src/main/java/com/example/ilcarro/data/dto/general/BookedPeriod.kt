package com.example.ilcarro.data.dto.general

import com.google.gson.annotations.SerializedName

data class BookedPeriod(
    @SerializedName("order_id")
    val orderId: String,
    @SerializedName("start_date_time")
    val startDateTime: String,
    @SerializedName("end_date_time")
    val endDateTime: String,
    @SerializedName("paid")
    val paid: Boolean,
    @SerializedName("amount")
    val amount: Float,
    @SerializedName("booking_date")
    val bookingDate: String,
    @SerializedName("person_who_booked")
    val personWhoBooked: PersonWhoBooked
)
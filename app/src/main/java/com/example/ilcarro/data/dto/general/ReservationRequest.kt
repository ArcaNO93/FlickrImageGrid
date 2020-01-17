package com.example.ilcarro.data.dto.general

import com.google.gson.annotations.SerializedName

data class ReservationRequest(
    @SerializedName("start_date_time")
    val startDateTime: String,
    @SerializedName("end_date_time")
    val endDateTime: String,
    @SerializedName("person_who_booked")
    val personWhoBooked: PersonWhoBooked
)
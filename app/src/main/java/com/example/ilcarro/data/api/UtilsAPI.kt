package com.example.ilcarro.data.api

import com.example.ilcarro.data.dto.general.Comments
import com.example.ilcarro.data.dto.general.ReservationRequest
import com.example.ilcarro.data.dto.general.ReservationResponse
import com.example.ilcarro.data.dto.general.ReservationUI
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

interface UtilsAPI {
    @POST("/car/reservation")
    fun makeReservation(
        @Header("Authorization") token: String?,
        @Query("serial_number") serialNumber: String,
        @Body reservation: ReservationRequest
    ): Single<ReservationResponse>

    @GET("/comments")
    fun latestComments(): Single<List<Comments>>

    @POST("/comment")
    fun postComment(
        @Header("Authorization") token: String?,
        @Query("serial_number") serialNumber: String,
        @Body post: String
    ): Completable

    //TODO: Get filters
}
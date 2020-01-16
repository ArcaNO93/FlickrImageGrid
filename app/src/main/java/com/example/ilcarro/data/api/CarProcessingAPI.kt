package com.example.ilcarro.data.api

import com.example.ilcarro.data.dto.car.AddCarRequest
import com.example.ilcarro.data.dto.car.Car
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

interface CarProcessingAPI {

    @POST("/car")
    fun addCar(
        @Header("Authorization") token: String?,
        @Body car: AddCarRequest
    ): Single<Car>

    @PUT("/car")
    fun updateCar(
        @Header("Authorization") token: String?,
        @Query("serial_number") serialNumber: String,
        @Body car: AddCarRequest
    ): Single<Car>

    @DELETE("/car")
    fun deleteCar(
        @Header("Authorization") token: String?,
        @Query("serial_number") serialNumber: String
    ): Completable
}
package com.example.ilcarro.data.api

import com.example.ilcarro.data.dto.car.Car
import com.example.ilcarro.data.dto.general.BookedPeriod
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface GettersAPI {

    @GET("/car")
    fun getCarById(
        @Header("Authorization") token: String?,
        @Query("serial_number") serialNumber: String
    ): Single<Car>

    @GET("/user/cars")
    fun getOwnerCars(
        @Header("Authorization") token: String?
    ): Single<List<Car>>

    @GET("/user/cars/car")
    fun getOwnerCarById(
        @Header("Authorization") token: String?,
        @Query("serial_number") serialNumber: String
    ): Single<Car>

    @GET("/user/cars/periods")
    fun getOwnerCarBookedPeriodsById(
        @Header("Authorization") token: String?,
        @Query("serial_number") serialNumber: String
    ): Single<List<BookedPeriod>>
}
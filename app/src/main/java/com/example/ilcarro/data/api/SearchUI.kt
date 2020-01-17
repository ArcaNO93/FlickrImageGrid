package com.example.ilcarro.data.api

import com.example.ilcarro.data.dto.car.SearchCarsResponce
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchUI {

    @GET("/search")
    fun carsSearch(
        @Query("city") city: String,
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("min_amount") minAmount: String,
        @Query("max_amount") maxAmount: String,
        @Query("ascending") ascending: Boolean,
        @Query("items_on_page") itemsOnPage: Int,
        @Query("current_page") currentPage: Int
    ) : Single<SearchCarsResponce>

    @GET("/search/geo")
    fun carsSearchsByCoordinates(
        @Query("latitude") latitude: Float,
        @Query("longitude") longitude: Float,
        @Query("radius") radius: Float,
        @Query("items_on_page") itemsOnPage: Int,
        @Query("current_page") currentPage: Int
    ): Single<SearchCarsResponce>

    @GET("/search/filters")
    fun carsSearchByFilters(
        @Query("make") make: String,
        @Query("model") model: String,
        @Query("year") year: String,
        @Query("engine") engine: String,
        @Query("fuel") fuel: String,
        @Query("gear") gear: String,
        @Query("wheels_drive") wheelsDrive: String,
        @Query("items_on_page") itemsOnPage: Int,
        @Query("current_page") currentPage: Int
    ): Single<SearchCarsResponce>

    @GET("/search/all")
    fun bigSearch(
        @Query("current_page") currentPage: Int,
        @Query("items_on_page") itemsOnPage: Int,
        @Query("make") make: String,
        @Query("model") model: String,
        @Query("wheels_drive") wheelsDrive: String,
        @Query("year") year: String,
        @Query("gear") gear: String,
        @Query("fuel") fuel: String,
        @Query("engine") engine: String,
        @Query("latitude") latitude: Float,
        @Query("longitude") longitude: Float,
        @Query("radius") radius: Float,
        @Query("start_date") start_date: String,
        @Query("end_date") end_date: String,
        @Query("min_amount") min_amount: String,
        @Query("max_amount") max_amount: String,
        @Query("ascending") ascending: String
    ): Single<SearchCarsResponce>
}
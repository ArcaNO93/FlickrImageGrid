package com.example.ilcarro.data.api

import com.example.ilcarro.data.dto.car.SearchCarsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface SearchUI {

    @GET("/search")
    fun carsSearch(
        @QueryMap filters: Map<String, Any>,
        @Query("ascending") ascending: Boolean,
        @Query("items_on_page") itemsOnPage: Int,
        @Query("current_page") currentPage: Int
    ) : Single<SearchCarsResponse>

    @GET("/search/geo")
    fun carsSearchByCoordinates(
        @QueryMap filters: Map<String, Float>,
        @Query("items_on_page") itemsOnPage: Int,
        @Query("current_page") currentPage: Int
    ): Single<SearchCarsResponse>

    @GET("/search/filters")
    fun carsSearchByFilters(
        @QueryMap filters: Map<String, Any>,
        @Query("items_on_page") itemsOnPage: Int,
        @Query("current_page") currentPage: Int
    ): Single<SearchCarsResponse>

    @GET("/search/all")
    fun bigSearch(
        @Query("current_page") currentPage: Int,
        @Query("items_on_page") itemsOnPage: Int,
        @QueryMap filters: Map<String, Any>,
        @Query("ascending") ascending: Boolean
    ): Single<SearchCarsResponse>
}
package com.example.ilcarro.data.api

import com.example.ilcarro.data.dto.geolocation.GeolocationResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GeolocationAPI {

    @GET("/maps/api/geocode/json")
    fun fetchPlaceIDByLatLng(
        @Query("latlng") latLng: String,
        @Query("key") key: String
    ): Single<GeolocationResponse>
}
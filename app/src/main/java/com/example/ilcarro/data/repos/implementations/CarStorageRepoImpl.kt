package com.example.ilcarro.data.repos.implementations

import android.util.Log
import com.example.ilcarro.dagger.scopes.ActivityScope
import com.example.ilcarro.data.api.GeolocationAPI
import com.example.ilcarro.data.dto.car.ui.addCarUI.*
import com.example.ilcarro.data.dto.general.Features
import com.example.ilcarro.data.dto.general.PickUpPlace
import com.example.ilcarro.data.dto.geolocation.GeolocationResponse
import com.example.ilcarro.data.repos.interfaces.CarStorageRepo
import com.example.ilcarro.utils.CloudinaryImageUploader
import com.example.ilcarro.utils.Geocoder
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@ActivityScope
class CarStorageRepoImpl @Inject constructor(
    private var newCar: AddCarUI,
    private var imageUploader: CloudinaryImageUploader,
    mRetrofit: Retrofit,
    private val mGeocoder: Geocoder
): CarStorageRepo {

    private lateinit var coordinates: Pair<Double, Double>
    private val mService: GeolocationAPI = mRetrofit
        .newBuilder()
        .baseUrl("https://maps.googleapis.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(
            OkHttpClient()
                .newBuilder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()
        )
        .build()
        .create(GeolocationAPI::class.java)

    override fun addCarUILocationChunk(locationChunk: AddCarUILocationChunk) {
        newCar.country = locationChunk.country
        newCar.city = locationChunk.city
        newCar.street = locationChunk.street
    }

    override fun addCarUICarDetailsFirstChunk(carDetailsFirstChunk: AddCarUICarDetailsFirstChunk) {
        newCar.make = carDetailsFirstChunk.make
        newCar.model = carDetailsFirstChunk.model
        newCar.year = carDetailsFirstChunk.year
        newCar.engine = carDetailsFirstChunk.engine
        newCar.fuel = carDetailsFirstChunk.fuel
        newCar.gear = carDetailsFirstChunk.gear
    }

    override fun addCarUIDetailsSecondChunk(carDetailsSecondChunk: AddCarUICarDetailsSecondChunk) {
        newCar.wheelsDrive = carDetailsSecondChunk.wheelsDrive
        newCar.horsePower = carDetailsSecondChunk.horsePower
        newCar.torque = carDetailsSecondChunk.torque
        newCar.fuelConsumption = carDetailsSecondChunk.fuelConsumption
        newCar.distanceIncluded = carDetailsSecondChunk.distanceIncluded
        newCar.doors = carDetailsSecondChunk.doors
        newCar.seats = carDetailsSecondChunk.seats
        newCar.carClass = carDetailsSecondChunk.carClass
    }

    override fun addCarUIDetailsLastChunk(carDetailsLastChunk: AddCarUICarDetailsLastChunk) {
        newCar.about = carDetailsLastChunk.about
        newCar.features = carDetailsLastChunk.features.map { Features(it) } as MutableList<Features>
        newCar.pricePerDay = carDetailsLastChunk.pricePerDay
        newCar.images = carDetailsLastChunk.images
    }

    override fun uploadImage(image: String) {
        imageUploader.uploadImage(image, newCar)
    }

    override fun fetchImageUploadResult(): Observable<String> {
        return imageUploader.mUploadImageProcess
    }

    override fun clearRepo() {
        newCar = AddCarUI()
    }

    override fun fetchDataFromRepo() = newCar

    override fun fetchPlaceID(): Single<GeolocationResponse> {
        coordinates = mGeocoder.getCoordinates("${newCar.street},${newCar.city},${newCar.country}")
        val latLng = "${coordinates.first},${coordinates.second}"
        return mService.fetchPlaceIDByLatLng(latLng, "AIzaSyDSZiVQ_cAoDgeXNlkjKj5oGwIovd0coXY")
    }

    override fun addPlaceID(placeID: String) {
        newCar.pickUpPlace = PickUpPlace(placeID, coordinates.first, coordinates.second)
    }
}
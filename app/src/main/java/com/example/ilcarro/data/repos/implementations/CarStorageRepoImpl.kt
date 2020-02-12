package com.example.ilcarro.data.repos.implementations

import com.example.ilcarro.dagger.scopes.ActivityScope
import com.example.ilcarro.data.dto.car.ui.addCarUI.*
import com.example.ilcarro.data.dto.general.Features
import com.example.ilcarro.data.repos.interfaces.CarStorageRepo
import com.example.ilcarro.utils.CloudinaryImageUploader
import io.reactivex.Observable
import javax.inject.Inject

@ActivityScope
class CarStorageRepoImpl @Inject constructor(
    private var newCar: AddCarUI,
    private var imageUploader: CloudinaryImageUploader
): CarStorageRepo {

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
}
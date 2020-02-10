package com.example.ilcarro.data.repos.implementations

import android.util.Log
import com.example.ilcarro.dagger.scopes.ActivityScope
import com.example.ilcarro.data.dto.car.ui.addCarUI.*
import com.example.ilcarro.data.dto.general.Features
import com.example.ilcarro.data.repos.interfaces.CarStorageRepo
import javax.inject.Inject

@ActivityScope
class CarStorageRepoImpl @Inject constructor(
    private var newCar: AddCarUI
): CarStorageRepo {

    override fun addCarUILocationChunk(locationChunk: AddCarUILocationChunk) {
        newCar.country = locationChunk.country
        newCar.city = locationChunk.city
        newCar.street = locationChunk.street
        Log.d("tag", newCar.toString())
    }

    override fun addCarUICarDetailsFirstChunk(carDetailsFirstChunk: AddCarUICarDetailsFirstChunk) {
        newCar.make = carDetailsFirstChunk.make
        newCar.model = carDetailsFirstChunk.model
        newCar.year = carDetailsFirstChunk.year
        newCar.engine = carDetailsFirstChunk.engine
        newCar.fuel = carDetailsFirstChunk.fuel
        newCar.gear = carDetailsFirstChunk.gear
        Log.d("tag", newCar.toString())
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
        Log.d("tag", newCar.toString())
    }

    override fun addCarUIDetailsLastChunk(carDetailsLastChunk: AddCarUICarDetailsLastChunk) {
        newCar.about = carDetailsLastChunk.about
        newCar.features = carDetailsLastChunk.features.map { Features(it) } as MutableList<Features>
        newCar.pricePerDay = carDetailsLastChunk.pricePerDay
        newCar.images = carDetailsLastChunk.images
        Log.d("tag", newCar.toString())
    }

    override fun clearRepo() {
        newCar = AddCarUI()
    }

    override fun getAddCarUI() = newCar
}
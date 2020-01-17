package com.example.ilcarro.data.repos.interfaces

import com.example.ilcarro.data.dto.car.ui.CarUI
import io.reactivex.Completable

interface CarProcessingRepo {
    fun addCar(newCar: CarUI): Completable
    fun updateCar(car: CarUI): Completable
    fun deleteCar(car: CarUI): Completable
}
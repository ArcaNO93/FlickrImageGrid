package com.example.ilcarro.business.interfaces

import com.example.ilcarro.data.dto.car.ui.CarUI
import io.reactivex.Completable

interface CarProcessingUseCases {
    fun addCar(newCar: CarUI): Completable
    fun updateCar(car: CarUI): Completable
    fun deleteCar(car: CarUI): Completable
}
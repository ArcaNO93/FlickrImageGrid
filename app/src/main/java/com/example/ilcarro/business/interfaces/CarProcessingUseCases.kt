package com.example.ilcarro.business.interfaces

import com.example.ilcarro.data.dto.car.ui.addCarUI.AddCarUI
import io.reactivex.Completable

interface CarProcessingUseCases {
    fun addCar(newCar: AddCarUI): Completable
    fun updateCar(car: AddCarUI): Completable
    fun deleteCar(car: AddCarUI): Completable
}
package com.example.ilcarro.data.repos.interfaces

import com.example.ilcarro.data.dto.car.ui.addCarUI.AddCarUI
import io.reactivex.Completable

interface CarProcessingRepo {
    fun addCar(newCar: AddCarUI): Completable
    fun updateCar(car: AddCarUI): Completable
    fun deleteCar(car: AddCarUI): Completable
}
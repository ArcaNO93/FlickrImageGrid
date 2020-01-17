package com.example.ilcarro.business.interfaces

import com.example.ilcarro.data.dto.car.Car
import com.example.ilcarro.data.dto.car.ui.CarUI
import com.example.ilcarro.data.dto.general.BookedPeriod
import io.reactivex.Single

interface GettersUseCases {
    fun getCarById(car: CarUI): Single<Car>
    fun getOwnerCars(): Single<List<Car>>
    fun getOwnerCarById(car: CarUI): Single<Car>
    fun getOwnerCarBookedPeriodsById(car: CarUI): Single<List<BookedPeriod>>
}
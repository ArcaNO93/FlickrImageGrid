package com.example.ilcarro.business.interfaces

import com.example.ilcarro.data.dto.car.Car
import com.example.ilcarro.data.dto.car.ui.addCarUI.AddCarUI
import com.example.ilcarro.data.dto.car.ui.ShowCarUI
import com.example.ilcarro.data.dto.general.BookedPeriod
import io.reactivex.Single

interface GettersUseCases {
    fun getCarById(car: AddCarUI): Single<Car>
    fun getOwnerCars(): Single<List<Car>>
    fun getOwnerCarById(car: AddCarUI): Single<Car>
    fun getOwnerCarBookedPeriodsById(car: AddCarUI): Single<List<BookedPeriod>>
    fun getBestBookedCars(): Single<List<ShowCarUI>>
}
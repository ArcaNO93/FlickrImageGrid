package com.example.ilcarro.data.repos.interfaces

import com.example.ilcarro.data.dto.car.Car
import com.example.ilcarro.data.dto.car.ui.CarUI
import com.example.ilcarro.data.dto.car.ui.ShowCarUI
import com.example.ilcarro.data.dto.general.BookedPeriod
import io.reactivex.Single

interface GettersRepo {
    fun getCarById(car: CarUI): Single<Car>
    fun getOwnerCars(): Single<List<Car>>
    fun getOwnerCarById(car: CarUI): Single<Car>
    fun getOwnerCarBookedPeriodsById(car: CarUI): Single<List<BookedPeriod>>
    fun getBestBookedCars(): Single<List<ShowCarUI>>
}
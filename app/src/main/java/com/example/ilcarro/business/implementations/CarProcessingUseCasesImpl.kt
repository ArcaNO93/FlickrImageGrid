package com.example.ilcarro.business.implementations

import com.example.ilcarro.business.interfaces.CarProcessingUseCases
import com.example.ilcarro.dagger.scopes.FragmentScope
import com.example.ilcarro.data.dto.car.ui.CarUI
import com.example.ilcarro.data.repos.implementations.CarProcessingRepoImpl
import javax.inject.Inject

@FragmentScope
class CarProcessingUseCasesImpl @Inject constructor():
    CarProcessingUseCases {

    @Inject
    lateinit var mCarProcessingRepo: CarProcessingRepoImpl

    override fun addCar(newCar: CarUI) =
        mCarProcessingRepo.addCar(newCar)

    override fun updateCar(car: CarUI) =
        mCarProcessingRepo.updateCar(car)

    override fun deleteCar(car: CarUI) =
        mCarProcessingRepo.deleteCar(car)
}
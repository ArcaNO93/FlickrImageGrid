package com.example.ilcarro.business.implementations

import com.example.ilcarro.business.interfaces.GettersUseCases
import com.example.ilcarro.dagger.scopes.FragmentScope
import com.example.ilcarro.data.dto.car.ui.CarUI
import com.example.ilcarro.data.repos.implementations.GettersRepoImpl
import javax.inject.Inject

@FragmentScope
class GettersUseCasesImpl @Inject constructor():
    GettersUseCases {

    @Inject
    lateinit var mGettersRepo: GettersRepoImpl

    override fun getCarById(car: CarUI) =
        mGettersRepo.getCarById(car)

    override fun getOwnerCars() =
        mGettersRepo.getOwnerCars()

    override fun getOwnerCarById(car: CarUI) =
        mGettersRepo.getOwnerCarById(car)

    override fun getOwnerCarBookedPeriodsById(car: CarUI) =
        mGettersRepo.getOwnerCarBookedPeriodsById(car)
}
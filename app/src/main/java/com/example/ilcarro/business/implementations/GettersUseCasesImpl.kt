package com.example.ilcarro.business.implementations

import com.example.ilcarro.business.interfaces.GettersUseCases
import com.example.ilcarro.dagger.scopes.ActivityScope
import com.example.ilcarro.data.dto.car.ui.addCarUI.AddCarUI
import com.example.ilcarro.data.dto.car.ui.ShowCarUI
import com.example.ilcarro.data.repos.implementations.GettersRepoImpl
import io.reactivex.Single
import javax.inject.Inject

@ActivityScope
class GettersUseCasesImpl @Inject constructor(
    private val mGettersRepo: GettersRepoImpl
): GettersUseCases {

    override fun getCarById(car: AddCarUI) =
        mGettersRepo.getCarById(car)

    override fun getOwnerCars() =
        mGettersRepo.getOwnerCars()

    override fun getOwnerCarById(car: AddCarUI) =
        mGettersRepo.getOwnerCarById(car)

    override fun getOwnerCarBookedPeriodsById(car: AddCarUI) =
        mGettersRepo.getOwnerCarBookedPeriodsById(car)

    override fun getBestBookedCars(): Single<List<ShowCarUI>> =
        mGettersRepo.getBestBookedCars()
}
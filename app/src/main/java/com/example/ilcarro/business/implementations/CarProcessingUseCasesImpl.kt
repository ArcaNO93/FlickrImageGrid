package com.example.ilcarro.business.implementations

import com.example.ilcarro.business.interfaces.CarProcessingUseCases
import com.example.ilcarro.dagger.scopes.ActivityScope
import com.example.ilcarro.data.dto.car.ui.addCarUI.AddCarUI
import com.example.ilcarro.data.repos.implementations.CarProcessingRepoImpl
import com.example.ilcarro.utils.Validator
import io.reactivex.Completable
import javax.inject.Inject

@ActivityScope
class CarProcessingUseCasesImpl @Inject constructor(
    private var mCarProcessingRepo: CarProcessingRepoImpl
): CarProcessingUseCases {

    override fun addCar(newCar: AddCarUI) =
        mCarProcessingRepo.addCar(newCar)

    override fun updateCar(car: AddCarUI) =
        mCarProcessingRepo.updateCar(car)

    override fun deleteCar(car: AddCarUI) =
        mCarProcessingRepo.deleteCar(car)

    fun validateIfEmpty(street: String): Completable {
        return if(Validator.validateIfEmpty(street))
            Completable.complete()
        else
            Completable.error(Validator.error)
    }

}
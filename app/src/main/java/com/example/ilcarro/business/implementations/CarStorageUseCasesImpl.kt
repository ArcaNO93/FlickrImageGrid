package com.example.ilcarro.business.implementations

import com.example.ilcarro.business.interfaces.CarStorageUseCases
import com.example.ilcarro.dagger.scopes.ActivityScope
import com.example.ilcarro.data.dto.car.ui.addCarUI.*
import com.example.ilcarro.data.repos.implementations.CarStorageRepoImpl
import javax.inject.Inject

@ActivityScope
class CarStorageUseCasesImpl @Inject constructor(
    private val mCarStorageRepo: CarStorageRepoImpl
): CarStorageUseCases {

    override fun addCarUILocationChunk(locationChunk: AddCarUILocationChunk) =
        mCarStorageRepo.addCarUILocationChunk(locationChunk)

    override fun addCarUICarDetailsFirstChunk(carDetailsFirstChunk: AddCarUICarDetailsFirstChunk) =
        mCarStorageRepo.addCarUICarDetailsFirstChunk(carDetailsFirstChunk)

    override fun addCarUIDetailsSecondChunk(carDetailsSecondChunk: AddCarUICarDetailsSecondChunk) =
        mCarStorageRepo.addCarUIDetailsSecondChunk(carDetailsSecondChunk)

    override fun addCarUIDetailsLastChunk(carDetailsLastChunk: AddCarUICarDetailsLastChunk) =
        mCarStorageRepo.addCarUIDetailsLastChunk(carDetailsLastChunk)

    override fun getAddCarUI() =
        mCarStorageRepo.getAddCarUI()
}
package com.example.ilcarro.business.implementations

import com.example.ilcarro.business.interfaces.CarStorageUseCases
import com.example.ilcarro.dagger.scopes.ActivityScope
import com.example.ilcarro.data.dto.car.ui.addCarUI.*
import com.example.ilcarro.data.repos.implementations.CarStorageRepoImpl
import com.example.ilcarro.utils.Validator
import io.reactivex.Single
import javax.inject.Inject

@ActivityScope
class CarStorageUseCasesImpl @Inject constructor(
    private val mCarStorageRepo: CarStorageRepoImpl
): CarStorageUseCases {

    private var mFeatureList = fetchDataFromRepo().features.map {it.feature} as MutableList<String>

    override fun addCarUILocationChunk(locationChunk: AddCarUILocationChunk) =
        mCarStorageRepo.addCarUILocationChunk(locationChunk)

    override fun addCarUICarDetailsFirstChunk(carDetailsFirstChunk: AddCarUICarDetailsFirstChunk) =
        mCarStorageRepo.addCarUICarDetailsFirstChunk(carDetailsFirstChunk)

    override fun addCarUIDetailsSecondChunk(carDetailsSecondChunk: AddCarUICarDetailsSecondChunk) =
        mCarStorageRepo.addCarUIDetailsSecondChunk(carDetailsSecondChunk)

    override fun addCarUIDetailsLastChunk(carDetailsLastChunk: AddCarUICarDetailsLastChunk) {
        carDetailsLastChunk.features = mFeatureList
        mCarStorageRepo.addCarUIDetailsLastChunk(carDetailsLastChunk)
    }

    override fun fetchDataFromRepo() =
        mCarStorageRepo.fetchDataFromRepo()

    override fun addFeature(feature: String): Single<MutableList<String>> {
        return if (Validator.validateList(feature, mFeatureList)) {
            mFeatureList.add(feature)
            Single.just(mFeatureList)
        } else
            Single.error(Validator.error)
    }

    override fun removeFeature(feature: String): MutableList<String> {
        mFeatureList.remove(feature)
        return mFeatureList
    }

    override fun uploadImage(image: String) {
        mCarStorageRepo.uploadImage(image)
    }

    override fun fetchImageUploadResult() =
        mCarStorageRepo.fetchImageUploadResult()

    override fun fetchPlaceID() =
        mCarStorageRepo.fetchPlaceID()

    override fun addPlaceID(placeID: String) =
        mCarStorageRepo.addPlaceID(placeID)

    override fun clearRepo() =
        mCarStorageRepo.clearRepo()
}
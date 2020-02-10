package com.example.ilcarro.data.repos.implementations

import com.example.ilcarro.dagger.scopes.ActivityScope
import com.example.ilcarro.data.api.CarProcessingAPI
import com.example.ilcarro.data.dto.car.ui.addCarUI.AddCarUI
import com.example.ilcarro.data.repos.interfaces.CarProcessingRepo
import com.example.ilcarro.utils.Mapper
import io.reactivex.Completable
import retrofit2.Retrofit
import javax.inject.Inject

@ActivityScope
class CarProcessingRepoImpl @Inject constructor(
    mServiceRepo: ServiceRepoImpl,
    val mapper: Mapper,
    var mRetrofit: Retrofit
): CarProcessingRepo {

    private val mService by lazy {
        mRetrofit.create(CarProcessingAPI::class.java)
    }

    private val token = mServiceRepo.getToken()

    override fun addCar(newCar: AddCarUI) =
        Completable.fromSingle(mService.addCar(token, mapper.toAddCarRequest(newCar)))

    override fun updateCar(car: AddCarUI) =
        Completable.fromSingle(mService.updateCar(token, car.about, mapper.toAddCarRequest(car)))

    override fun deleteCar(car: AddCarUI) =
        mService.deleteCar(token, car.about)
}
package com.example.ilcarro.data.repos.implementations

import com.example.ilcarro.dagger.scopes.ActivityScope
import com.example.ilcarro.dagger.scopes.FragmentScope
import com.example.ilcarro.data.api.CarProcessingAPI
import com.example.ilcarro.data.dto.car.ui.CarUI
import com.example.ilcarro.data.repos.interfaces.CarProcessingRepo
import com.example.ilcarro.utils.Mapper
import io.reactivex.Completable
import retrofit2.Retrofit
import javax.inject.Inject

@ActivityScope
class CarProcessingRepoImpl @Inject constructor(): CarProcessingRepo {

    @Inject
    lateinit var mServiceRepo: ServiceRepoImpl

    @Inject
    lateinit var mRetrofit: Retrofit
    private val mService by lazy {
        mRetrofit.create(CarProcessingAPI::class.java)
    }

    private val token = mServiceRepo.getToken()

    override fun addCar(newCar: CarUI) =
        Completable.fromSingle(mService.addCar(token, Mapper.toAddCarRequest(newCar)))

    override fun updateCar(car: CarUI) =
        Completable.fromSingle(mService.updateCar(token, car.serialNumber, Mapper.toAddCarRequest(car)))

    override fun deleteCar(car: CarUI) =
        mService.deleteCar(token, car.serialNumber)
}
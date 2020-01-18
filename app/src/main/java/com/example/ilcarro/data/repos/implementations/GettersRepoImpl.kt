package com.example.ilcarro.data.repos.implementations

import com.example.ilcarro.dagger.scopes.FragmentScope
import com.example.ilcarro.data.api.GettersAPI
import com.example.ilcarro.data.dto.car.ui.CarUI
import com.example.ilcarro.data.repos.interfaces.GettersRepo
import retrofit2.Retrofit
import javax.inject.Inject

@FragmentScope
class GettersRepoImpl @Inject constructor(): GettersRepo {

    @Inject
    lateinit var mServiceRepo: ServiceRepoImpl

    @Inject
    lateinit var mRetrofit: Retrofit
    private val service: GettersAPI by lazy {
        mRetrofit.create(GettersAPI::class.java)
    }

    private val token = mServiceRepo.getToken()

    override fun getCarById(car: CarUI) =
        service.getCarById(token, car.serialNumber)

    override fun getOwnerCars() =
        service.getOwnerCars(token)

    override fun getOwnerCarById(car: CarUI) =
        service.getOwnerCarById(token, car.serialNumber)

    override fun getOwnerCarBookedPeriodsById(car: CarUI) =
        service.getOwnerCarBookedPeriodsById(token, car.serialNumber)

    override fun getBestBookedCars() =
        service.getBestBookedCars()
}


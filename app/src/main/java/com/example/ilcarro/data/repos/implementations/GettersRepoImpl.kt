package com.example.ilcarro.data.repos.implementations

import com.example.ilcarro.dagger.scopes.ActivityScope
import com.example.ilcarro.data.api.GettersAPI
import com.example.ilcarro.data.dto.car.ui.CarUI
import com.example.ilcarro.data.repos.interfaces.GettersRepo
import com.example.ilcarro.utils.Mapper
import retrofit2.Retrofit
import javax.inject.Inject

@ActivityScope
class GettersRepoImpl @Inject constructor(): GettersRepo {

    @Inject
    lateinit var mServiceRepo: ServiceRepoImpl

    @Inject
    lateinit var mRetrofit: Retrofit
    private val mService by lazy {
        mRetrofit.create(GettersAPI::class.java)
    }

    private val token by lazy {
        mServiceRepo.getToken()
    }

    override fun getCarById(car: CarUI) =
        mService.getCarById(token, car.serialNumber)

    override fun getOwnerCars() =
        mService.getOwnerCars(token)

    override fun getOwnerCarById(car: CarUI) =
        mService.getOwnerCarById(token, car.serialNumber)

    override fun getOwnerCarBookedPeriodsById(car: CarUI) =
        mService.getOwnerCarBookedPeriodsById(token, car.serialNumber)

    override fun getBestBookedCars() =
        mService.getBestBookedCars().map { it ->
            it.map { Mapper.toShowCarUI(it) }
        }
}


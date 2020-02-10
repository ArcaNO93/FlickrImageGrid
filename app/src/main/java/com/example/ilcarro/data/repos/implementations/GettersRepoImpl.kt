package com.example.ilcarro.data.repos.implementations

import com.example.ilcarro.dagger.scopes.ActivityScope
import com.example.ilcarro.data.api.GettersAPI
import com.example.ilcarro.data.dto.car.ui.addCarUI.AddCarUI
import com.example.ilcarro.data.repos.interfaces.GettersRepo
import com.example.ilcarro.utils.Mapper
import retrofit2.Retrofit
import javax.inject.Inject

@ActivityScope
class GettersRepoImpl @Inject constructor(
    private val mServiceRepo: ServiceRepoImpl,
    val mRetrofit: Retrofit
): GettersRepo {

    private val mService by lazy {
        mRetrofit.create(GettersAPI::class.java)
    }

    private val token by lazy {
        mServiceRepo.getToken()
    }

    override fun getCarById(car: AddCarUI) =
        mService.getCarById(token, car.about)

    override fun getOwnerCars()=
        mService.getOwnerCars(token)

    override fun getOwnerCarById(car: AddCarUI) =
        mService.getOwnerCarById(token, car.about)

    override fun getOwnerCarBookedPeriodsById(car: AddCarUI) =
        mService.getOwnerCarBookedPeriodsById(token, car.about)

    override fun getBestBookedCars() =
        mService.getBestBookedCars().map { it ->
            it.map { Mapper.toShowCarUI(it) }
        }
}


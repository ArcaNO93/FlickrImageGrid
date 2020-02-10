package com.example.ilcarro.data.repos.implementations

import com.example.ilcarro.dagger.scopes.ActivityScope
import com.example.ilcarro.data.api.UtilsAPI
import com.example.ilcarro.data.dto.car.ui.addCarUI.AddCarUI
import com.example.ilcarro.data.dto.general.ReservationUI
import com.example.ilcarro.data.repos.interfaces.UtilsRepo
import com.example.ilcarro.utils.Mapper
import retrofit2.Retrofit
import javax.inject.Inject

@ActivityScope
class UtilsRepoImpl @Inject constructor(
    mServiceRepo: ServiceRepoImpl,
    var mRetrofit: Retrofit
): UtilsRepo {

    private val mService by lazy {
        mRetrofit.create(UtilsAPI::class.java)
    }

    private val token = mServiceRepo.getToken()

    override fun makeReservation(car: AddCarUI, reservation: ReservationUI) =
        mService.makeReservation(token, car.about, Mapper.toReservationRequest(reservation))

    override fun latestComments() =
        mService.latestComments()

    override fun postComment(car: AddCarUI, comment: String) =
        mService.postComment(token, car.about, comment)
}
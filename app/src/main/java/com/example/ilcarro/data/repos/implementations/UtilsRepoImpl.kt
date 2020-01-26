package com.example.ilcarro.data.repos.implementations

import com.example.ilcarro.dagger.scopes.FragmentScope
import com.example.ilcarro.data.api.UtilsAPI
import com.example.ilcarro.data.dto.car.ui.CarUI
import com.example.ilcarro.data.dto.general.ReservationUI
import com.example.ilcarro.data.repos.interfaces.UtilsRepo
import com.example.ilcarro.utils.Mapper
import retrofit2.Retrofit
import javax.inject.Inject

@FragmentScope
class UtilsRepoImpl @Inject constructor(): UtilsRepo {

    @Inject
    lateinit var mServiceRepo: ServiceRepoImpl

    @Inject
    lateinit var mRetrofit: Retrofit
    private val mService by lazy {
        mRetrofit.create(UtilsAPI::class.java)
    }

    private val token = mServiceRepo.getToken()

    override fun makeReservation(car: CarUI, reservation: ReservationUI) =
        mService.makeReservation(token, car.serialNumber, Mapper.toReservationRequest(reservation))

    override fun latestComments() =
        mService.latestComments()

    override fun postComment(car: CarUI, comment: String) =
        mService.postComment(token, car.serialNumber, comment)
}
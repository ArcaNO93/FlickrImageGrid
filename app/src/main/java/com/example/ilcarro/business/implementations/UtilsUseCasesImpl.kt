package com.example.ilcarro.business.implementations

import com.example.ilcarro.business.interfaces.UtilsUseCases
import com.example.ilcarro.dagger.scopes.FragmentScope
import com.example.ilcarro.data.dto.car.ui.CarUI
import com.example.ilcarro.data.dto.general.ReservationUI
import com.example.ilcarro.data.repos.implementations.UtilsRepoImpl
import javax.inject.Inject

@FragmentScope
class UtilsUseCasesImpl @Inject constructor(): UtilsUseCases {

    @Inject
    lateinit var mUtilsRepo: UtilsRepoImpl
    override fun makeReservation(car: CarUI, reservation: ReservationUI) =
        mUtilsRepo.makeReservation(car, reservation)

    override fun latestComments() =
        mUtilsRepo.latestComments()

    override fun postComment(car: CarUI, comment: String) =
        mUtilsRepo.postComment(car, comment)
}
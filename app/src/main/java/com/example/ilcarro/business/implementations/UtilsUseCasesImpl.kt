package com.example.ilcarro.business.implementations

import com.example.ilcarro.business.interfaces.UtilsUseCases
import com.example.ilcarro.dagger.scopes.ActivityScope
import com.example.ilcarro.data.dto.car.ui.addCarUI.AddCarUI
import com.example.ilcarro.data.dto.general.ReservationUI
import com.example.ilcarro.data.repos.implementations.UtilsRepoImpl
import javax.inject.Inject

@ActivityScope
class UtilsUseCasesImpl @Inject constructor(
    private val mUtilsRepo: UtilsRepoImpl
): UtilsUseCases {

    override fun makeReservation(car: AddCarUI, reservation: ReservationUI) =
        mUtilsRepo.makeReservation(car, reservation)

    override fun latestComments() =
        mUtilsRepo.latestComments()

    override fun postComment(car: AddCarUI, comment: String) =
        mUtilsRepo.postComment(car, comment)
}
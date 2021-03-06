package com.example.ilcarro.business.interfaces

import com.example.ilcarro.data.dto.car.ui.addCarUI.AddCarUI
import com.example.ilcarro.data.dto.general.Comments
import com.example.ilcarro.data.dto.general.ReservationResponse
import com.example.ilcarro.data.dto.general.ReservationUI
import io.reactivex.Completable
import io.reactivex.Single

interface UtilsUseCases {
    fun makeReservation(car: AddCarUI, reservation: ReservationUI): Single<ReservationResponse>
    fun latestComments(): Single<List<Comments>>
    fun postComment(car: AddCarUI, comment: String): Completable
}
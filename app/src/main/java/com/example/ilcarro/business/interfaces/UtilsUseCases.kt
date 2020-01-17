package com.example.ilcarro.business.interfaces

import com.example.ilcarro.data.dto.car.ui.CarUI
import com.example.ilcarro.data.dto.general.Comments
import com.example.ilcarro.data.dto.general.ReservationResponse
import com.example.ilcarro.data.dto.general.ReservationUI
import io.reactivex.Completable
import io.reactivex.Single

interface UtilsUseCases {
    fun makeReservation(car: CarUI, reservation: ReservationUI): Single<ReservationResponse>
    fun latestComments(): Single<List<Comments>>
    fun postComment(car: CarUI, comment: String): Completable
}
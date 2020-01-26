package com.example.ilcarro.utils

import com.example.ilcarro.data.dto.car.AddCarRequest
import com.example.ilcarro.data.dto.car.ui.*
import com.example.ilcarro.data.dto.general.PersonWhoBooked
import com.example.ilcarro.data.dto.general.ReservationRequest
import com.example.ilcarro.data.dto.general.ReservationUI
import com.example.ilcarro.data.dto.user.RegisterUserRequest
import com.example.ilcarro.data.dto.user.UpdateUserRequest
import com.example.ilcarro.data.dto.user.ui.RegisterUserUI
import com.example.ilcarro.data.dto.user.ui.UpdateUserUI

object Mapper {
    fun toRegisterUserRequest(user: RegisterUserUI) =
        RegisterUserRequest(
            firstName = user.firstName,
            secondName = user.secondName
        )

    fun toUpdateUserRequest(user: UpdateUserUI) =
        UpdateUserRequest(
            firstName = user.firstName,
            secondName = user.secondName,
            photo = user.photo
        )

    fun toAddCarRequest(car: CarUI) =
        AddCarRequest(
            serialNumber = car.serialNumber,
            make = car.make,
            model = car.model,
            year = car.year,
            engine = car.engine,
            fuel = car.fuel,
            gear = car.gear,
            wheelsDrive = car.wheelsDrive,
            horsePower = car.horsePower,
            torque = car.torque,
            seats = car.seats,
            fuelConsumption = car.fuelConsumption,
            features = car.features,
            carClass = car.carClass,
            pricePerDay = car.pricePerDay,
            distanceIncluded = car.distanceIncluded,
            about = car.about,
            pickUpPlace = car.pickUpPlace,
            images = car.images
        )

    fun toReservationRequest(reservation: ReservationUI) =
        ReservationRequest(
            startDateTime = reservation.start_date_time,
            endDateTime = reservation.end_date_time,
            personWhoBooked = PersonWhoBooked(
                email = reservation.email,
                firstName = reservation.firstName,
                second_name = reservation.secondName,
                phone = reservation.phone
            )
        )

    fun mapCarSearchUI(carSearch: CarSearchUI) =
        mapOf(
            "city" to carSearch.city,
            "start_date" to carSearch.startDate,
            "end_date" to carSearch.endDate,
            "min_amount" to carSearch.minAmount,
            "max_amount" to carSearch.maxAmount
        )

    fun mapCarSearchByCoordinatesUI(carSearch: CarSearchByCoordinatesUI) =
        mapOf(
            "latitude" to carSearch.latitude,
            "longitude" to carSearch.longitude,
            "radius" to carSearch.radius
        )

    fun mapCarSearchByFiltersUI(carSearch: CarSearchByFiltersUI) =
        mapOf(
            "make" to carSearch.make,
            "model" to carSearch.model,
            "year" to carSearch.year,
            "engine" to carSearch.engine,
            "fuel" to carSearch.fuel,
            "gear" to carSearch.gear,
            "wheels_drive" to carSearch.wheelsDrive
        )

    fun mapCarBigSearch(carSearch: CarBigSearchUI) =
        mapOf(
            "make" to carSearch.make,
            "model" to carSearch.model,
            "wheels_drive" to carSearch.wheelsDrive,
            "fuel" to carSearch.fuel,
            "gear" to carSearch.gear,
            "engine" to carSearch.engine,
            "latitude" to carSearch.latitude,
            "longitude" to carSearch.longitude,
            "radius" to carSearch.radius,
            "startDate" to carSearch.startDate,
            "endDate" to carSearch.endDate,
            "minAmount" to carSearch.minAmount,
            "maxAmount" to carSearch.maxAmount
        )
}

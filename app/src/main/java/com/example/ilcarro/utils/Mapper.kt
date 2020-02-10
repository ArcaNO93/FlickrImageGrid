package com.example.ilcarro.utils

import com.example.ilcarro.dagger.scopes.GlobalScope
import com.example.ilcarro.data.dto.car.AddCarRequest
import com.example.ilcarro.data.dto.car.Car
import com.example.ilcarro.data.dto.car.ui.*
import com.example.ilcarro.data.dto.car.ui.addCarUI.AddCarUI
import com.example.ilcarro.data.dto.general.PersonWhoBooked
import com.example.ilcarro.data.dto.general.PickUpPlace
import com.example.ilcarro.data.dto.general.ReservationRequest
import com.example.ilcarro.data.dto.general.ReservationUI
import com.example.ilcarro.data.dto.user.RegisterUserRequest
import com.example.ilcarro.data.dto.user.UpdateUserRequest
import com.example.ilcarro.data.dto.user.User
import com.example.ilcarro.data.dto.user.ui.RegisterUserUI
import com.example.ilcarro.data.dto.user.ui.UpdateUserUI
import com.example.ilcarro.data.dto.user.ui.UserUI
import javax.inject.Inject

@GlobalScope
class Mapper @Inject constructor(
    private val geocoder: Geocoder
) {

    fun toAddCarRequest(car: AddCarUI): AddCarRequest {
        val coordinates = geocoder.getCoordinates("${car.street},${car.city},${car.country}")
        return AddCarRequest(
            make = car.make,
            model = car.model,
            year = car.year,
            engine = car.engine,
            fuel = car.fuel,
            gear = car.gear,
            wheelsDrive = car.wheelsDrive,
            horsePower = car.horsePower.toInt(),
            torque = car.torque.toDouble(),
            doors = car.doors.toInt(),
            seats = car.seats.toInt(),
            fuelConsumption = car.fuelConsumption.toDouble(),
            features = car.features,
            carClass = car.carClass,
            pricePerDay = car.pricePerDay.toDouble(),
            distanceIncluded = car.distanceIncluded.toDouble(),
            about = car.about,
            pickUpPlace = PickUpPlace("", coordinates.first, coordinates.second),
            images = car.images
        )
    }

    companion object {
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

        fun toShowCarUI(car: Car) = ShowCarUI(
            serialNumber = car.serialNumber,
            image = car.images[0],
            carFullName = "${car.make} ${car.model} ${car.year}",
            pricePerDay = car.pricePerDay.value,
            seats = car.seats,
            doors = car.doors,
            gear = car.gear
        )

        fun toUserUI(user: User) = UserUI(
            fullName = user.firstName + " " + user.secondName,
            registrationDate = user.registrationDate,
            photo = user.photo,
            comments = user.comments,
            ownedCars = user.ownedCars.map {
                toShowCarUI(it)
            },
            bookedCars = user.bookedCars,
            history = user.history
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
}

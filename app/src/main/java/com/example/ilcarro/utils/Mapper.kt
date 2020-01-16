package com.example.ilcarro.utils

import com.example.ilcarro.data.dto.car.AddCarRequest
import com.example.ilcarro.data.dto.car.ui.CarUI
import com.example.ilcarro.data.dto.user.RegisterUserRequest
import com.example.ilcarro.data.dto.user.UpdateUserRequest
import com.example.ilcarro.data.dto.user.ui.RegisterUserUI
import com.example.ilcarro.data.dto.user.ui.UpdateUserUI

class Mapper {
    companion object {
        fun toRegisterUserRequest(user: RegisterUserUI): RegisterUserRequest =
            RegisterUserRequest(
                firstName = user.firstName,
                secondName = user.secondName
            )

        fun toUpdateUserRequest(user: UpdateUserUI): UpdateUserRequest =
            UpdateUserRequest(
                firstName = user.firstName,
                secondName = user.secondName,
                photo = user.photo
            )

        fun toAddCarRequest(car: CarUI): AddCarRequest =
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
    }
}
package com.example.ilcarro.business.interfaces

import com.example.ilcarro.data.dto.car.ui.addCarUI.*

interface CarStorageUseCases {
    fun addCarUILocationChunk(locationChunk: AddCarUILocationChunk)
    fun addCarUICarDetailsFirstChunk(carDetailsFirstChunk: AddCarUICarDetailsFirstChunk)
    fun addCarUIDetailsSecondChunk(carDetailsSecondChunk: AddCarUICarDetailsSecondChunk)
    fun addCarUIDetailsLastChunk(carDetailsLastChunk: AddCarUICarDetailsLastChunk)
    fun getAddCarUI(): AddCarUI
}
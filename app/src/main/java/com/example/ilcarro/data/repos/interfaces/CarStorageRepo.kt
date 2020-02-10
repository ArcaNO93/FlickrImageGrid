package com.example.ilcarro.data.repos.interfaces

import com.example.ilcarro.data.dto.car.ui.addCarUI.*

interface CarStorageRepo {
    fun addCarUILocationChunk(locationChunk: AddCarUILocationChunk)
    fun addCarUICarDetailsFirstChunk(carDetailsFirstChunk: AddCarUICarDetailsFirstChunk)
    fun addCarUIDetailsSecondChunk(carDetailsSecondChunk: AddCarUICarDetailsSecondChunk)
    fun addCarUIDetailsLastChunk(carDetailsLastChunk: AddCarUICarDetailsLastChunk)
    fun getAddCarUI(): AddCarUI
}
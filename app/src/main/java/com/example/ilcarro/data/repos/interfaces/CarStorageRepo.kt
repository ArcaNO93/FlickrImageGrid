package com.example.ilcarro.data.repos.interfaces

import com.example.ilcarro.data.dto.car.ui.addCarUI.*
import io.reactivex.Observable

interface CarStorageRepo {
    fun addCarUILocationChunk(locationChunk: AddCarUILocationChunk)
    fun addCarUICarDetailsFirstChunk(carDetailsFirstChunk: AddCarUICarDetailsFirstChunk)
    fun addCarUIDetailsSecondChunk(carDetailsSecondChunk: AddCarUICarDetailsSecondChunk)
    fun addCarUIDetailsLastChunk(carDetailsLastChunk: AddCarUICarDetailsLastChunk)
    fun fetchDataFromRepo(): AddCarUI
    fun uploadImage(image:String)
    fun fetchImageUploadResult(): Observable<String>
    fun clearRepo()
}
package com.example.ilcarro.data.repos.interfaces

import com.example.ilcarro.data.dto.car.SearchCarsResponce
import io.reactivex.Single

interface SearchRepo {
    fun carsSearch(): Single<SearchCarsResponce>
    fun carsSearchByCoordinates(): Single<SearchCarsResponce>
    fun carsSearchByFilters(): Single<SearchCarsResponce>
    fun bigSearch(): Single<SearchCarsResponce>
}
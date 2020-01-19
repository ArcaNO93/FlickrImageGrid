package com.example.ilcarro.business.interfaces

import com.example.ilcarro.data.dto.car.ui.CarBigSearchUI
import com.example.ilcarro.data.dto.car.ui.CarSearchByCoordinatesUI
import com.example.ilcarro.data.dto.car.ui.CarSearchByFiltersUI
import com.example.ilcarro.data.dto.car.ui.CarSearchUI
import com.example.ilcarro.data.paging.factories.CarBigSearchFactory
import com.example.ilcarro.data.paging.factories.CarSearchByCoordinatesFactory
import com.example.ilcarro.data.paging.factories.CarSearchByFilersFactory
import com.example.ilcarro.data.paging.factories.CarSearchFactory

interface CarSearchUseCases {
    fun carSearch(carSearch: CarSearchUI): CarSearchFactory
    fun carSearchByCoordinates(carSearch: CarSearchByCoordinatesUI): CarSearchByCoordinatesFactory
    fun carSearchByFilters(carSearch: CarSearchByFiltersUI): CarSearchByFilersFactory
    fun carBigSearch(carSearch: CarBigSearchUI): CarBigSearchFactory
}
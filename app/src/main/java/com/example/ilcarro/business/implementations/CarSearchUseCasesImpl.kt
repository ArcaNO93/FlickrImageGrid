package com.example.ilcarro.business.implementations

import com.example.ilcarro.business.interfaces.CarSearchUseCases
import com.example.ilcarro.dagger.scopes.ActivityScope
import com.example.ilcarro.data.dto.car.ui.CarBigSearchUI
import com.example.ilcarro.data.dto.car.ui.CarSearchByCoordinatesUI
import com.example.ilcarro.data.dto.car.ui.CarSearchByFiltersUI
import com.example.ilcarro.data.dto.car.ui.CarSearchUI
import com.example.ilcarro.data.paging.factories.CarBigSearchFactory
import com.example.ilcarro.data.paging.factories.CarSearchByCoordinatesFactory
import com.example.ilcarro.data.paging.factories.CarSearchByFilersFactory
import com.example.ilcarro.data.paging.factories.CarSearchFactory
import javax.inject.Inject

@ActivityScope
class CarSearchUseCasesImpl @Inject constructor(
    private val mCarSearchFactory: CarSearchFactory,
    private val mCarSearchByFilersFactory: CarSearchByFilersFactory,
    private val mCarSearchByCoordinatesFactory: CarSearchByCoordinatesFactory,
    private val mCarBigSearchFactory: CarBigSearchFactory
) : CarSearchUseCases {

    override fun carSearch(carSearch: CarSearchUI) =
        mCarSearchFactory

    override fun carSearchByFilters(carSearch: CarSearchByFiltersUI) =
        mCarSearchByFilersFactory

    override fun carSearchByCoordinates(carSearch: CarSearchByCoordinatesUI) =
        mCarSearchByCoordinatesFactory

    override fun carBigSearch(carSearch: CarBigSearchUI) =
        mCarBigSearchFactory
}
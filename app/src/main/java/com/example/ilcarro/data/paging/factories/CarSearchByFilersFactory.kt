package com.example.ilcarro.data.paging.factories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.ilcarro.dagger.scopes.FragmentScope
import com.example.ilcarro.data.dto.car.Car
import com.example.ilcarro.data.dto.car.ui.CarSearchByFiltersUI
import com.example.ilcarro.data.paging.dataSources.CarSearchByCoordinatesDataSource
import com.example.ilcarro.data.paging.dataSources.CarSearchByFiltersDataSource
import javax.inject.Inject

@FragmentScope
class CarSearchByFilersFactory @Inject constructor(val mCarSearch: CarSearchByFiltersUI): DataSource.Factory<Int, Car>() {

    private val dataSourceLive = MutableLiveData<CarSearchByFiltersDataSource>()

    override fun create(): DataSource<Int, Car> {
        val dataSource = CarSearchByFiltersDataSource(mCarSearch)
        dataSourceLive.postValue(dataSource)
        return dataSource
    }

    fun getDataSourceLive() = dataSourceLive as LiveData<CarSearchByFiltersDataSource>
}
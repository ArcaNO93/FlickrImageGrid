package com.example.ilcarro.data.paging.factories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.ilcarro.dagger.scopes.FragmentScope
import com.example.ilcarro.data.dto.car.Car
import com.example.ilcarro.data.dto.car.ui.CarSearchByCoordinatesUI
import com.example.ilcarro.data.paging.dataSources.CarSearchByCoordinatesDataSource
import javax.inject.Inject

@FragmentScope
class CarSearchByCoordinatesFactory @Inject constructor(val mCarSearch: CarSearchByCoordinatesUI): DataSource.Factory<Int, Car>() {

    private val dataSourceLive = MutableLiveData<CarSearchByCoordinatesDataSource>()

    override fun create(): DataSource<Int, Car> {
        val dataSource = CarSearchByCoordinatesDataSource(mCarSearch)
        dataSourceLive.postValue(dataSource)
        return dataSource
    }

    fun getDataSourceLive() = dataSourceLive as LiveData<CarSearchByCoordinatesDataSource>
}
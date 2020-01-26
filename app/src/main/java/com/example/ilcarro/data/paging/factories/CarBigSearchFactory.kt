package com.example.ilcarro.data.paging.factories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.ilcarro.dagger.scopes.FragmentScope
import com.example.ilcarro.data.dto.car.Car
import com.example.ilcarro.data.dto.car.ui.CarBigSearchUI
import com.example.ilcarro.data.paging.dataSources.CarBigSearchDataSource
import javax.inject.Inject

@FragmentScope
class CarBigSearchFactory @Inject constructor(val mCarSearch: CarBigSearchUI): DataSource.Factory<Int, Car>() {

    private val dataSourceLive = MutableLiveData<CarBigSearchDataSource>()

    override fun create(): DataSource<Int, Car> {
        val dataSource = CarBigSearchDataSource(mCarSearch)
        dataSourceLive.postValue(dataSource)
        return dataSource
    }

    fun getDataSourceLive() = dataSourceLive as LiveData<CarBigSearchDataSource>
}
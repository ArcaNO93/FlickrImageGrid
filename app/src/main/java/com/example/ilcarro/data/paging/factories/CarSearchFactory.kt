package com.example.ilcarro.data.paging.factories

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.ilcarro.dagger.scopes.FragmentScope
import com.example.ilcarro.data.dto.car.Car
import com.example.ilcarro.data.dto.car.ui.CarSearchUI
import com.example.ilcarro.data.paging.dataSources.CarSearchDataSource
import javax.inject.Inject

@FragmentScope
class CarSearchFactory @Inject constructor(val mCarSearch: CarSearchUI): DataSource.Factory<Int, Car>() {

    private val dataSourceLive = MutableLiveData<CarSearchDataSource>()

    override fun create(): DataSource<Int, Car> {
        val dataSource = CarSearchDataSource(mCarSearch)
        dataSourceLive.postValue(dataSource)
        return dataSource
    }
}
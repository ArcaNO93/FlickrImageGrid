package com.example.ilcarro.data.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.ilcarro.data.dto.car.Car
import javax.inject.Inject

class DataSourceFactory<T: Any> @Inject constructor(val mCarSearch: T): DataSource.Factory<Int, Car>() {

    private val dataSourceLive = MutableLiveData<CarSearchDataSource<T>>()

    override fun create(): DataSource<Int, Car> {
        val dataSource = CarSearchDataSource(mCarSearch)
        dataSourceLive.postValue(dataSource)
        return dataSource
    }

}
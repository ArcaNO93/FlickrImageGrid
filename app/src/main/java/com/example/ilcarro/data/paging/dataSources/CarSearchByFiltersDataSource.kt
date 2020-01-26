package com.example.ilcarro.data.paging.dataSources

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.ilcarro.data.api.SearchUI
import com.example.ilcarro.data.dto.car.Car
import com.example.ilcarro.data.dto.car.ui.CarSearchByFiltersUI
import com.example.ilcarro.utils.Mapper
import com.example.ilcarro.utils.NetworkState
import com.example.ilcarro.utils.ResponseHandler
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit
import javax.inject.Inject

class CarSearchByFiltersDataSource @Inject constructor(val mCarSearch: CarSearchByFiltersUI): PageKeyedDataSource<Int, Car>() {

    private val ITEMS_ON_PAGE = 10
    private val FIRST_PAGE = 1

    @Inject
    lateinit var mRetrofit: Retrofit
    private val service: SearchUI by lazy {
        mRetrofit.create(SearchUI::class.java)
    }

    fun getNetworkState(): LiveData<NetworkState> = networkState

    private val networkState = MutableLiveData<NetworkState>()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Car>) {
        networkState.postValue(NetworkState.LOADING)
        CompositeDisposable().add(
            service.carsSearchByFilters(
                Mapper.mapCarSearchByFiltersUI(mCarSearch),
                ITEMS_ON_PAGE,
                FIRST_PAGE
            ).subscribe({
                callback.onResult(it.cars, null, FIRST_PAGE + 1)
                networkState.postValue(NetworkState.LOADED)
            }, {
                handleError(it)
            })
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Car>) {
        networkState.postValue(NetworkState.LOADING)
        CompositeDisposable().add(
            service.carsSearchByFilters(
                Mapper.mapCarSearchByFiltersUI(mCarSearch),
                ITEMS_ON_PAGE,
                params.key
            ).subscribe({
                callback.onResult(it.cars, params.key + 1)
                networkState.postValue(NetworkState.LOADED)
            }, {
                handleError(it)
            })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Car>) {
        //TODO: will be realised if needed
    }

    private fun handleError(exception: Throwable) =
        networkState.postValue(NetworkState.fail(ResponseHandler.parseException(exception)))
}
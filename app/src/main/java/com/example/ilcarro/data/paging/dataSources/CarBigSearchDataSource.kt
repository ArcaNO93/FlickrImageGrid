package com.example.ilcarro.data.paging.dataSources

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.ilcarro.data.api.SearchUI
import com.example.ilcarro.data.dto.car.Car
import com.example.ilcarro.data.dto.car.ui.CarBigSearchUI
import com.example.ilcarro.utils.Mapper
import com.example.ilcarro.utils.State
import com.example.ilcarro.utils.ResponseHandler
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit
import javax.inject.Inject

class CarBigSearchDataSource @Inject constructor(val mCarSearch: CarBigSearchUI): PageKeyedDataSource<Int, Car>() {

    private val ITEMS_ON_PAGE = 10
    private val FIRST_PAGE = 1

    @Inject
    lateinit var mRetrofit: Retrofit
    private val service: SearchUI by lazy {
        mRetrofit.create(SearchUI::class.java)
    }

    private val networkState = MutableLiveData<State>()

    fun getNetworkState() : LiveData<State> = networkState

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Car>) {
        networkState.postValue(State.LOADING)
        CompositeDisposable().add(
            service.bigSearch(
                FIRST_PAGE,
                ITEMS_ON_PAGE,
                Mapper.mapCarBigSearch(mCarSearch),
                true
            ).subscribe({
                callback.onResult(it.cars, null, FIRST_PAGE + 1)
                networkState.postValue(State.LOADED)
            }, {
                handleError(it)
            })
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Car>) {
        networkState.postValue(State.LOADING)
        CompositeDisposable().add(
            service.bigSearch(
                 FIRST_PAGE,
                ITEMS_ON_PAGE,
                Mapper.mapCarBigSearch(mCarSearch),
                true
            ).subscribe({
                callback.onResult(it.cars, params.key + 1)
                networkState.postValue(State.LOADED)
            }, {
                handleError(it)
            })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Car>) {
        //TODO: will be realised if needed
    }

    private fun handleError(exception: Throwable) =
        networkState.postValue(State.fail(ResponseHandler.parseException(exception)))
}
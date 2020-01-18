package com.example.ilcarro.data.paging

import androidx.paging.PageKeyedDataSource
import com.example.ilcarro.data.api.SearchUI
import com.example.ilcarro.data.dto.car.Car
import com.example.ilcarro.utils.Mapper
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

class CarSearchDataSource<T: Any> @Inject constructor(val mCarSearch: T): PageKeyedDataSource<Int, Car>() {

    private val ITEMS_ON_PAGE = 10
    private val FIRST_PAGE = 1

    @Inject
    lateinit var mRetrofit: Retrofit
    private val service: SearchUI by lazy {
        mRetrofit.create(SearchUI::class.java)
    }

    //TODO networkState

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Car>) {
        CompositeDisposable().add(
            service.carsSearch(
                Mapper.map(mCarSearch, getCarSearchClass()),
                true,
                ITEMS_ON_PAGE,
                FIRST_PAGE
            ).subscribe({
                callback.onResult(it.cars, null, FIRST_PAGE + 1)
            }, {
                //TODO error handle
            })
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Car>) {
        CompositeDisposable().add(
            service.carsSearch(
                Mapper.map(mCarSearch, getCarSearchClass()),
                true,
                ITEMS_ON_PAGE,
                params.key
            ).subscribe({
                callback.onResult(it.cars, params.key + 1)
            }, {
                //TODO handle error
            }
            )
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Car>) {
        //TODO: will be realised if needed
    }

    @Suppress("UNCHECKED_CAST")
    private fun getCarSearchClass(): Class<T> {
        val type = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
        return type as Class<T>
    }
}
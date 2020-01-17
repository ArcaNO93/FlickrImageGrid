package com.example.ilcarro.data.repos.implementations

import com.example.ilcarro.dagger.scopes.FragmentScope
import com.example.ilcarro.data.dto.car.SearchCarsResponce
import com.example.ilcarro.data.repos.interfaces.SearchRepo
import io.reactivex.Single
import javax.inject.Inject

@FragmentScope
class SearchRepoImpl @Inject constructor(): SearchRepo {
    override fun carsSearch(): Single<SearchCarsResponce> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun carsSearchByCoordinates(): Single<SearchCarsResponce> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun carsSearchByFilters(): Single<SearchCarsResponce> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun bigSearch(): Single<SearchCarsResponce> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
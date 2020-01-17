package com.example.ilcarro.business.implementations

import com.example.ilcarro.business.interfaces.SearchUseCases
import com.example.ilcarro.dagger.scopes.FragmentScope
import com.example.ilcarro.data.repos.implementations.SearchRepoImpl
import javax.inject.Inject

@FragmentScope
class SearchUseCasesImpl @Inject constructor(): SearchUseCases {

    @Inject
    lateinit var mSearchRepo: SearchRepoImpl

    override fun carsSearch() =
        mSearchRepo.carsSearch()

    override fun carsSearchByCoordinates() =
        mSearchRepo.carsSearchByCoordinates()

    override fun carsSearchByFilters() =
        mSearchRepo.carsSearchByFilters()

    override fun bigSearch() =
        mSearchRepo.bigSearch()
}
package com.example.ilcarro.utils

import java.util.*

object SpinnerLists {
    fun getCountriesList(): MutableList<String> {
        val locales = Locale.getISOCountries()
        val countries = mutableListOf<String>()
        for(locale in locales)
            countries.add(Locale("", locale).displayCountry)
        countries.sort()
        return countries
    }
    val wheelsDriveList = listOf("FWD", "RWD", "4WD")
    val fuelList = listOf("Petrol", "Diesel Fuel", "Gas")
    val transmissionList = listOf("Automatic", "Manual")
    val carClassesList = listOf("Convertible", "Coupe", "Crossover", "Hatchback", "Minivan", "Van", "Sedan", "SUV")
}
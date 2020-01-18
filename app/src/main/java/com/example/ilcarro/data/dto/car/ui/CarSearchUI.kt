package com.example.ilcarro.data.dto.car.ui

import com.example.ilcarro.dagger.scopes.FragmentScope
import javax.inject.Inject

@FragmentScope
data class CarSearchUI @Inject constructor(
    var city: String,
    var startDate: String,
    var endDate: String,
    var minAmount: Float,
    var maxAmount: Float
)

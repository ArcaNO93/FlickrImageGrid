package com.example.ilcarro.utils

import android.location.Address
import android.location.Geocoder
import android.util.Log
import com.example.ilcarro.dagger.scopes.GlobalScope
import okio.IOException
import javax.inject.Inject

@GlobalScope
class Geocoder @Inject constructor(var mGeocoder: Geocoder) {

    fun getCoordinates(address: String): Pair<Double, Double> {
        try {
            val addresses: List<Address> = mGeocoder.getFromLocationName(address, 1)
            if (addresses.isNotEmpty()) {
                return Pair(addresses[0].latitude, addresses[0].longitude)
            }
            return Pair(0.0, 0.0)
        }
        catch (e: IOException) {
            return Pair(0.0, 0.0)
        }
    }
}
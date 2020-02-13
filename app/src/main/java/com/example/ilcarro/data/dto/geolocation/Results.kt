package com.example.ilcarro.data.dto.geolocation

import com.google.gson.annotations.SerializedName

data class Results (

    @SerializedName("address_components") val address_components : List<AddressComponents>,
    @SerializedName("formatted_address") val formatted_address : String,
    @SerializedName("geometry") val geometry : Geometry,
    @SerializedName("place_id") val place_id : String,
    @SerializedName("plus_code") val plus_code : PlusCode,
    @SerializedName("types") val types : List<String>
)
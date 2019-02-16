package com.android.hellogold.test.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SpotPriceResponse(
    val `data`: SpotPriceData, val result: String
) : Parcelable


@Parcelize
data class SpotPriceData(
    val buy: Double,
    val sell: Double,
    @SerializedName("spot_price") val spotPrice: Double,
    val timestamp: String
) : Parcelable
package com.android.hellogold.test.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

const val TIMESTAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"

@Parcelize
data class SpotPriceResponse(
    @SerializedName("data") val goldPrice: GoldPrice?, val result: String, val message: String?, val code: String?
) : Parcelable {
    fun isSuccess() = result.equals("ok", ignoreCase = true)
}


@Parcelize
data class GoldPrice(
    val buy: Double,
    val sell: Double,
    @SerializedName("spot_price") val spotPrice: Double,
    val timestamp: String
) : Parcelable {

    fun getTimestampDate(): Date {
        val sdf = SimpleDateFormat(TIMESTAMP_FORMAT, Locale.getDefault())
        return try {
            sdf.parse(timestamp)
        } catch (e: ParseException) {
            e.printStackTrace()
            Date()
        }
    }

    fun getFormattedDate(): String {
        val sdf = SimpleDateFormat("MMM dd hh:mm a", Locale.getDefault())
        return sdf.format(getTimestampDate())
    }
}
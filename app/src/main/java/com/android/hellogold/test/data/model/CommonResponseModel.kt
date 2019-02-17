package com.android.hellogold.test.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CommonResponseModel(var result: String, var code: String? = null, var message: String?) : Parcelable
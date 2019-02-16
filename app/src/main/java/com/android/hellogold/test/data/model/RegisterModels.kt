package com.android.hellogold.test.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*
import javax.crypto.Cipher
import javax.crypto.KeyGenerator


@Parcelize
data class RegisterPayload(
    var email: String,
    var uuid: String = UUID.randomUUID().toString(),
    var data: String,
    var tnc: String
) : Parcelable {
    companion object {
        fun get256StringFromKey(key: String): String {
            val generator = KeyGenerator.getInstance("AES")
            generator.init(256)
            val secretKey = generator.generateKey()
            val c = Cipher.getInstance("AES")
            c.init(Cipher.ENCRYPT_MODE, secretKey)
            val encrypted = c.doFinal(key.toByteArray())
            return String(encrypted)
        }
    }
}

@Parcelize
data class RegisterResponse(
    var result: String,
    var code: String?,
    var message: String?,
    var data: RegisterResponseData?
) : Parcelable {

    val isSuccess = result.equals("ok", ignoreCase = true)
}

@Parcelize
data class RegisterResponseData(
    @SerializedName("api_token") var apiToken: String,
    @SerializedName("public_key") var publicKey: String,
    @SerializedName("api_key") var apiKey: String,
    @SerializedName("account_number") var accountNumber: String
) : Parcelable

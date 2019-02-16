@file:Suppress("NOTHING_TO_INLINE")

package com.android.hellogold.test.extensions

import android.text.TextUtils
import android.util.Patterns

inline fun String.currencyUnit(): String {
    return when (this) {
        "TH" -> "฿"
        else -> {
            "฿"
        }
    }
}

inline fun String.isValidEmail(): Boolean = Patterns.EMAIL_ADDRESS.matcher(this).matches()

inline fun String.isMobileNumber(): Boolean {
    if (this.isEmpty()) return true
    return matches("\\+?[0-9]+?".toRegex()) || (length == 1) and startsWith("+")
}

inline fun String.htmlEncode(): String = TextUtils.htmlEncode(this)

inline fun String.removeLeadZero(): String = replaceFirst("^0+(?!$)", "")

inline fun String.trimDoubleQuotes(): String {
    var trimmedString = this
    if (startsWith("\"")) trimmedString = trimmedString.substring(1)

    if (endsWith("\"")) trimmedString = trimmedString.substring(0, trimmedString.length - 1)

    return trimmedString;
}


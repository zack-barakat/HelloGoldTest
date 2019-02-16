package com.android.hellogold.test.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView


fun TextView.beforeTextChanged(action: (s: CharSequence, start: Int, count: Int, after: Int) -> Unit) =
    addTextChangedListener(beforeTextChanged = action)

fun TextView.onTextChanged(action: (s: CharSequence, start: Int, count: Int, after: Int) -> Unit) =
    addTextChangedListener(onTextChanged = action)

fun TextView.afterTextChanged(action: (s: Editable) -> Unit) = addTextChangedListener(afterTextChanged = action)


fun TextView.addTextChangedListener(
    beforeTextChanged: ((s: CharSequence, start: Int, count: Int, after: Int) -> Unit)? = null,
    onTextChanged: ((s: CharSequence, start: Int, before: Int, count: Int) -> Unit)? = null,
    afterTextChanged: ((s: Editable) -> Unit)? = null
): TextWatcher {
    val listener = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            beforeTextChanged?.invoke(s, start, count, after)
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            onTextChanged?.invoke(s, start, before, count)
        }

        override fun afterTextChanged(s: Editable) {
            afterTextChanged?.invoke(s)
        }
    }
    addTextChangedListener(listener)
    return listener
}

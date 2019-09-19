package com.application.revoluttestapp.util

import android.content.Context
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

fun getCurrencyNameResId(context: Context, symbol: String) =
    context.resources.getIdentifier("currency_$symbol", "string", context.packageName)

fun getCurrencyFlagId(context: Context, symbol: String) = context.resources.getIdentifier(
    "ic_" + symbol + "_flag", "mipmap", context.packageName)

fun String.toFloat(): Float = if (isNullOrBlank()) {
    0F
} else {
    DecimalFormat("0.#", DecimalFormatSymbols.getInstance(Locale.getDefault())).parse(this).toFloat()
}

fun Float.format() : String = String.format(Locale.getDefault(), "%.2f", this)
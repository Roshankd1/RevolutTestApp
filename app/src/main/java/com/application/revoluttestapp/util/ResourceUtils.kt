package com.application.revoluttestapp.util

import android.content.Context

fun getCurrencyNameResId(context: Context, symbol: String) =
    context.resources.getIdentifier("currency_$symbol", "string", context.packageName)

fun getCurrencyFlagId(context: Context, symbol: String) = context.resources.getIdentifier(
    "ic_" + symbol + "_flag", "mipmap", context.packageName)
package com.kurniawan.newsapp.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun dateFormatter(input: String): String {
    val normalizedInput = input.replace("(\\.\\d{3})\\d*Z$".toRegex(), "$1Z")

    val sdfInput = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
    sdfInput.timeZone = TimeZone.getTimeZone("UTC")

    val sdfOutput = SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH)

    val date = sdfInput.parse(normalizedInput)
    return sdfOutput.format(date as Date)
}
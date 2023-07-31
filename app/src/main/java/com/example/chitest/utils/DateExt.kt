package com.example.chitest.utils

import java.text.SimpleDateFormat
import java.util.Locale

val ddMMyyyyFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

fun Long.toddMMyyyyFormat(): String {
    return ddMMyyyyFormat.format(this)
}
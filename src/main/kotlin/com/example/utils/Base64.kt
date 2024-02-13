package com.example.utils

import java.util.*


fun encodeToBase64(input: String): String {
    val encodedBytes = Base64.getEncoder().encode(input.toByteArray())
    return String(encodedBytes)
}

fun decodeFromBase64(input: String): String{
    val decodeBytes = Base64.getDecoder().decode(input.toByteArray())
    return String(decodeBytes)
}

package com.karna.mycards.presentation.util

object AppUtils {

    fun formatCardNumber(cardNumber: String): String {
        val cleanedCardNumber = cardNumber.replace(" ", "")
        // Insert a space after every 4 digits
        return cleanedCardNumber.chunked(4).joinToString(" ")
    }

}
package com.karna.mycards.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Card(
    val cardNo: String,
    val expiryDate: String,
    val cvv: String,
    val nameOnCard: String,
    val cardBank: String,
    val cardPaymentNetworkType: String,
    val cardType: String,
    @PrimaryKey val id: Int? = null
)

enum class CardBank {
    Axis, ICICI, BOB, SBI, IDBI, HDFC, Other
}

enum class CardType {
    Debit, Credit
}

enum class CardPaymentNetwork {
    Visa, Master, RuPay, Amex, Diners, Discover
}

enum class CardBenefits {
    Fuel, Shopping, Travel, Food
}

class InvalidCardException(message: String): Exception(message)



package com.karna.mycards.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Card(
    val cardType: CardType,
    val nameOnCard: String,
    val cardNo: String,
    val expiryDate: String,
    val cvv: Int,
    val cardBank: CardBank,
    val cardPaymentNetworkType: CardPaymentNetwork,
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

class InvalidCardException(message: String): Exception(message)



package com.karna.mycards.domain.use_case

import com.karna.mycards.data.repository.CardRepository
import com.karna.mycards.domain.model.Card
import com.karna.mycards.domain.model.InvalidCardException

class AddCard(
    private val repository: CardRepository
) {

    @Throws(InvalidCardException::class)
    suspend operator fun invoke(card: Card) {
        if (card.cardNo.isBlank()){
            throw InvalidCardException("Card Number can't be empty.")
        }
        if (card.cardNo.length < 16){
            throw InvalidCardException("Invalid Card Number.")
        }
        if (card.expiryDate.isBlank()){
            throw InvalidCardException("Invalid Expiry Date.")
        }
        if (card.cvv.isBlank() || card.cvv.length < 3){
            throw InvalidCardException("Invalid Cvv.")
        }
        if (card.nameOnCard.isBlank()){
            throw InvalidCardException("Name on the card can't be empty.")
        }
        if (card.cardBank.isBlank()){
            throw InvalidCardException("Invalid Card Bank.")
        }
        if (card.cardPaymentNetworkType.isBlank()){
            throw InvalidCardException("Invalid Payment Network type.")
        }
        if (card.cardType.isBlank()){
            throw InvalidCardException("Invalid Card type.")
        }
        // Need to handle other cases
        repository.insertCard(card)
    }

}
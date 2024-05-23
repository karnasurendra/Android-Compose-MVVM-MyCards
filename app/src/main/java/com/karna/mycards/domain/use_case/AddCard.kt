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
        if (card.nameOnCard.isBlank()){
            throw InvalidCardException("Name on the card can't be empty.")
        }
        // Need to handle other cases
        repository.insertCard(card)
    }

}
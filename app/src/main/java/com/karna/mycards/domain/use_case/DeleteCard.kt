package com.karna.mycards.domain.use_case

import com.karna.mycards.data.repository.CardRepository
import com.karna.mycards.domain.model.Card

class DeleteCard(private val repository: CardRepository) {
    suspend operator fun invoke(card: Card){
        repository.deleteCard(card)
    }

}
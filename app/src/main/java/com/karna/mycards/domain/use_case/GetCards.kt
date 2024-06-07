package com.karna.mycards.domain.use_case

import com.karna.mycards.data.repository.CardRepository
import com.karna.mycards.domain.model.Card
import kotlinx.coroutines.flow.Flow

class GetCards(private val repository: CardRepository) {
    operator fun invoke(): Flow<List<Card>> {
        return repository.getCards()
    }

}
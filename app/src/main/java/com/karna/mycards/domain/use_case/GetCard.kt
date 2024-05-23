package com.karna.mycards.domain.use_case

import com.karna.mycards.data.repository.CardRepository
import com.karna.mycards.domain.model.Card

class GetCard(private val repository: CardRepository) {
    suspend operator fun invoke(id: Int): Card? {
        return repository.getCardById(id)
    }

}
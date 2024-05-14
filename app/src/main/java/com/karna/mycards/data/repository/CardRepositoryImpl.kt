package com.karna.mycards.data.repository

import com.karna.mycards.data.data_source.CardDao
import com.karna.mycards.data.model.Card
import kotlinx.coroutines.flow.Flow

class CardRepositoryImpl(private val dao: CardDao) : CardRepository {
    override suspend fun getCards(): Flow<List<Card>> {
        return dao.getCards()
    }

    override suspend fun getCardById(id: Int): Card? {
        return dao.getCardById(id)
    }

    override suspend fun insertCard(card: Card) {
        return dao.insertCard(card)
    }

    override suspend fun deleteCard(card: Card) {
        return dao.deleteCard(card)
    }
}
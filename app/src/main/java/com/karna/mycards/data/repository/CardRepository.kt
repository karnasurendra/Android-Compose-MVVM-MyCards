package com.karna.mycards.data.repository

import com.karna.mycards.data.model.Card
import kotlinx.coroutines.flow.Flow

interface CardRepository {

    suspend fun getCards(): Flow<List<Card>>

    suspend fun getCardById(id: Int): Card?

    suspend fun insertCard(card: Card)

    suspend fun deleteCard(card: Card)

}
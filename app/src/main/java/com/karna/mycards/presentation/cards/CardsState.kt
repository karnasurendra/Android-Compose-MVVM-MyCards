package com.karna.mycards.presentation.cards

import com.karna.mycards.data.model.Card

data class CardsState(
    val cards: List<Card> = emptyList()
)

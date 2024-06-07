package com.karna.mycards.presentation.cards

import com.karna.mycards.domain.model.Card

data class CardsState(
    val cards: List<Card> = emptyList()
)

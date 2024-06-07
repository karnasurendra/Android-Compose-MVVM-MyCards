package com.karna.mycards.domain.use_case

data class CardUseCases(
    val getCards: GetCards,
    val getCard: GetCard,
    val addCard: AddCard,
    val deleteCard: DeleteCard
)

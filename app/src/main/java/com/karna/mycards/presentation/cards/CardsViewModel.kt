package com.karna.mycards.presentation.cards

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karna.mycards.domain.model.Card
import com.karna.mycards.domain.use_case.CardUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardsViewModel @Inject constructor(private val cardUseCases: CardUseCases) : ViewModel() {

    private val _state = mutableStateOf(CardsState())
    val state: State<CardsState> = _state

    private val _selectedCard = mutableStateOf(
        Card(
            cardNo = "",
            expiryDate = "",
            cvv = "",
            cardType = "",
            cardBank = "",
            cardPaymentNetworkType = "",
            nameOnCard = ""
        )
    )
    val selectedCard: State<Card> = _selectedCard

    private var getCardJob: Job? = null

    init {
        getCards()
    }

    private fun getCards() {
        getCardJob?.cancel()
        getCardJob = cardUseCases.getCards().onEach { cards ->
            _state.value = state.value.copy(
                cards = cards
            )
        }.launchIn(viewModelScope)
    }

    fun deleteCard(card: Card) {
        viewModelScope.launch {
            cardUseCases.deleteCard(card)
            getCards()
        }
    }

    fun updateSelectedCard(card: Card) {
        _selectedCard.value = card
    }


}
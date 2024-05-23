package com.karna.mycards.presentation.add_edit_card


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karna.mycards.domain.model.Card
import com.karna.mycards.domain.model.CardBank
import com.karna.mycards.domain.model.CardPaymentNetwork
import com.karna.mycards.domain.model.CardType
import com.karna.mycards.domain.use_case.CardUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditCardViewModel @Inject constructor(
    private val cardUseCases: CardUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _cardNumber = mutableStateOf(
        CardTextFieldState(
            hint = "Enter Card Number..."
        )
    )
    val cardNumber: State<CardTextFieldState> = _cardNumber

    private val _nameOnCard = mutableStateOf(
        CardTextFieldState(
            hint = "Enter Name On Card..."
        )
    )
    val nameOnCard: State<CardTextFieldState> = _nameOnCard

    private val _cardCvv = mutableStateOf(CardTextFieldState(hint = "Enter CVV"))
    val cardCvv: State<CardTextFieldState> = _cardCvv

    private val _cardExpiryDate = mutableStateOf(Pair(0, 0))
    val cardExpiryDate: State<Pair<Int, Int>> = _cardExpiryDate

    private val _cardBank = mutableStateOf(
        CardBank.SBI
    )
    val cardBank: State<CardBank> = _cardBank

    private val _cardPaymentNetwork = mutableStateOf(
        CardPaymentNetwork.RuPay
    )
    val cardPaymentNetwork: State<CardPaymentNetwork> = _cardPaymentNetwork


    private val _cardType = mutableStateOf(
        CardType.Credit
    )
    val cardType: State<CardType> = _cardType

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentCardId: Int? = null

    init {
        savedStateHandle.get<Int>("cardId")?.let { cardId ->

            if (cardId != -1) {

            }

        }
    }

    fun onEvent(event: AddEditCardEvent) {
        when (event) {
            is AddEditCardEvent.EnteredCardNumber -> {
                _cardNumber.value = cardNumber.value.copy(
                    text = event.value
                )
            }

            is AddEditCardEvent.ChangeCardNoFocus -> {
                _cardNumber.value = cardNumber.value.copy(
                    isHintVisible = !event.focusState.isFocused && cardNumber.value.text.isBlank()
                )
            }

            is AddEditCardEvent.EnteredNameOnCard -> {
                _nameOnCard.value = nameOnCard.value.copy(
                    text = event.nameOnCard
                )
            }

            is AddEditCardEvent.ChangeCardNameFocus -> {
                _nameOnCard.value = nameOnCard.value.copy(
                    isHintVisible = !event.focusState.isFocused && cardNumber.value.text.isBlank()
                )
            }

            is AddEditCardEvent.EnteredCardCvv -> {
                _cardCvv.value = cardCvv.value.copy(
                    text = event.cvv.toString()
                )
            }

            is AddEditCardEvent.ChangeCardCvvFocus -> {
                _cardCvv.value = cardCvv.value.copy(
                    isHintVisible = !event.focusState.isFocused && cardNumber.value.text.isBlank()
                )
            }

            is AddEditCardEvent.SelectedCardExpiryDate -> {
                _cardExpiryDate.value = Pair(event.date, event.month)
            }

            is AddEditCardEvent.SelectedCardBank -> {
                _cardBank.value = event.cardBank
            }

            is AddEditCardEvent.SelectedCardPaymentNetwork -> {
                _cardPaymentNetwork.value = event.cardPaymentNetwork
            }

            is AddEditCardEvent.SelectedCardType -> {
                _cardType.value = event.cardType
            }

            AddEditCardEvent.SaveCard -> {

            }
        }
    }

    private fun saveCard() = viewModelScope.launch {
        try {
//            cardUseCases.addCard(card = Card(
//                // Payoff
//            ))
        }catch (e:Exception){
            _eventFlow.emit(
                UiEvent.ShowSnackBar(
                    message = e.message ?: "Couldn't save card"
                )
            )
        }
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()

        object SaveNote : UiEvent()

    }

}
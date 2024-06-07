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
    private val cardUseCases: CardUseCases
) : ViewModel() {

    private val _cardNumber = mutableStateOf(
        CardTextFieldState(
            hint = "Card Number..."
        )
    )
    val cardNumber: State<CardTextFieldState> = _cardNumber

    private val _nameOnCard = mutableStateOf(
        CardTextFieldState(
            hint = "Name On Card..."
        )
    )
    val nameOnCard: State<CardTextFieldState> = _nameOnCard

    private val _cardCvv = mutableStateOf(CardTextFieldState(hint = "Cvv..."))
    val cardCvv: State<CardTextFieldState> = _cardCvv

    private val _cardExpiryDate = mutableStateOf(CardTextFieldState(hint = "Date..."))
    val cardExpiryDate: State<CardTextFieldState> = _cardExpiryDate

    private val _cardBank = mutableStateOf(
        CardTextFieldState(hint = "Select Card Bank...")
    )
    val cardBank: State<CardTextFieldState> = _cardBank

    private val _cardPaymentNetwork = mutableStateOf(
        CardTextFieldState(hint = "Select Payment Network...")
    )
    val cardPaymentNetwork: State<CardTextFieldState> = _cardPaymentNetwork

    private val _cardType = mutableStateOf(
        CardTextFieldState(hint = "Select Card Type...")
    )
    val cardType: State<CardTextFieldState> = _cardType

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    var cardBanksList: List<String>
    var cardTypeList: List<String>
    var cardPaymentNetworkList: List<String>

    init {
        cardBanksList = prepareCardBanksList()
        cardTypeList = prepareCardTypeList()
        cardPaymentNetworkList = prepareCardPaymentNetworkList()
    }

    private fun prepareCardBanksList(): List<String> {
        return CardBank.entries.map { it.name }
    }

    private fun prepareCardTypeList(): List<String> {
        return CardType.entries.map { it.name }
    }

    private fun prepareCardPaymentNetworkList(): List<String> {
        return CardPaymentNetwork.entries.map { it.name }
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

            is AddEditCardEvent.EnteredCardExpiryDate -> {
                _cardExpiryDate.value = cardExpiryDate.value.copy(
                    text = event.expiryDate
                )
            }

            is AddEditCardEvent.ChangeExpiryDateFocus -> {
                _cardCvv.value = cardCvv.value.copy(
                    isHintVisible = !event.focusState.isFocused && cardNumber.value.text.isBlank()
                )
            }

            is AddEditCardEvent.EnteredCardCvv -> {
                _cardCvv.value = cardCvv.value.copy(
                    text = event.cvv
                )
            }

            is AddEditCardEvent.ChangeCardCvvFocus -> {
                _cardCvv.value = cardCvv.value.copy(
                    isHintVisible = !event.focusState.isFocused && cardNumber.value.text.isBlank()
                )
            }


            is AddEditCardEvent.SelectedCardBank -> {
                _cardBank.value = cardBank.value.copy(
                    text = event.cardBank
                )
            }

            is AddEditCardEvent.SelectedCardPaymentNetwork -> {
                _cardPaymentNetwork.value = cardPaymentNetwork.value.copy(
                    text = event.cardPaymentNetwork
                )
            }

            is AddEditCardEvent.SelectedCardType -> {
                _cardType.value = cardType.value.copy(
                    text = event.cardType
                )
            }

            AddEditCardEvent.SaveCard -> {
                saveCard()
            }
        }
    }

    private fun saveCard() = viewModelScope.launch {
        try {
            cardUseCases.addCard(
                card = Card(
                    cardNo = cardNumber.value.text,
                    expiryDate = cardExpiryDate.value.text,
                    cvv = cardCvv.value.text,
                    nameOnCard = nameOnCard.value.text,
                    cardType = cardType.value.text,
                    cardBank = cardBank.value.text,
                    cardPaymentNetworkType = cardPaymentNetwork.value.text
                )
            )
            _eventFlow.emit(
                UiEvent.SaveCard()
            )
        } catch (e: Exception) {
            _eventFlow.emit(
                UiEvent.ShowSnackBar(
                    message = e.message ?: "Couldn't save card"
                )
            )
        }
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        class SaveCard : UiEvent()

    }

}
package com.karna.mycards.presentation.add_edit_card

import androidx.compose.ui.focus.FocusState
import com.karna.mycards.domain.model.CardBank
import com.karna.mycards.domain.model.CardPaymentNetwork
import com.karna.mycards.domain.model.CardType

sealed class AddEditCardEvent {
    data class EnteredCardNumber(val value: String) : AddEditCardEvent()
    data class ChangeCardNoFocus(val focusState: FocusState) : AddEditCardEvent()
    data class EnteredNameOnCard(val nameOnCard: String) : AddEditCardEvent()
    data class ChangeCardNameFocus(val focusState: FocusState) : AddEditCardEvent()
    data class EnteredCardExpiryDate(val expiryDate: String) : AddEditCardEvent()
    data class ChangeExpiryDateFocus(val focusState: FocusState) : AddEditCardEvent()
    data class EnteredCardCvv(val cvv: String) : AddEditCardEvent()
    data class ChangeCardCvvFocus(val focusState: FocusState) : AddEditCardEvent()
    data class SelectedCardBank(val cardBank: String) : AddEditCardEvent()
    data class SelectedCardPaymentNetwork(val cardPaymentNetwork: String) :
        AddEditCardEvent()
    data class SelectedCardType(val cardType: String) : AddEditCardEvent()

    object SaveCard : AddEditCardEvent()

}
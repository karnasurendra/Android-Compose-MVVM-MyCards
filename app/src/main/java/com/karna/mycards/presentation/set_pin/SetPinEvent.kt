package com.karna.mycards.presentation.set_pin

sealed class SetPinEvent {
    data class EnteredPin(val pin: String) : SetPinEvent()
    data class EnteredConfirmPin(val confirmPin: String) : SetPinEvent()
    object SetPin : SetPinEvent()

}
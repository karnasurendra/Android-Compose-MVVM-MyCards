package com.karna.mycards.presentation.login_with_pin

sealed class LoginWithPinEvent {

    data class OnPinEntered(val pin: String) : LoginWithPinEvent()
    object DoLogin : LoginWithPinEvent()

}
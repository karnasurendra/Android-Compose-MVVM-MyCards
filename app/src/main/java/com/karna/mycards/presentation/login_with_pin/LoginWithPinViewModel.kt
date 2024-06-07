package com.karna.mycards.presentation.login_with_pin

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karna.mycards.presentation.add_edit_card.CardTextFieldState
import com.karna.mycards.presentation.set_pin.SetPinViewModel
import com.karna.mycards.presentation.util.proto.UserInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginWithPinViewModel @Inject constructor(private val userDataStore: DataStore<UserInfo>) :
    ViewModel() {

    private val _pin = mutableStateOf(CardTextFieldState(hint = "PIN..."))
    val pin: State<CardTextFieldState> = _pin

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var userPin = -1

    init {
        viewModelScope.launch {
            userDataStore.data.collect { info ->
                userPin = info.pin
            }
        }
    }

    fun onEvent(event: LoginWithPinEvent) {
        when (event) {
            LoginWithPinEvent.DoLogin -> {
                viewModelScope.launch {
                    if (pin.value.text.isEmpty() || pin.value.text.length < 4){
                        _eventFlow.emit(UIEvent.ShowSnackBar("Invalid PIN."))
                        return@launch
                    }
                    if (pin.value.text == userPin.toString()){
                        _eventFlow.emit(UIEvent.Login())
                    } else{
                        _eventFlow.emit(UIEvent.ShowSnackBar("Wrong PIN."))
                    }
                }
            }

            is LoginWithPinEvent.OnPinEntered -> {
                _pin.value = pin.value.copy(
                    text = event.pin.trim()
                )
            }
        }
    }


    sealed class UIEvent {
        data class ShowSnackBar(val message: String) : UIEvent()
        class Login : UIEvent()

    }

}
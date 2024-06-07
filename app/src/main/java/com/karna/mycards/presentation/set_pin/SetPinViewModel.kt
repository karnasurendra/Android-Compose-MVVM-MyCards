package com.karna.mycards.presentation.set_pin

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karna.mycards.presentation.add_edit_card.CardTextFieldState
import com.karna.mycards.presentation.util.proto.UserInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetPinViewModel @Inject constructor(private val userDataStore: DataStore<UserInfo>) : ViewModel() {

    private val _pin = mutableStateOf(
        CardTextFieldState(hint = "PIN...")
    )
    val pin: State<CardTextFieldState> = _pin

    private val _confirmPin = mutableStateOf(
        CardTextFieldState(hint = "Confirm PIN...")
    )
    val confirmPin: State<CardTextFieldState> = _confirmPin

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: SetPinEvent) {
        when (event) {
            is SetPinEvent.EnteredPin -> {
                _pin.value = pin.value.copy(
                    text = event.pin.trim()
                )
            }

            is SetPinEvent.EnteredConfirmPin -> {
                _confirmPin.value = confirmPin.value.copy(
                    text = event.confirmPin.trim()
                )
            }

            SetPinEvent.SetPin -> {
                viewModelScope.launch {
                    if (pin.value.text.isEmpty()){
                        _eventFlow.emit(UiEvent.ShowSnackBar("PIN Can't be empty."))
                        return@launch
                    }
                    if (pin.value.text.length < 4){
                        _eventFlow.emit(UiEvent.ShowSnackBar("PIN Should be in 4 digits."))
                        return@launch
                    }
                    if (confirmPin.value.text.isEmpty()){
                        _eventFlow.emit(UiEvent.ShowSnackBar("Confirm PIN Can't be empty."))
                        return@launch
                    }
                    if (pin.value.text != confirmPin.value.text){
                        _eventFlow.emit(UiEvent.ShowSnackBar("PIN and Confirm PIN not matching."))
                        return@launch
                    }

                    // Save to Proto
                    userDataStore.updateData {info ->
                        info.copy(pin = pin.value.text.toInt())
                    }

                    _eventFlow.emit(UiEvent.SetPIN())

                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        class SetPIN : UiEvent()

    }

}
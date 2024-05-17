package com.karna.mycards.presentation.cards

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CardsViewModel @Inject constructor() : ViewModel() {

    private val _state = mutableStateOf(CardsState())
    val state: State<CardsState> = _state

}
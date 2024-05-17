package com.karna.mycards.presentation.add_edit_card


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditCardViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {

    init {
        savedStateHandle.get<Int>("cardId")?.let { cardId ->

            if (cardId != -1) {

            }

        }
    }

}
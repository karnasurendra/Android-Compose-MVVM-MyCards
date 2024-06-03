package com.karna.mycards.presentation.add_edit_card.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Composable
fun CommonTextField(
    text: String,
    hint: String,
    modifier: Modifier = Modifier,
    isHintVisible: Boolean = true,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = TextStyle(),
    singleLine: Boolean = false,
    maxLength: Int?,
    onFocusChange: (FocusState) -> Unit,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions
) {
    Box(
        modifier = modifier
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = {
                if (it.length <= (maxLength ?: Int.MAX_VALUE)) {
                    onValueChange(it)
                }
            },
            singleLine = singleLine,
            textStyle = textStyle,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    onFocusChange(it)
                },
            label = {
                Text(text = hint, style = textStyle, color = Color.Gray)
            }
        )
    }
}







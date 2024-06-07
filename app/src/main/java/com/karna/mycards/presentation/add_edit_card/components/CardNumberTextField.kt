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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun CardNumberTextField(
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
                val normalized = normalizeCardNumber(it)
                if (normalized.length <= (maxLength ?: Int.MAX_VALUE)) {
                    onValueChange(normalized)
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
            },
            visualTransformation = CardNumberVisualTransformation()
        )
    }
}

fun formatCardNumber(input: String): String {
    return input.chunked(4).joinToString(" ")
}

fun normalizeCardNumber(input: String): String {
    return input.replace(" ", "")
}

class CardNumberVisualTransformation : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        val formatted = formatCardNumber(text.text)
        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                // Calculates the transformed offset by adding spaces after every 4 characters
                var transformedOffset = offset + offset / 4
                if (offset % 4 == 0 && offset != 0) {
                    transformedOffset -= 1
                }
                return transformedOffset
            }

            override fun transformedToOriginal(offset: Int): Int {
                // Calculates the original offset by removing spaces
                val originalOffset = offset - offset / 5
                return originalOffset
            }
        }
        return TransformedText(AnnotatedString(formatted), offsetMapping)
    }

}







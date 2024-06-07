package com.karna.mycards.presentation.add_edit_card.components

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import java.util.Calendar

@Composable
fun TextFieldForExpiryDate(
    text: String,
    hint: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = TextStyle(),
    singleLine: Boolean = false
) {

    val context = LocalContext.current
    var datePickerDialog: DatePickerDialog? = null

    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, _: Int ->
            // Format the date as MM/YY
            val formattedDate = String.format("%02d/%02d", selectedMonth + 1, selectedYear % 100)
            onValueChange(formattedDate)
        },
        year,
        month,
        day
    ).apply {
        // Set min and max date if needed
        datePicker.maxDate = System.currentTimeMillis()
    }

    Box(
        modifier = modifier
    ) {
        OutlinedTextField(
            value = text,
            readOnly = true,
            onValueChange = { onValueChange(it) },
            singleLine = singleLine,
            textStyle = textStyle,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    if (it.isFocused) {
                        datePickerDialog.show()
                    }
                },
            label = {
                Text(text = hint, style = textStyle, color = Color.Gray)
            }
        )
    }
}
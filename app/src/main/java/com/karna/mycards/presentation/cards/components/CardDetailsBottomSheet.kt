package com.karna.mycards.presentation.cards.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.karna.mycards.R
import com.karna.mycards.domain.model.Card
import com.karna.mycards.presentation.util.AppUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardDetailBottomSheet(
    selectedCard: Card,
    dismissBottomSheet: () -> Unit,
    onDeleteSelected: () -> Unit
) {

    val sheetState = rememberModalBottomSheetState()
    val clipboardManager = LocalClipboardManager.current

    ModalBottomSheet(
        onDismissRequest = {
            dismissBottomSheet()
        },
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.secondaryContainer
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp, bottom = 20.dp)
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Text(
                    text = selectedCard.cardType,
                    style = MaterialTheme.typography.bodySmall,
                    textDecoration = TextDecoration.Underline
                )
                Text(
                    text = selectedCard.cardBank,
                    style = MaterialTheme.typography.bodySmall,
                    textDecoration = TextDecoration.Underline
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = stringResource(id = R.string.card_no),
                style = MaterialTheme.typography.titleSmall,
                color = Color.LightGray,
            )
            TextWithTrailingIcon(
                text = AppUtils.formatCardNumber(selectedCard.cardNo),
                textStyle = MaterialTheme.typography.titleLarge,
                textColor = Color.White,
                iconResId = R.drawable.content_copy,
                iconContentDescription = "Copy"
            ) {
                clipboardManager.setText(AnnotatedString(selectedCard.cardNo))
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(id = R.string.name_on_card),
                style = MaterialTheme.typography.titleSmall,
                color = Color.LightGray
            )
            TextWithTrailingIcon(
                text = selectedCard.nameOnCard,
                textStyle = MaterialTheme.typography.titleLarge,
                textColor = Color.White,
                iconResId = R.drawable.content_copy,
                iconContentDescription = "Copy"
            ) {
                clipboardManager.setText(AnnotatedString(selectedCard.nameOnCard))
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Absolute.SpaceBetween
            ) {
                Column {
                    Text(
                        text = stringResource(id = R.string.expire_date),
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.LightGray
                    )
                    TextWithTrailingIcon(
                        text = selectedCard.expiryDate,
                        textStyle = MaterialTheme.typography.titleLarge,
                        textColor = Color.White,
                        iconResId = R.drawable.content_copy,
                        iconContentDescription = "Copy"
                    ) {
                        clipboardManager.setText(AnnotatedString(selectedCard.expiryDate))
                    }
                }

                Column {
                    Text(
                        text = stringResource(id = R.string.cvv),
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.LightGray
                    )
                    TextWithTrailingIcon(
                        text = selectedCard.cvv,
                        textStyle = MaterialTheme.typography.titleLarge,
                        textColor = Color.White,
                        iconResId = R.drawable.content_copy,
                        iconContentDescription = "Copy"
                    ) {
                        clipboardManager.setText(AnnotatedString(selectedCard.cvv))
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.error),
                onClick = {
                    onDeleteSelected()
                }) {
                Text(
                    text = stringResource(id = R.string.delete),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}


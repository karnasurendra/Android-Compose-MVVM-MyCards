package com.karna.mycards.presentation.cards.components

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.karna.mycards.domain.model.Card

@Composable
fun CardItem(
    card: Card,
    modifier: Modifier,
    onDeleteClick: () -> Unit
) {
    Box(modifier = modifier) {

    }
}
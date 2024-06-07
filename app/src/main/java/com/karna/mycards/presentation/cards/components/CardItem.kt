package com.karna.mycards.presentation.cards.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.karna.mycards.R
import com.karna.mycards.domain.model.Card
import com.karna.mycards.presentation.util.AppUtils

@Composable
fun CardItem(
    card: Card,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .background(
                shape = RoundedCornerShape(15.dp),
                brush = Brush.linearGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primaryContainer,
                        MaterialTheme.colorScheme.secondaryContainer
                    )
                )
            )
            .padding(15.dp) // Optional padding around the card
    ) {
        Column {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = card.cardType,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White
                )
                Text(
                    text = card.cardBank,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(50.dp))

            Image(
                painter = painterResource(id = R.drawable.chip),
                contentDescription = "Chip Symbol",
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = AppUtils.formatCardNumber(card.cardNo),
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )

            Text(
                text = card.nameOnCard,
                style = MaterialTheme.typography.labelMedium,
                maxLines = 1,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = card.expiryDate,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White
                )
                Text(
                    text = stringResource(id = R.string.tap_to_view),
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White
                )
            }
        }

    }

}
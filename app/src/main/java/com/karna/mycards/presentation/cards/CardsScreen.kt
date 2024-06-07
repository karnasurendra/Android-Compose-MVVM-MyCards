package com.karna.mycards.presentation.cards

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.karna.mycards.R
import com.karna.mycards.presentation.cards.components.CardDetailBottomSheet
import com.karna.mycards.presentation.cards.components.CardItem
import com.karna.mycards.presentation.util.Screen
import kotlinx.coroutines.launch


@Composable
fun CardsScreen(navController: NavController, viewModel: CardsViewModel = hiltViewModel()) {

    val state = viewModel.state.value
    val selectedCard = viewModel.selectedCard.value
    val scope = rememberCoroutineScope()

    var showCardDetailBottomSheet by remember { mutableStateOf(false) }
    var showDeleteConfirmationDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Screen.AddEditCardScreen.route)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Card")
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->

        if (showDeleteConfirmationDialog) {
            AlertDialog(
                onDismissRequest = { showDeleteConfirmationDialog = false },
                confirmButton = {
                    TextButton(onClick = {
                        showDeleteConfirmationDialog = false
                        viewModel.deleteCard(selectedCard)
                    }) {
                        Text("Delete")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDeleteConfirmationDialog = false }) {
                        Text("Cancel")
                    }
                },
                title = {
                    Text(
                        text = stringResource(id = R.string.delete_card),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                text = {
                    Text(
                        text = stringResource(id = R.string.sure_delete),
                        style = MaterialTheme.typography.bodySmall
                    )
                })
        }

        if (showCardDetailBottomSheet) {
            CardDetailBottomSheet(selectedCard, dismissBottomSheet = {
                showCardDetailBottomSheet = false
            }, onDeleteSelected = {
                showCardDetailBottomSheet = false
                showDeleteConfirmationDialog = true
            })
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primaryContainer,
                            MaterialTheme.colorScheme.secondaryContainer
                        )
                    )
                )
                .padding(paddingValues)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp, top = 15.dp),
                text = stringResource(id = R.string.my_cards),
                style = MaterialTheme.typography.titleLarge
            )

            if (state.cards.isEmpty()) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .align(Alignment.CenterHorizontally),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 15.dp, end = 15.dp, top = 15.dp),
                        text = stringResource(id = R.string.no_cards),
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Button(onClick = { navController.navigate(Screen.AddEditCardScreen.route) }) {
                        Text(
                            text = stringResource(id = R.string.add_card),
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
            } else {

                Spacer(modifier = Modifier.height(15.dp))

                LazyColumn(Modifier.fillMaxSize()) {
                    items(state.cards) { card ->
                        CardItem(
                            card = card,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 15.dp, start = 15.dp, end = 15.dp)
                                .clickable {
                                    scope.launch {
                                        viewModel.updateSelectedCard(card)
                                        showCardDetailBottomSheet = true
                                    }
                                })
                    }
                }
            }

        }
    }

}







package com.karna.mycards.presentation.add_edit_card

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.karna.mycards.R
import com.karna.mycards.presentation.add_edit_card.components.CommonTextField
import com.karna.mycards.presentation.add_edit_card.components.Spinner
import com.karna.mycards.presentation.add_edit_card.components.TextFieldForExpiryDate
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditCardScreen(
    navController: NavController,
    viewModel: AddEditCardViewModel = hiltViewModel()
) {

    val cardNumberState = viewModel.cardNumber.value
    val nameOnCardState = viewModel.nameOnCard.value
    val cardExpiryDateState = viewModel.cardExpiryDate.value
    val cardCvvState = viewModel.cardCvv.value
    val cardBankState = viewModel.cardBank.value
    val cardPaymentNetworkState = viewModel.cardPaymentNetwork.value
    val cardTypeState = viewModel.cardType.value

    val snackBarHostState = remember {
        SnackbarHostState()
    }


    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                AddEditCardViewModel.UiEvent.SaveNote -> {
                    navController.navigateUp()
                }

                is AddEditCardViewModel.UiEvent.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    Scaffold(floatingActionButton = {
        FloatingActionButton(
            onClick = { viewModel.onEvent(AddEditCardEvent.SaveCard) },
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Save Card")
        }
    },
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
        ) {

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp, end = 15.dp, start = 15.dp)
            ) {

                Image(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .clickable {
                            navController.navigateUp()
                        },
                    colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(Color.White),
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )

                Spacer(modifier = Modifier.width(15.dp))

                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = stringResource(id = R.string.add_card),
                    style = MaterialTheme.typography.titleLarge
                )

            }

            Column(
                Modifier
                    .fillMaxSize()
                    .padding(15.dp)
            ) {

                Spacer(modifier = Modifier.height(15.dp))

                CommonTextField(
                    text = cardNumberState.text,
                    hint = cardNumberState.hint,
                    onValueChange = {
                        viewModel.onEvent(AddEditCardEvent.EnteredCardNumber(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(AddEditCardEvent.ChangeCardNoFocus(it))
                    },
                    isHintVisible = cardNumberState.isHintVisible,
                    singleLine = true,
                    maxLength = 16,
                    textStyle = MaterialTheme.typography.labelMedium,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {

                        }
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(Modifier.fillMaxWidth()) {

                    TextFieldForExpiryDate(
                        modifier = Modifier.weight(1f),
                        text = cardExpiryDateState.text,
                        hint = cardExpiryDateState.hint,
                        onValueChange = {
                            viewModel.onEvent(AddEditCardEvent.EnteredCardExpiryDate(it))
                        },
                        onFocusChange = {
                            viewModel.onEvent(AddEditCardEvent.ChangeExpiryDateFocus(it))
                        },
                        singleLine = true,
                        textStyle = MaterialTheme.typography.labelMedium
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    CommonTextField(
                        modifier = Modifier.weight(1f),
                        text = cardCvvState.text,
                        hint = cardCvvState.hint,
                        onValueChange = {
                            viewModel.onEvent(AddEditCardEvent.EnteredCardCvv(it))
                        },
                        onFocusChange = {
                            viewModel.onEvent(AddEditCardEvent.ChangeCardCvvFocus(it))
                        },
                        isHintVisible = cardCvvState.isHintVisible,
                        singleLine = true,
                        maxLength = 3,
                        textStyle = MaterialTheme.typography.labelMedium,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {

                            }
                        )
                    )

                }

                Spacer(modifier = Modifier.height(10.dp))

                CommonTextField(
                    text = nameOnCardState.text,
                    hint = nameOnCardState.hint,
                    onValueChange = {
                        viewModel.onEvent(AddEditCardEvent.EnteredNameOnCard(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(AddEditCardEvent.ChangeCardNameFocus(it))
                    },
                    isHintVisible = nameOnCardState.isHintVisible,
                    singleLine = true,
                    maxLength = Int.MAX_VALUE,
                    textStyle = MaterialTheme.typography.labelMedium,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Default
                    ),
                    keyboardActions = KeyboardActions.Default
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Card Bank
                Spinner(
                    options = viewModel.cardBanksList,
                    selectedOption = cardBankState.text,
                    onOptionSelected = {
                        viewModel.onEvent(AddEditCardEvent.SelectedCardBank(it))
                    },
                    label = "Select Card Bank..")

                Spacer(modifier = Modifier.height(10.dp))

                // Card Payment Network
                Spinner(
                    options = viewModel.cardPaymentNetworkList,
                    selectedOption = cardPaymentNetworkState.text,
                    onOptionSelected = {
                        viewModel.onEvent(AddEditCardEvent.SelectedCardPaymentNetwork(it))
                    },
                    label = "Select Payment Network..")

                Spacer(modifier = Modifier.height(10.dp))

                // Card Type
                Spinner(
                    options = viewModel.cardTypeList,
                    selectedOption = cardTypeState.text,
                    onOptionSelected = {
                        viewModel.onEvent(AddEditCardEvent.SelectedCardType(it))
                    },
                    label = "Select Card Type..")
            }

        }

    }


}
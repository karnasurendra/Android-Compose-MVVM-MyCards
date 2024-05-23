package com.karna.mycards.presentation.add_edit_card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.karna.mycards.R
import com.karna.mycards.presentation.add_edit_card.components.TransparentHintTextField
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditCardScreen(
    navController: NavController,
    viewModel: AddEditCardViewModel = hiltViewModel()
) {

    val cardNumberState = viewModel.cardNumber.value
    val nameOnCardState = viewModel.nameOnCard.value
    val cardCvvState = viewModel.cardCvv.value
    val cardExpiryDateState = viewModel.cardExpiryDate.value
    val cardBankState = viewModel.cardBank.value
    val cardPaymentNetworkState = viewModel.cardPaymentNetwork.value
    val cardTypeState = viewModel.cardType.value

    val rememberScaffoldState = rememberBottomSheetScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                AddEditCardViewModel.UiEvent.SaveNote -> {

                }

                is AddEditCardViewModel.UiEvent.ShowSnackBar -> {

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
                    modifier = Modifier.align(Alignment.CenterVertically),
                    colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(androidx.compose.ui.graphics.Color.White),
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
                    .padding(15.dp)) {

                Spacer(modifier = Modifier.height(15.dp))

                TransparentHintTextField(
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
                    textStyle = MaterialTheme.typography.labelMedium
                )

                Spacer(modifier = Modifier.height(15.dp))

                TransparentHintTextField(
                    text = nameOnCardState.text,
                    hint = nameOnCardState.hint,
                    onValueChange = {
                        viewModel.onEvent(AddEditCardEvent.EnteredNameOnCard(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(AddEditCardEvent.ChangeCardNameFocus(it))
                    },
                    isHintVisible = cardNumberState.isHintVisible,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.labelMedium
                )
            }

        }

    }


}
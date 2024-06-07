package com.karna.mycards.presentation.login_with_pin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.karna.mycards.R
import com.karna.mycards.presentation.add_edit_card.components.CommonOnlyNumberTextField
import com.karna.mycards.presentation.add_edit_card.components.CommonTextField
import com.karna.mycards.presentation.set_pin.SetPinEvent
import com.karna.mycards.presentation.set_pin.SetPinViewModel
import com.karna.mycards.presentation.util.Screen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginWithPinScreen(
    navController: NavController,
    viewModel: LoginWithPinViewModel = hiltViewModel()
) {

    val pinState = viewModel.pin.value

    val snackBarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is LoginWithPinViewModel.UIEvent.Login -> {
                    // OnSuccess Take to Cards List screen
                    navController.navigate(Screen.CardsScreen.route){
                        popUpTo(0) { inclusive = true }
                    }
                }

                is LoginWithPinViewModel.UIEvent.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(), floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // Login PIN
                    viewModel.onEvent(LoginWithPinEvent.DoLogin)
                },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "Next"
                )
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }) { paddingValues ->

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
                .padding(start = 15.dp, end = 15.dp, bottom = 30.dp)
        ) {

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = stringResource(id = R.string.login_with_pin),
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(30.dp))

            CommonOnlyNumberTextField(
                modifier = Modifier.fillMaxWidth(),
                text = pinState.text,
                hint = pinState.hint,
                onValueChange = {
                    viewModel.onEvent(LoginWithPinEvent.OnPinEntered(it))
                },
                singleLine = true,
                maxLength = 4,
                textStyle = MaterialTheme.typography.labelMedium,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions()
            )

        }

    }

}
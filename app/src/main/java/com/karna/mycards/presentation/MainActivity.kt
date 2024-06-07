package com.karna.mycards.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.karna.mycards.presentation.add_edit_card.AddEditCardScreen
import com.karna.mycards.presentation.cards.CardsScreen
import com.karna.mycards.presentation.login_with_pin.LoginWithPinScreen
import com.karna.mycards.presentation.set_pin.SetPinScreen
import com.karna.mycards.presentation.util.Screen
import com.karna.mycards.presentation.util.proto.UserInfo
import com.karna.mycards.ui.theme.MyCardsTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    @Inject
    lateinit var userInfo: DataStore<UserInfo>

    private val _aboutUser = MutableStateFlow(-1)
    private val aboutUser: StateFlow<Int> = _aboutUser

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            MyCardsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    navController = rememberNavController()

                    updateUserInfo()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.SetPinScreen.route
                    ) {
                        composable(route = Screen.SetPinScreen.route) {
                            SetPinScreen(navController = navController)
                        }
                        composable(route = Screen.LoginWithPinScreen.route) {
                            LoginWithPinScreen(navController = navController)
                        }
                        composable(route = Screen.CardsScreen.route) {
                            CardsScreen(navController = navController)
                        }
                        composable(
                            route = Screen.AddEditCardScreen.route
                        ) {
                            AddEditCardScreen(navController = navController)
                        }
                    }
                }
            }
        }

    }

    private fun updateUserInfo() = lifecycleScope.launch {
        userInfo.data.collect {
            _aboutUser.value = it.pin

            if (aboutUser.value != -1)  {
                navController.navigate(Screen.LoginWithPinScreen.route){
                    popUpTo(0) { inclusive = true }
                }
                requestBioMetricLogin()
            }
        }
    }

    private fun requestBioMetricLogin() {
        val biometricPrompt = createBiometricPrompt(this)
        showBiometricPrompt(biometricPrompt)
    }

    private fun showBiometricPrompt(biometricPrompt: BiometricPrompt) {
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG)
            .setTitle("Authenticate with Biometrics")
            .setNegativeButtonText("Use App PIN")
            .setConfirmationRequired(true)
            .build()
        biometricPrompt.authenticate(promptInfo)
    }

    private fun createBiometricPrompt(activity: FragmentActivity): BiometricPrompt {
        return BiometricPrompt(
            activity,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    // Well Nothing to do here
                    navController.navigate(Screen.CardsScreen.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            }
        )
    }
}


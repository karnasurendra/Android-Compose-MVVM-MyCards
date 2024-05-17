package com.karna.mycards.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.karna.mycards.presentation.add_edit_card.AddEditCardScreen
import com.karna.mycards.presentation.cards.CardsScreen
import com.karna.mycards.presentation.util.Screen
import com.karna.mycards.ui.theme.MyCardsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCardsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CardsScreen.route
                    ) {
                        composable(route = Screen.CardsScreen.route) {
                            CardsScreen(navController = navController)
                        }
                        composable(
                            route = Screen.AddEditCardScreen.route + "?:cardId={cardId}",
                            arguments = listOf(
                                navArgument(
                                    name = "cardId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) {
                            AddEditCardScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}


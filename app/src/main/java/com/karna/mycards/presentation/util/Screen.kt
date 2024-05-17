package com.karna.mycards.presentation.util

sealed class Screen(val route: String) {

    object CardsScreen : Screen("cards_screen")
    object AddEditCardScreen : Screen("add_edit_card_screen")

}
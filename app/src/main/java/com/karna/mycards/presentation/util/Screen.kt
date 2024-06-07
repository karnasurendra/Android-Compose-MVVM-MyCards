package com.karna.mycards.presentation.util

sealed class Screen(val route: String) {

    object SetPinScreen : Screen("set_pin_screen")
    object LoginWithPinScreen : Screen("login_with_pin_screen")
    object CardsScreen : Screen("cards_screen")
    object AddEditCardScreen : Screen("add_edit_card_screen")

}
package com.example.logindemousingcompose.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen() {
    object Login : Screen()
    object SignUp : Screen()
    object TermsAndConditionScreen : Screen()
}


object PostOfficeAppRouter {

    val currentScreen : MutableState<Screen> = mutableStateOf(Screen.Login)

    fun navigateTo(destination: Screen) {
        currentScreen.value = destination
    }

}
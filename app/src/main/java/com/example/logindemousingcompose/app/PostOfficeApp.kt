package com.example.logindemousingcompose.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.logindemousingcompose.navigation.PostOfficeAppRouter
import com.example.logindemousingcompose.navigation.Screen
import com.example.logindemousingcompose.screens.Login
import com.example.logindemousingcompose.screens.SignUp
import com.example.logindemousingcompose.screens.TermsAndConditionScreen


@Composable
fun PostOfficeApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Crossfade(targetState = PostOfficeAppRouter.currentScreen) { currentState ->
            when(currentState.value) {
                is Screen.Login-> {
                    Login()
                }

                is Screen.SignUp-> {
                    SignUp()
                }

                is Screen.TermsAndConditionScreen -> {
                    TermsAndConditionScreen()
                }
            }
        }
    }
}
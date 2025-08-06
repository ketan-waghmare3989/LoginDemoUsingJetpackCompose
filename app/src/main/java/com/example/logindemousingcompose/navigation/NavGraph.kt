package com.example.logindemousingcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.logindemousingcompose.screens.Login
import com.example.logindemousingcompose.screens.SignUp

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            Login(navController)
        }
        composable("signup") {
            SignUp(navController) // Create this screen
        }
    }
}
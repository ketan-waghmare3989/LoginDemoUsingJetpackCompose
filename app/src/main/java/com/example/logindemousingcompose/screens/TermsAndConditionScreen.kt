package com.example.logindemousingcompose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.logindemousingcompose.R
import com.example.logindemousingcompose.components.HeadingTextComponent
import com.example.logindemousingcompose.navigation.PostOfficeAppRouter
import com.example.logindemousingcompose.navigation.Screen
import com.example.logindemousingcompose.navigation.SystemBackButtonHandler

@Composable
fun TermsAndConditionScreen() {
    Surface(modifier = Modifier.fillMaxSize()
        .background(color = Color.White)
        .padding(16.dp)) {

        HeadingTextComponent(value = stringResource(R.string.terms_and_condition_header))
    }
    SystemBackButtonHandler {
        PostOfficeAppRouter.navigateTo(Screen.SignUp)
    }
}

@Preview
@Composable
fun TermsAndConditionScreenPreview() {
    TermsAndConditionScreen()
}
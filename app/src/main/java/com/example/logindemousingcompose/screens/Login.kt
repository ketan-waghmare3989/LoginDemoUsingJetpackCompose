package com.example.logindemousingcompose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.ui.Modifier
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import com.example.logindemousingcompose.R
import com.example.logindemousingcompose.components.ButtonComponent
import com.example.logindemousingcompose.components.ClickableLoginTextComponent
import com.example.logindemousingcompose.components.DividerComponent
import com.example.logindemousingcompose.components.NormalTextComponent
import com.example.logindemousingcompose.components.HeadingTextComponent
import com.example.logindemousingcompose.components.MyTextFieldComponent
import com.example.logindemousingcompose.components.PasswordTextComponent
import com.example.logindemousingcompose.components.UnderLinedTextComponent
import com.example.logindemousingcompose.data.LoginViewModel
import com.example.logindemousingcompose.data.UIEvent
import com.example.logindemousingcompose.navigation.PostOfficeAppRouter
import com.example.logindemousingcompose.navigation.Screen


@Composable
fun Login()  {
    val context = LocalContext.current;
    val viewModel: LoginViewModel = androidx.hilt.navigation.compose.hiltViewModel() // ✅ Add this line

    Surface(
        modifier = Modifier.fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column {
            NormalTextComponent(value = stringResource(R.string.login))
            HeadingTextComponent(value = stringResource(R.string.welcome))

            Spacer(modifier = Modifier.height(20.dp))

            MyTextFieldComponent(labelValue = stringResource(R.string.email),
                painterResource = painterResource(id = R.drawable.email),
                onTextSelected = {

                }
            )

            PasswordTextComponent(labelValue = stringResource(R.string.password),
                painterResource = painterResource(id = R.drawable.lock),
                onTextSelected = {

                }
            )

            Spacer(modifier = Modifier.height(10.dp))
            UnderLinedTextComponent(value = stringResource(R.string.forgot_password))

            Spacer(modifier = Modifier.height(40.dp))
            ButtonComponent(value = stringResource(R.string.login), onButtonClicked = {
                viewModel.onEvent(UIEvent.LoginButtonClicked)
            })

            Spacer(modifier = Modifier.height(20.dp))

            DividerComponent()

            ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = {
                PostOfficeAppRouter.navigateTo(Screen.SignUp)
            })
        }
    }
}

@Preview
@Composable
fun LoginPreview() {
    Login()
}
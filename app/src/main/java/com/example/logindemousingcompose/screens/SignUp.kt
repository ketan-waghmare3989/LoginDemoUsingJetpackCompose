package com.example.logindemousingcompose.screens

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.logindemousingcompose.R
import com.example.logindemousingcompose.components.ButtonComponent
import com.example.logindemousingcompose.components.CheckboxComponent
import com.example.logindemousingcompose.components.ClickableLoginTextComponent
import com.example.logindemousingcompose.components.DividerComponent
import com.example.logindemousingcompose.components.HeadingTextComponent
import com.example.logindemousingcompose.components.MyTextFieldComponent
import com.example.logindemousingcompose.components.NormalTextComponent
import com.example.logindemousingcompose.components.PasswordTextComponent
import com.example.logindemousingcompose.data.LoginViewModel
import com.example.logindemousingcompose.data.UIEvent
import com.example.logindemousingcompose.navigation.PostOfficeAppRouter
import com.example.logindemousingcompose.navigation.Screen

@Composable
fun SignUp(loginViewModel: LoginViewModel = viewModel()) {
    Surface(
        modifier = Modifier.fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {

        Column(modifier = Modifier.fillMaxSize()){
            NormalTextComponent(value = stringResource(id = R.string.hello))
            HeadingTextComponent(value = stringResource(id = R.string.create_account))
            Spacer(modifier = Modifier.height(20.dp))

            MyTextFieldComponent(labelValue = stringResource(id = R.string.first_name),
                painterResource(R.drawable.profile),
                onTextSelected = {
                    loginViewModel.onEvent(UIEvent.FirstNameChanged(it))
                },
                errorStatus = loginViewModel.registrationUIState.value.firstNameError
            )
            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.last_name),
                painterResource = painterResource(R.drawable.profile),
                onTextSelected = {
                    loginViewModel.onEvent(UIEvent.LastNameChanged(it))
                },
                errorStatus = loginViewModel.registrationUIState.value.lastNameError
            )
            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.email),
                painterResource = painterResource(R.drawable.email),
                onTextSelected = {
                    loginViewModel.onEvent(UIEvent.EmailChanged(it))
                },
                errorStatus = loginViewModel.registrationUIState.value.emailError
            )

            PasswordTextComponent(
                labelValue = stringResource(R.string.password),
                painterResource = painterResource(R.drawable.lock),
                onTextSelected = {
                    loginViewModel.onEvent(UIEvent.PasswordChanged(it))
                },
                errorStatus = loginViewModel.registrationUIState.value.passwordError
            )

            CheckboxComponent(value = stringResource(id = R.string.terms_and_condition),
                onTextSelected = {
                    PostOfficeAppRouter.navigateTo(Screen.TermsAndConditionScreen)
                })

            Spacer(modifier = Modifier.height(90.dp))

            ButtonComponent(value = stringResource(R.string.register), onButtonClicked = {
                loginViewModel.onEvent(UIEvent.RegisterButtonClicked)
            })

            Spacer(modifier = Modifier.height(20.dp))

            DividerComponent()

            ClickableLoginTextComponent(onTextSelected ={
                PostOfficeAppRouter.navigateTo(Screen.Login)
            })
        }
    }
}


@Preview (showBackground = true)
@Composable
fun DefaultPreviewOfSignUpScreen() {
    SignUp()
}
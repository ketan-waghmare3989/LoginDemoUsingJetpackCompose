package com.example.logindemousingcompose.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.logindemousingcompose.data.rules.Validator

class LoginViewModel : ViewModel() {

    private val TAG = LoginViewModel::class.simpleName

    var registrationUIState = mutableStateOf(RegistrationUIState())

    fun onEvent(event : UIEvent) {
        validateDataWithRules()
        when(event) {
            is UIEvent.FirstNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    firstName = event.firstName
                )

                printState()
            }

            is UIEvent.LastNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    lastName = event.lastName
                )

                printState()
            }

            is UIEvent.EmailChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    email = event.email
                )

                printState()
            }

            is UIEvent.PasswordChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    password = event.password
                )

                printState()
            }

            is UIEvent.RegisterButtonClicked -> {
                signUpAfterValidation()
            }
        }
    }

    private fun signUpAfterValidation() {
        Log.d(TAG,"Inside_signup")
        validateDataWithRules()
    }

    private fun validateDataWithRules() {
        val firstNameResult = Validator.validateFirstName(
            fName = registrationUIState.value.firstName
        )

        val lastNameResult = Validator.validateLastName(
            lName = registrationUIState.value.lastName
        )

        val emailResult = Validator.validateEmail(
            email = registrationUIState.value.email
        )

        val passwordResult = Validator.validatePassword(
            password = registrationUIState.value.password
        )

        Log.d(TAG, "firstNameResult $firstNameResult")
        Log.d(TAG, "lastNameResult $lastNameResult")
        Log.d(TAG, "emailResult $emailResult")
        Log.d(TAG, "passwordResult $passwordResult")

        registrationUIState.value = registrationUIState.value.copy(
            firstNameError = firstNameResult.status,
            lastNameError = lastNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )

    }

    private fun printState() {
        Log.d(TAG,"Inside printState")
        Log.d(TAG,registrationUIState.value.toString())
    }

}
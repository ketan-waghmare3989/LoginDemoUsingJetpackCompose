package com.example.logindemousingcompose.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logindemousingcompose.data.rules.Validator
import com.example.logindemousingcompose.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

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

            is UIEvent.LoginButtonClicked -> {
                callLoginAPI()
            }

        }
    }

    private fun callLoginAPI() {
        val state = registrationUIState.value
        viewModelScope.launch {
            try {
                registrationUIState.value = state.copy(isLoading = true)
                val response = loginUseCase("Ketan", "Ketan@123")

                if(response.isSuccessful) {
                    Log.d(TAG, "Login success: ${response.body()}")
                } else {
                    Log.d(TAG, "Login not success: ${response.body()}")
                }

                registrationUIState.value = state.copy(
                    isLoading = false,
                    token = response.toString()
                )

            } catch (e: Exception) {
                Log.e(TAG, "Login failed", e)
                registrationUIState.value = state.copy(
                    isLoading = false,
                    error = e.localizedMessage ?: "Unknown error"
                )
            }
        }
    }

    private fun signUpAfterValidation() {
        Log.d(TAG,"Inside_signup")
        validateDataWithRules()

        val state = registrationUIState.value
        if (state.firstNameError || state.lastNameError || state.emailError || state.passwordError) {
            return
        }

        viewModelScope.launch {
            try {
                registrationUIState.value = state.copy(isLoading = true)
                val response = loginUseCase(registrationUIState.value.email, registrationUIState.value.password)
                if(response.isSuccessful) {
                    Log.d(TAG, "Login success: ${response.body()}")
                    //TODO:store the token to the local storage
                } else {
                    Log.d(TAG, "Login not success: ${response.body()}")
                }



                registrationUIState.value = state.copy(
                    isLoading = false,
                    token = response.toString()
                )

            } catch (e: Exception) {
                Log.e(TAG, "Login failed", e)
                registrationUIState.value = state.copy(
                    isLoading = false,
                    error = e.localizedMessage ?: "Unknown error"
                )
            }
        }
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
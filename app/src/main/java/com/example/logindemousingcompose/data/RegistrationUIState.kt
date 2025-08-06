package com.example.logindemousingcompose.data

import com.example.logindemousingcompose.domain.model.User

data class RegistrationUIState(
    var firstName : String = "",
    var lastName : String = "",
    var email : String = "",
    var password : String = "",


    val firstNameError : Boolean = false,
    val lastNameError : Boolean = false,
    val emailError : Boolean = false,
    val passwordError : Boolean = false,

    val isLoading: Boolean = false,
    val token: String? = null,
    val user: User? = null,
    val error: String? = null

)

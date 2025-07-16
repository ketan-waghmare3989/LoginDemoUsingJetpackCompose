package com.example.logindemousingcompose.data

data class RegistrationUIState(
    var firstName : String = "",
    var lastName : String = "",
    var email : String = "" ,
    var password : String = "",


    val firstNameError : Boolean = false,
    val lastNameError : Boolean = false,
    val emailError : Boolean = false,
    val passwordError : Boolean = false

)

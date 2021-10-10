package com.victordevs.signupcompose.presentation.register

import androidx.annotation.StringRes

// Created by Victor Hernandez on 9/10/21.
// Proyect SignupCompose
//contact victoralfonso920@gmail.com

data class RegisterState(
    val successRegister: Boolean = false,
    val displayProgressBar: Boolean = false,
    @StringRes val errorMessage: Int? = null
)

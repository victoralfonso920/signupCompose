package com.victordevs.signupcompose.presentation.login

import androidx.annotation.StringRes

// Created by Victor Hernandez on 9/10/21.
// Proyect SignupCompose
//contact victoralfonso920@gmail.com

data class LoginState(
    val email:String = "",
    val password:String = "",
    val successLogin:Boolean = false,
    val displayProgressBar:Boolean = false,
    @StringRes val errorMessage:Int? = null
)

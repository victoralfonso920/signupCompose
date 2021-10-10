package com.victordevs.signupcompose.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

// Created by Victor Hernandez on 6/10/21.
// Proyect SignupCompose
//contact victoralfonso920@gmail.com

sealed class Destinations(
    val route:String,
    val arguments:List<NamedNavArgument>
){
    object Login:Destinations("login", emptyList())
    object Register:Destinations("register", emptyList())
    object Home:Destinations(
        "home",
        listOf(
            navArgument("email"){
                type = NavType.StringType
            },
            navArgument("password"){
                type = NavType.StringType
            }
        )
    )

}

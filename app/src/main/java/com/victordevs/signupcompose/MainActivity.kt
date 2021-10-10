package com.victordevs.signupcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.victordevs.signupcompose.navigation.Destinations
import com.victordevs.signupcompose.presentation.home.HomeScreen
import com.victordevs.signupcompose.presentation.login.LoginScreen
import com.victordevs.signupcompose.presentation.login.LoginState
import com.victordevs.signupcompose.presentation.login.LoginViewModel
import com.victordevs.signupcompose.presentation.register.RegisterState
import com.victordevs.signupcompose.presentation.register.RegisterViewModel
import com.victordevs.signupcompose.presentation.register.RegistrationScreen
import com.victordevs.signupcompose.ui.theme.SignupComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignupComposeTheme {
                val navController = rememberAnimatedNavController()
                BoxWithConstraints {
                    AnimatedNavHost(
                        navController = navController,
                        startDestination = Destinations.Login.route
                    ) {
                        addLogin(navController = navController)
                        addRegister(navController = navController)
                        addHome()
                    }
                }
            }
        }
    }
}


@Composable
fun Login(navController: NavController) {
    val viewModel:LoginViewModel = hiltViewModel()
    val email = viewModel.state.value.email
    val password = viewModel.state.value.password

    if(viewModel.state.value.successLogin){
        LaunchedEffect(key1 = Unit){
            navController.navigate(
                "${Destinations.Home.route}/$email/$password"
            ){
                popUpTo(Destinations.Login.route){
                    inclusive = true
                }
            }
        }
    }else{
        LoginScreen(
            state = viewModel.state.value,
            onLogin = viewModel::login,
            onNavigationToRegister = {
                navController.navigate(Destinations.Register.route)
            },
            onDismissDialog = viewModel::hideErrorDialog
        )
    }
}

@Composable
fun Registration(navController: NavController) {
    val viewModel: RegisterViewModel = hiltViewModel()
    RegistrationScreen(
        state = viewModel.state.value,
        onRegister = viewModel::register,
        onBack = {
            navController.popBackStack()
        },
        onDismissDialog = viewModel::hideErrorDialog
    )
}


@ExperimentalAnimationApi
fun NavGraphBuilder.addLogin(
    navController: NavController
) {
    composable(
        route = Destinations.Login.route,
        enterTransition = { _, _ ->
            slideInHorizontally(
                initialOffsetX = { 1000 },
                animationSpec = tween(500)
            )
        },
        exitTransition = { _, _ ->
            slideOutHorizontally(
                targetOffsetX = { -1000 },
                animationSpec = tween(500)
            )
        },
        popEnterTransition = { _, _ ->
            slideInHorizontally(
                initialOffsetX = { -1000 },
                animationSpec = tween(500)
            )
        },
        popExitTransition = { _, _ ->
            slideOutHorizontally(
                targetOffsetX = { 1000 },
                animationSpec = tween(500)
            )
        },
    ) {
        Login(navController)
    }
}

@ExperimentalAnimationApi
fun NavGraphBuilder.addRegister(
    navController: NavController
) {
    composable(
        route = Destinations.Register.route,
        enterTransition = { _, _ ->
            slideInHorizontally(
                initialOffsetX = { 1000 },
                animationSpec = tween(500)
            )
        },
        exitTransition = { _, _ ->
            slideOutHorizontally(
                targetOffsetX = { -1000 },
                animationSpec = tween(500)
            )
        },
        popEnterTransition = { _, _ ->
            slideInHorizontally(
                initialOffsetX = { -1000 },
                animationSpec = tween(500)
            )
        },
        popExitTransition = { _, _ ->
            slideOutHorizontally(
                targetOffsetX = { 1000 },
                animationSpec = tween(500)
            )
        },
    ) {
        Registration(navController = navController)
    }
}

@ExperimentalAnimationApi
fun NavGraphBuilder.addHome(
){
    composable(
        route = "${Destinations.Home.route}/{email}/{password}",
        arguments = Destinations.Home.arguments
    ){ backStackEntry ->
        val email = backStackEntry.arguments?.getString("email").orEmpty()
        val password = backStackEntry.arguments?.getString("password").orEmpty()
        HomeScreen(email,password)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    //PreviewLogin()
    PreviewRegister()
}
@Composable
fun PreviewLogin(){
    LoginScreen(
        state = LoginState(),
        onLogin = { email,pass -> },
        onNavigationToRegister = { /*TODO*/ }
    ){}
}

@Composable
fun PreviewRegister() {
    RegistrationScreen(
        state = RegisterState(),
        onRegister = { name, email,phone,password,passwordC -> }
        , onBack = { /*TODO*/ }) {

    }
}
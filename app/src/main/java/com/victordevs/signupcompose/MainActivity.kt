package com.victordevs.signupcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.victordevs.signupcompose.presentation.login.LoginScreen
import com.victordevs.signupcompose.ui.theme.SignupComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignupComposeTheme {
                // A surface container using the 'background' color from the theme
                Login()
            }
        }
    }
}

@Composable
fun Login() {
    LoginScreen()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SignupComposeTheme {
       Login()
    }
}
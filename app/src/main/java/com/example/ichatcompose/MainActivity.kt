package com.example.ichatcompose

import SignUpView
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ichatcompose.signIn.SignInView
import com.example.ichatcompose.signIn.SignInViewModel
import com.example.ichatcompose.signup.SignUpViewModel
import com.example.ichatcompose.ui.theme.IChatComposeTheme
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val signInViewModel: SignInViewModel by viewModels()
    private val signUpViewModel: SignUpViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MainView(signInViewModel = signInViewModel, signUpViewModel = signUpViewModel, register = register)
        }
    }

    private val register = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        println(result.data?.data!!)
        signUpViewModel.setProfilePhoto(result.data?.data!!)
    }


}



@Composable
fun MainView(signInViewModel: SignInViewModel, signUpViewModel: SignUpViewModel,register: ActivityResultLauncher<Intent>) {
    IChatComposeTheme {
        val navController = rememberNavController()
        NavHost(navController, startDestination = "sign_in") {
            composable(
                "sign_in",
            ) {
                SignInView(
                    signInViewModel = signInViewModel,
                    navController = navController
                )
            }
            composable("sign_up") { SignUpView(signUpViewModel = signUpViewModel,register=register) }
        }


    }
}


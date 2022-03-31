package com.example.ichatcompose.main.navigation

import androidx.navigation.NavHostController
import com.example.ichatcompose.signIn.SignFeature
import com.example.ichatcompose.signIn.SignScreen
import com.example.ichatcompose.signIn.SignUpScreen

class RouterImpl(navController: NavHostController) : Router<NavHostController> {

    override val navigationController = navController


    override fun navigateOfSignIn() {
        navigationController.navigate(SignScreen.route) {
//            popUpTo(0) // reset stack
        }
    }

    override fun navigateOfSignUp() {
        navigationController.navigate(SignUpScreen.route) {

        }
    }

}
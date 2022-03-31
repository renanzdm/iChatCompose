package com.example.ichatcompose.main.navigation

interface Router<T> {
    val navigationController: T
    fun navigateOfSignIn()
    fun navigateOfSignUp()
}
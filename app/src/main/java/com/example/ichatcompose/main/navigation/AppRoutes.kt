package com.example.ichatcompose.main.navigation

abstract class AppRoutes(private val baseRoute: String) {
    open val route: String = baseRoute
}
package com.example.ichatcompose.main.navigation


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.example.ichatcompose.main.viewModel.MainViewModel
import com.example.ichatcompose.signIn.SignFeature
import com.example.ichatcompose.signIn.signGraph
import com.google.accompanist.navigation.animation.AnimatedNavHost
import signUpGraph


@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun MainNavigation(
    viewModel: MainViewModel,
) {
    val router = viewModel.router!!
    AnimatedNavHost(
        router.navigationController,
        startDestination = SignFeature.route
    ) {
        signGraph(router)
        signUpGraph(router)
    }
}
package com.example.ichatcompose.signIn

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.example.ichatcompose.main.navigation.Router
import com.google.accompanist.navigation.animation.composable

@ExperimentalAnimationApi
fun NavGraphBuilder.signGraph(
    router: Router<*>
) {
    navigation(
        route = SignFeature.route,
        startDestination = SignScreen.route,
    ) {
        composable(SignScreen.route) {
            val viewModel = hiltViewModel<SignInViewModel>()
            //TODO Quando abre a tela e chamado
//            LaunchedEffect(auth) {
//                viewModel.useCase.auth = auth
//            }
            SignInView(signInViewModel = viewModel,router)
        }
    }
}
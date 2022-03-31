import androidx.compose.animation.ExperimentalAnimationApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.example.ichatcompose.main.navigation.Router
import com.example.ichatcompose.signIn.*
import com.example.ichatcompose.signup.SignUpViewModel
import com.google.accompanist.navigation.animation.composable

@ExperimentalAnimationApi
fun NavGraphBuilder.signUpGraph(
    router: Router<*>
) {
    navigation(
        route = SignUpFeature.route,
        startDestination = SignUpScreen.route,
    ) {
        composable(SignUpScreen.route) {
            val viewModel = hiltViewModel<SignUpViewModel>()
            //TODO Quando abre a tela e chamado
//            LaunchedEffect(auth) {
//                viewModel.useCase.auth = auth
//            }
            SignUpView( viewModel,router)
        }
    }
}
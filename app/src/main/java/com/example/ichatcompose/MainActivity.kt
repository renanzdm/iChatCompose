package com.example.ichatcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.example.ichatcompose.main.navigation.MainNavigation
import com.example.ichatcompose.main.navigation.RouterImpl
import com.example.ichatcompose.main.viewModel.MainViewModel
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.firebase.FirebaseApp.initializeApp
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeApp(this)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val navController = rememberAnimatedNavController()
            mainViewModel.router = RouterImpl(navController)
            Surface(modifier = Modifier.fillMaxSize()) {
                MainNavigation(viewModel = mainViewModel)
            }
        }

//  registerForActivityResult(
//            ActivityResultContracts.StartActivityForResult()
//        ) { result: ActivityResult ->
//            println(result.data?.data!!)
//        }

    }


}





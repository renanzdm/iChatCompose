package com.example.ichatcompose.main.viewModel

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.ichatcompose.main.navigation.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel  @Inject constructor(): ViewModel() {

    var router: Router<NavHostController>? = null
    var resultProfileIntent : ActivityResultLauncher<Intent>? = null


}
package com.example.ichatcompose.signIn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SignInViewModel  @Inject constructor(): ViewModel() {
    private val _email: MutableStateFlow<String> = MutableStateFlow("")
    var email: StateFlow<String> = _email
    private val _password: MutableStateFlow<String> = MutableStateFlow("")
    var password: StateFlow<String> = _password
    fun setEmail(email:String){
        _email.value = email

    }
    fun setPassword(password:String){
        _password.value = password

    }

}
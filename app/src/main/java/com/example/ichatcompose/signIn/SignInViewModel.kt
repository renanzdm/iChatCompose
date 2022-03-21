package com.example.ichatcompose.signIn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignInViewModel : ViewModel() {
    private val _email: MutableLiveData<String> = MutableLiveData("")
    var email: LiveData<String> = _email
    private val _password: MutableLiveData<String> = MutableLiveData("")
    var password: LiveData<String> = _password
    fun setEmail(email:String){
        _email.value = email

    }
    fun setPassword(password:String){
        _password.value = password

    }

}
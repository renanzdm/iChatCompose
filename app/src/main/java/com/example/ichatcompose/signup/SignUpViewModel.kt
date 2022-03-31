package com.example.ichatcompose.signup

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() :  ViewModel()  {
    private lateinit var auth: FirebaseAuth
    private val _email: MutableStateFlow<String> = MutableStateFlow("")
    var email: StateFlow<String> = _email
    private val _password: MutableStateFlow<String> = MutableStateFlow("")
    var password: StateFlow<String> = _password
    private val _name: MutableStateFlow<String> = MutableStateFlow("")
    var name: StateFlow<String> = _name
    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    var isLoading: StateFlow<Boolean> = _isLoading
    private val _errorText: MutableStateFlow<String> = MutableStateFlow("")
    var errorText: StateFlow<String> = _errorText
    private val _profilePhoto: MutableStateFlow<Uri> = MutableStateFlow(Uri.EMPTY)
    var profilePhoto : StateFlow<Uri> = _profilePhoto





    fun setEmail(email: String) {
        _email.value = email
    }
    fun setPassword(password: String) {
        _password.value = password
    }
    fun setName(name: String) {
        _name.value = name
    }
    fun setProfilePhoto(image: Uri) {
        _profilePhoto.value = image
    }
    fun signUp(){
        _isLoading.value = true
        auth = FirebaseAuth.getInstance()
        if (_email.value.isNotEmpty() && _password.value.isNotEmpty()) {
            auth.createUserWithEmailAndPassword(_email.value,
                _password.value
            ).addOnCompleteListener { task ->
                when {
                    task.isSuccessful -> {

                    }
                    else -> {
                        _errorText.value = task.exception.toString()
                        println(task.exception)
                    }
                }
            }
        }
        _isLoading.value = false

    }





}
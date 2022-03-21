package com.example.ichatcompose.signup

import android.net.Uri
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.scopes.ActivityRetainedScoped
import java.util.*
import kotlin.concurrent.timerTask

@ActivityRetainedScoped
class SignUpViewModel : ViewModel() {
    private lateinit var auth: FirebaseAuth
    private val _email: MutableLiveData<String> = MutableLiveData("")
    var email: LiveData<String> = _email
    private val _password: MutableLiveData<String> = MutableLiveData("")
    var password: LiveData<String> = _password
    private val _name: MutableLiveData<String> = MutableLiveData("")
    var name: LiveData<String> = _name
    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    var isLoading: LiveData<Boolean> = _isLoading
    private val _errorText: MutableLiveData<String> = MutableLiveData("")
    var errorText: LiveData<String> = _errorText
    private val _profilePhoto: MutableLiveData<Uri> = MutableLiveData(Uri.EMPTY)
    var profilePhoto : LiveData<Uri> = _profilePhoto




    fun setEmail(email: String) {
        _email.value = email
    }
    fun setPassword(password: String) {
        _password.value = password
    }
    fun setName(name: String) {
        _name.value = name
    }
    fun setProfilePhoto(path: Uri) {
        _profilePhoto.value = path
    }
    fun signUp(){
        _isLoading.value = true
        auth = FirebaseAuth.getInstance()
        if (!_email.value.isNullOrEmpty() && !_password.value.isNullOrEmpty())
        auth.createUserWithEmailAndPassword(_email.value!!, _password.value!!).addOnCompleteListener {
            if (it.isSuccessful){
            }else{
                _errorText.postValue(it.exception.toString())
                println(it.exception)
            }
        }
        _isLoading.value = false

    }


}
package com.example.shroomapp.presentation.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class LoginViewModel(application: Application) : AndroidViewModel(application)  {

    private val _errorInputLogin = MutableLiveData<Boolean>()
    val errorInputLogin: LiveData<Boolean> = _errorInputLogin
    private val _errorInputPassword = MutableLiveData<Boolean>()
    val errorInputPassword: LiveData<Boolean> = _errorInputPassword
    private val _loginSuccessed = MutableLiveData<Unit>()
    val loginSuccessed: LiveData<Unit> = _loginSuccessed

    fun login(login:String,password:String){
        if(validateInput(login,password)){
            _loginSuccessed.postValue(Unit)
        }
    }

    private fun validateInput(login:String,password:String):Boolean{
        var result = true
        if(login.isBlank() || login.isEmpty())
        {
            _errorInputLogin.postValue(true)
            result = false
        }
        if(password.isBlank() || password.isEmpty() || password.contains(" "))
        {
            _errorInputPassword.postValue(true)
            result = false
        }
        return result
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun resetErrorInputLogin(){
        _errorInputLogin.postValue(false)
    }

    fun resetErrorInputPassword(){
        _errorInputPassword.postValue(false)
    }
}
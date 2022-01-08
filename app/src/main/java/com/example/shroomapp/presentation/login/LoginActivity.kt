package com.example.shroomapp.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.ViewModelProvider
import com.example.shroomapp.R
import com.example.shroomapp.databinding.ActivityLoginBinding
import com.example.shroomapp.presentation.farms.MyFarmsActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        viewModel.loginSuccessed.observe(this){
            val pref = getSharedPreferences("ACCOUNT_DATA", MODE_PRIVATE)
            val editor = pref.edit()
            editor.putString("LOGIN",binding.textFieldLogin.text.toString())
            editor.putString("PASSWORD",binding.textFieldPassword.text.toString())
            editor.apply()
            startActivity(Intent(applicationContext,MyFarmsActivity::class.java))
        }

        login.setOnClickListener {
            val login = binding.textFieldLogin.text.toString()
            val password = binding.textFieldPassword.text.toString()
            viewModel.login(login = login, password = password)
        }

        addTextChangeListeners()
        observeInputErrorsCheck()
    }

    private fun addTextChangeListeners(){
        binding.textFieldLogin.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputLogin()
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
        binding.textFieldPassword.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputPassword()
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun observeInputErrorsCheck(){
        viewModel.errorInputLogin.observe(this){
            val message = if (it) {
                "Некорректное имя пользователя"
            } else {null}
            binding.tilFieldLogin.error = message
        }
        viewModel.errorInputPassword.observe(this){
            val message = if (it) {
                "Некорректный пароль"
            } else {null}
            binding.tilFieldPassword.error = message
        }
    }
}
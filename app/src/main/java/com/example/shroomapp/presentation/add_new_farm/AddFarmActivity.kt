package com.example.shroomapp.presentation.add_new_farm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.shroomapp.databinding.ActivityAddFarmBinding

class AddFarmActivity : AppCompatActivity() {

    private lateinit var viewModel: AddFarmViewModel
    private lateinit var binding: ActivityAddFarmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddFarmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(AddFarmViewModel::class.java)

        configureToolbarListeners()
        configureButtonsListeners()
        addTextChangeListeners()
        observeInputErrorsCheck()

        viewModel.shouldClose.observe(this){
            finish()
        }
    }

    private fun configureToolbarListeners() {
        //back button
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun configureButtonsListeners() {

        binding.saveButton.setOnClickListener {
            val name = binding.textFarmName.text.toString()
            val address = binding.textFarmAddress.text.toString()
            Log.d("TEST","Adding farm with name = $name, full address = $address")
            viewModel.createNewFarm(farmName = name, address = address)
        }

        binding.cancelButton.setOnClickListener {
            finish()
        }
    }

    private fun addTextChangeListeners(){
        binding.textFarmName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetInputName()
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
        binding.textFarmAddress.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetInputAddress()
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun observeInputErrorsCheck(){
        viewModel.errorInputName.observe(this){
            val message = if (it) {
                "Имя уже используется"
            } else {null}
            binding.tilFarmName.error = message
        }
        viewModel.errorInputAddress.observe(this){
            val message = if (it) {
                "Адрес некорректен"
            } else {null}
            binding.tilFarmAddress.error = message
        }
    }
}
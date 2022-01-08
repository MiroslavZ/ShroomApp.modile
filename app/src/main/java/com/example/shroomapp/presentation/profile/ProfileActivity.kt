package com.example.shroomapp.presentation.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shroomapp.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureToolbarListeners()
        configureButtonsListeners()
    }

    private fun configureToolbarListeners() {
        //back button
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun configureButtonsListeners() {

        binding.saveButton.setOnClickListener {
            //connecting to created farm, getting data
            finish()
        }

        binding.cancelButton.setOnClickListener {
            finish()
        }
    }
}
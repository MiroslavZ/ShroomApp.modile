package com.example.shroomapp.presentation.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shroomapp.databinding.ActivityMainBinding
import com.example.shroomapp.presentation.add_new_farm.AddFarmActivity
import com.example.shroomapp.presentation.farm_notifications.NotificationsActivity
import com.example.shroomapp.presentation.farm_sensors.SensorsActivity
import com.example.shroomapp.presentation.farms.MyFarmsActivity
import com.example.shroomapp.presentation.login.LoginActivity
import com.example.shroomapp.presentation.profile.ProfileActivity
import com.example.shroomapp.presentation.sensor_stats.SensorStatsActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonALogin.setOnClickListener {
            startActivity(Intent(applicationContext, LoginActivity::class.java))
        }
        binding.buttonAFarms.setOnClickListener {
            startActivity(Intent(applicationContext, MyFarmsActivity::class.java))
        }
        binding.buttonASensors.setOnClickListener {
            startActivity(Intent(applicationContext, SensorsActivity::class.java))
        }
        binding.buttonASensorStats.setOnClickListener {
            startActivity(Intent(applicationContext, SensorStatsActivity::class.java))
        }
        binding.buttonNotifications.setOnClickListener {
            startActivity(Intent(applicationContext, NotificationsActivity::class.java))
        }
        binding.buttonAAddFarm.setOnClickListener {
            startActivity(Intent(applicationContext, AddFarmActivity::class.java))
        }
        binding.buttonProfile.setOnClickListener {
            startActivity(Intent(applicationContext, ProfileActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        val pref = getSharedPreferences("ACCOUNT_DATA", MODE_PRIVATE)
        val login = pref.getString("LOGIN",null)
        val password = pref.getString("PASSWORD",null)
        if(login != null && password != null){
            startActivity(Intent(applicationContext, MyFarmsActivity::class.java))
        } else {
            startActivity(Intent(applicationContext, LoginActivity::class.java))
        }
    }
}
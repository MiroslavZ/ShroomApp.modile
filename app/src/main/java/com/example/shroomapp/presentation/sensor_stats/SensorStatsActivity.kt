package com.example.shroomapp.presentation.sensor_stats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shroomapp.databinding.ActivitySensorStatsBinding

class SensorStatsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySensorStatsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySensorStatsBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
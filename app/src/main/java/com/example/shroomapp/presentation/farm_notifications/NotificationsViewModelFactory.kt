package com.example.shroomapp.presentation.farm_notifications

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shroomapp.presentation.farm_sensors.SensorsViewModel

class NotificationsViewModelFactory(
    private val application: Application,
    private val farmId:Int,private val sensorId:Int?): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NotificationsViewModel(application = application, farmId = farmId, sensorId = sensorId) as T
    }
}
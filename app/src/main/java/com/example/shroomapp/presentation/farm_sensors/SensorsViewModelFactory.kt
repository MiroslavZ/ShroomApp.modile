package com.example.shroomapp.presentation.farm_sensors

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SensorsViewModelFactory(
    private val application: Application,
    private val farmId:Int):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SensorsViewModel(application = application, farmId = farmId) as T
    }
}
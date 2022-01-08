package com.example.shroomapp.presentation.farm_notifications.dialog

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DialogViewModelFactory (
    private val application: Application): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DialogViewModel(application = application) as T
    }
}
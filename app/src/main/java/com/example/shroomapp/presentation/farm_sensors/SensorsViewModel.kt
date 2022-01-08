package com.example.shroomapp.presentation.farm_sensors

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shroomapp.data.repository.RepositoryImpl
import com.example.shroomapp.domain.usecases.DeleteFarmUseCase
import com.example.shroomapp.domain.usecases.GetSensorsListUseCase
import com.example.shroomapp.domain.usecases.LoadDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SensorsViewModel(application: Application,private val farmId:Int) : AndroidViewModel(application) {

    private val repository = RepositoryImpl(application)
    private val getSensorsListUseCase = GetSensorsListUseCase(repository)
    private val deleteFarmUseCase = DeleteFarmUseCase(repository)

    private val _shouldClose = MutableLiveData<Unit>()
    val shouldClose: LiveData<Unit> = _shouldClose

    val sensorsList = getSensorsListUseCase(farmId = farmId)

    fun deleteFarm(){
        viewModelScope.launch(Dispatchers.IO) {
            deleteFarmUseCase.invoke(farmId = farmId)
        }
        _shouldClose.postValue(Unit)
    }


}
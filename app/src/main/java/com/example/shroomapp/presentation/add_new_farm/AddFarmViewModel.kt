package com.example.shroomapp.presentation.add_new_farm

import android.app.Application
import android.util.Log
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shroomapp.data.database.models.FarmDbModel
import com.example.shroomapp.data.repository.RepositoryImpl
import com.example.shroomapp.domain.usecases.CreateFarmUseCase
import com.example.shroomapp.domain.usecases.GetFarmsListUseCase
import com.example.shroomapp.domain.usecases.LoadDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddFarmViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RepositoryImpl(application)
    private val createFarmUseCase = CreateFarmUseCase(repository)
    private val getFarmsListUseCase = GetFarmsListUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    private val farms = getFarmsListUseCase.invoke()

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean> = _errorInputName
    private val _errorInputAddress = MutableLiveData<Boolean>()
    val errorInputAddress: LiveData<Boolean> = _errorInputAddress
    private val _shouldClose = MutableLiveData<Unit>()
    val shouldClose: LiveData<Unit> = _shouldClose

    fun createNewFarm(farmName: String, address: String) {
        if(validateInput(farmName,address)){
            Log.d("TEST","VIEWMODEL - Adding farm with name = $farmName and address = $address")
            loadDataForCreatedFarm()
            viewModelScope.launch(Dispatchers.IO){
                createFarmUseCase.invoke(farmName = farmName, address = address)
            }
            Log.d("TEST","VIEWMODEL - Closing the activity")
            _shouldClose.postValue(Unit)
        }
    }

    private fun validateInput(farmName: String, address: String): Boolean {
        var result = true
        if (farms.value?.any { it.name == farmName } == true) {
            result = false
            _errorInputName.postValue(true)
        }
        if (address.isNullOrEmpty()) {
            result = false
            _errorInputAddress.postValue(true)
        }
        return result
    }

    public fun resetInputName(){
        _errorInputName.postValue(false)
    }

    public fun resetInputAddress(){
        _errorInputAddress.postValue(false)
    }

    private fun loadDataForCreatedFarm(){
        loadDataUseCase()
    }
}
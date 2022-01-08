package com.example.shroomapp.presentation.farms

import android.app.Application
import androidx.lifecycle.*
import com.example.shroomapp.data.repository.RepositoryImpl
import com.example.shroomapp.domain.usecases.GetFarmsListUseCase

class MyFarmsViewModel(application: Application) : AndroidViewModel(application)  {

    private val repository = RepositoryImpl(application)
    private val getFarmsListUseCase = GetFarmsListUseCase(repository)

    val farmsList = getFarmsListUseCase()
}
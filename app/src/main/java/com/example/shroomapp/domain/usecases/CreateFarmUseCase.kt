package com.example.shroomapp.domain.usecases

import com.example.shroomapp.data.database.models.FarmDbModel
import com.example.shroomapp.domain.Repository

class CreateFarmUseCase(private val repository: Repository) {

    suspend operator fun invoke(farmName:String,address:String)
    = repository.createFarm(farmName = farmName, fullAddress = address)
}
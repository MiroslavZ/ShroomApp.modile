package com.example.shroomapp.domain.usecases

import com.example.shroomapp.domain.Repository

class DeleteFarmUseCase(private val repository: Repository) {

    suspend operator fun invoke(farmId:Int) = repository.deleteFarm(farmId = farmId)
}
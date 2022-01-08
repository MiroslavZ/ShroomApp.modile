package com.example.shroomapp.domain.usecases

import com.example.shroomapp.domain.Repository

class GetSensorsListUseCase(private val repository: Repository) {

    operator fun invoke(farmId:Int) = repository.getSensorsList(farmId = farmId)
}
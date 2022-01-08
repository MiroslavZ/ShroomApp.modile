package com.example.shroomapp.domain.usecases

import com.example.shroomapp.domain.Repository

class GetFarmNotificationsListUseCase(private val repository: Repository) {

    operator fun invoke(farmId:Int) = repository.getFarmNotifications(farmId = farmId)
}
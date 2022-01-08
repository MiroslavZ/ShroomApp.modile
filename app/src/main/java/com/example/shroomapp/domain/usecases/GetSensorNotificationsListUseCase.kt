package com.example.shroomapp.domain.usecases

import com.example.shroomapp.domain.Repository

class GetSensorNotificationsListUseCase(private val repository: Repository) {

    operator fun invoke(sensorId:Int) = repository.getSensorNotifications(sensorId = sensorId)
}
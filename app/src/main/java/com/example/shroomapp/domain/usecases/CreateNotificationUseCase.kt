package com.example.shroomapp.domain.usecases

import com.example.shroomapp.domain.Repository

class CreateNotificationUseCase (private val repository: Repository) {

    suspend operator fun invoke(condition:String,threshold:Double,sensorId:Int,farmId:Int) =
        repository.createNotification(condition, threshold, sensorId,farmId)
}
package com.example.shroomapp.domain.usecases

import com.example.shroomapp.domain.Repository

class EditNotificationUseCase(private val repository: Repository) {

    operator fun invoke(notificationId:Int,sensorId:Int,condition:String,threshold:Double)
    = repository.editNotification(notificationId, sensorId, condition, threshold)
}
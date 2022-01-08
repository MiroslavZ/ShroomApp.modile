package com.example.shroomapp.domain.usecases

import com.example.shroomapp.domain.Repository

class DeleteNotificationUseCase(private val repository: Repository) {

    suspend operator fun invoke(notificationId:Int)
    = repository.deleteNotification(notificationId = notificationId)
}
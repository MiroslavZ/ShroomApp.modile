package com.example.shroomapp.domain.usecases

import com.example.shroomapp.domain.Repository

class GetNotificationUseCase (private val repository: Repository) {

    operator fun invoke(notificationId:Int) = repository.getNotification(id = notificationId)
}
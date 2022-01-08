package com.example.shroomapp.presentation.farm_notifications

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shroomapp.data.repository.RepositoryImpl
import com.example.shroomapp.domain.Notification
import com.example.shroomapp.domain.NotificationCondition
import com.example.shroomapp.domain.Sensor
import com.example.shroomapp.domain.usecases.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotificationsViewModel
    (application: Application,val farmId:Int,val sensorId:Int?) : AndroidViewModel(application) {

    private val repository = RepositoryImpl(application)
    private val getFarmNotificationsListUseCase = GetFarmNotificationsListUseCase(repository)
    private val deleteNotificationUseCase = DeleteNotificationUseCase(repository)

    val notifications = getFarmNotificationsListUseCase(farmId = farmId)

    fun deleteNotification(notificationId:Int){
        viewModelScope.launch(Dispatchers.IO) {
            deleteNotificationUseCase.invoke(notificationId = notificationId)
        }
    }
}
package com.example.shroomapp.presentation.farm_notifications.dialog

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.shroomapp.data.repository.RepositoryImpl
import com.example.shroomapp.domain.Notification
import com.example.shroomapp.domain.NotificationCondition
import com.example.shroomapp.domain.usecases.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DialogViewModel
    (application: Application) : AndroidViewModel(application) {

    private val repository = RepositoryImpl(application)
    private val createNotificationUseCase = CreateNotificationUseCase(repository)
    private val editNotificationUseCase = EditNotificationUseCase(repository)
    private val getNotificationUseCase = GetNotificationUseCase(repository)

    fun getNotificationInfo(notificationId:Int):Notification{
        return getNotificationUseCase.invoke(notificationId)
    }

    fun createNotification(condition: NotificationCondition, threshold:Double, sensorId:Int,farmId:Int){
        viewModelScope.launch(Dispatchers.IO) {
            createNotificationUseCase.invoke(condition = condition.toString(),threshold, sensorId,farmId)
        }
    }

    fun editNotification(notificationId:Int,sensorId:Int,condition:String,threshold:Double){
        viewModelScope.launch(Dispatchers.IO) {
            editNotificationUseCase(notificationId, sensorId, condition, threshold)
        }
    }
}
package com.example.shroomapp.domain

import androidx.lifecycle.LiveData
import com.example.shroomapp.data.database.models.FarmDbModel
import com.example.shroomapp.data.database.models.NotificationDbModel

interface Repository {


    suspend fun createFarm(farmName: String,fullAddress:String)

    suspend fun createNotification(condition:String,threshold: Double,sensorId: Int,farmId:Int)

    suspend fun deleteFarm(farmId: Int)

    fun deleteNotification(notificationId: Int)

    fun editNotification(
        notificationId: Int,
        sensorId: Int,
        condition: String,
        threshold: Double
    )

    fun getFarmNotifications(farmId: Int): LiveData<List<Notification>>

    fun getFarmsList(): LiveData<List<Farm>>

    fun getSensorNotifications(sensorId: Int): LiveData<List<Notification>>

    fun getSensorsList(farmId: Int): LiveData<List<Sensor>>

    fun renameFarm(farmId:Int, newName: String)

    fun loadData()

    fun getNotification(id:Int):Notification
}
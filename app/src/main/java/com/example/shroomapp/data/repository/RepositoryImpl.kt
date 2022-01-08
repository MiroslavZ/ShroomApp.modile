package com.example.shroomapp.data.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.shroomapp.data.database.AppDatabase
import com.example.shroomapp.data.database.models.FarmDbModel
import com.example.shroomapp.data.database.models.NotificationDbModel
import com.example.shroomapp.data.database.models.SensorDbModel
import com.example.shroomapp.data.mapper.Mapper
import com.example.shroomapp.data.workers.RefreshDataWorker
import com.example.shroomapp.domain.*
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.sin

class RepositoryImpl(private val application: Application): Repository {

    private val dao = AppDatabase.getInstance(application).FarmDao()
    private val mapper = Mapper()

    override suspend fun createFarm(farmName: String, fullAddress:String) {
        val farm = FarmDbModel(null, name = farmName, fullAddress = fullAddress)
        dao.createFarm(farm = farm)
    }

    override suspend fun createNotification(condition:String,threshold: Double,sensorId: Int,farmId:Int) {
        val notification = NotificationDbModel(
            condition = condition,
            threshold = threshold,
            ownerSensorId = sensorId,
            ownerFarmId = farmId
        )
        dao.createNotification(notification)
    }

    override suspend fun deleteFarm(farmId: Int) {
        dao.deleteFarm(farmId = farmId)
    }

    override fun deleteNotification(notificationId: Int) {
        dao.deleteNotification(notificationId = notificationId)
    }

    override fun editNotification(
        notificationId: Int,
        sensorId: Int,
        condition: String,
        threshold: Double
    ) {
        val notification = dao.getNotificationById(notificationId = notificationId)
        notification.ownerSensorId = sensorId
        notification.condition = condition
        notification.threshold = threshold
        dao.editNotification(notification = notification)
    }

    override fun getFarmNotifications(farmId: Int): LiveData<List<Notification>> {
        return Transformations.map(dao.getFarmNotifications(farmId = farmId)){
            it.map {
                mapper.mapNotificationDbModelToEntity(it,dao.getSensorById(it.ownerSensorId).name)
            }
        }
    }

    override fun getFarmsList(): LiveData<List<Farm>> {
        return Transformations.map(dao.getAllFarms()){
            it.map {
                mapper.mapFarmDbModelToEntity(it)
            }
        }
    }

    override fun getSensorNotifications(sensorId: Int): LiveData<List<Notification>> {
        val sensor = dao.getSensorById(sensorId = sensorId)
        return Transformations.map(dao.getSensorNotifications(sensorId = sensorId)){
            it.map {
                mapper.mapNotificationDbModelToEntity(it,sensor.name)
            }
        }
    }

    override fun getSensorsList(farmId: Int): LiveData<List<Sensor>> {
        return Transformations.map(dao.getFarmSensors(farmId = farmId)){
            it.map {
                mapper.mapSensorDbModelToEntity(it)
            }
        }
    }

    override fun renameFarm(farmId: Int, newName: String) {
        val farm = dao.getFarmById(farmId = farmId)
        farm.name = newName
        dao.renameFarm(farm)
    }

    override fun loadData(){
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.makeRequest()
        )
    }

    override fun getNotification(id:Int):Notification{
        val notificationFromDb = dao.getNotificationById(notificationId = id)
        val owner = dao.getSensorById(notificationFromDb.ownerSensorId)
        return mapper.mapNotificationDbModelToEntity(notificationFromDb,owner.name)
    }

    private var sawCount = -1
    private fun sawSimulator():Int{
        if(sawCount<10){
            sawCount++
            return sawCount
        } else{
            sawCount = 0
            return sawCount
        }
    }

    private var sinMult = -10
    private var angle = 0
    private fun sinSimulator():Double{
        sinMult+=10
        return sin(angle/57.2958)
    }

    private val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
    private fun timeSimulator(): String {
        return sdf.format(Date())
    }

    private var vibrStart = false
    private fun vibrSimulator():Boolean{
        vibrStart = !vibrStart
        return vibrStart
    }

    private val dConst = false
    private fun digitConstSimulator():Boolean{
        return dConst
    }

    private val aConst = 0.5
    private fun analogConstSimulator():Double{
        return aConst
    }

    private val poolD = 0.5
    private fun poolDeviceSimulator():Double{
        return poolD
    }
}
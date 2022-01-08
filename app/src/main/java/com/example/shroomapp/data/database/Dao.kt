package com.example.shroomapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.example.shroomapp.data.database.models.FarmDbModel
import com.example.shroomapp.data.database.models.NotificationDbModel
import com.example.shroomapp.data.database.models.SensorDbModel
import com.example.shroomapp.data.database.models.SensorHistoricalDataDbModel
import com.example.shroomapp.domain.Notification
import com.example.shroomapp.domain.NotificationCondition

@Dao
interface Dao {

    @Insert
    fun createFarm(farm: FarmDbModel)

    @Insert
    fun createNotification(notification: NotificationDbModel)

    @Query("DELETE FROM farms WHERE id == :farmId")
    fun deleteFarm(farmId: Int)

    @Query("DELETE FROM notifications WHERE id == :notificationId")
    fun deleteNotification(notificationId: Int)

    @Update
    fun editNotification(notification: NotificationDbModel)

    @Query("SELECT * FROM notifications WHERE ownerFarmId == :farmId")
    fun getFarmNotifications(farmId: Int): LiveData<List<NotificationDbModel>>

    @Query("SELECT * FROM farms ORDER BY name")
    fun getAllFarms(): LiveData<List<FarmDbModel>>

    @Query("SELECT * FROM notifications WHERE ownerSensorId == :sensorId")
    fun getSensorNotifications(sensorId: Int): LiveData<List<NotificationDbModel>>

    @Query("SELECT * FROM sensors WHERE ownerFarmId == :farmId ORDER BY id")
    fun getFarmSensors(farmId: Int): LiveData<List<SensorDbModel>>

    @Update
    fun renameFarm(farm: FarmDbModel)

    @Query("SELECT * FROM notifications WHERE id == :notificationId LIMIT 1")
    fun getNotificationById(notificationId: Int): NotificationDbModel

    @Query("SELECT * FROM sensors WHERE id == :sensorId LIMIT 1")
    fun getSensorById(sensorId: Int): SensorDbModel

    @Query("SELECT * FROM farms WHERE id ==:farmId LIMIT 1")
    fun getFarmById(farmId:Int):FarmDbModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSensorsList(sensorsList:List<SensorDbModel>)

    @Query("DELETE FROM sensors WHERE ownerFarmId==:farmId")
    fun clearSensorsList(farmId:Int)

    @Query("SELECT * FROM sensors WHERE ownerFarmId==:farmId AND name==:sensorName LIMIT 1")
    fun getSensorFromFarmByName(farmId:Int,sensorName:String):SensorDbModel

    @Query("SELECT * FROM measurement_history WHERE sensorId==:sensorId")
    fun getMeasurementsBySensor(sensorId: Int):LiveData<List<SensorHistoricalDataDbModel>>
}
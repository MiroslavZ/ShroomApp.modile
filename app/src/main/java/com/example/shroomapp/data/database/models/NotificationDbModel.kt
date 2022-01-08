package com.example.shroomapp.data.database.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "notifications")
data class NotificationDbModel (
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    var condition:String,
    var threshold:Double,
    val ownerFarmId:Int,
    var ownerSensorId:Int
)
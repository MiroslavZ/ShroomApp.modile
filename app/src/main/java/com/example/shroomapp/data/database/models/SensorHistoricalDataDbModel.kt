package com.example.shroomapp.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "measurement_history")
data class SensorHistoricalDataDbModel (
    @PrimaryKey
    val id:Int = 0,
    val sensorId:Int,
    val measuredValue:Double,
    val measurementTimestamp: Long
        )
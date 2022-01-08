package com.example.shroomapp.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sensors")
data class SensorDbModel (
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val name:String,
    val value:String,
    val ownerFarmId:Int
        )
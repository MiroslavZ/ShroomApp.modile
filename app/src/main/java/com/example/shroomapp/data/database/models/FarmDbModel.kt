package com.example.shroomapp.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "farms")
data class FarmDbModel(
    @PrimaryKey
    val id: Int? = 0,
    var name:String,
    val fullAddress:String
        )
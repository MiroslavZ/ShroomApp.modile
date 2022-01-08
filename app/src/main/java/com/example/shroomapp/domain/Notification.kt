package com.example.shroomapp.domain

data class Notification (
    val id:Int,
    val sensorId:Int,
    var sensorName:String,
    var condition: NotificationCondition,
    var threshold:Double
        )
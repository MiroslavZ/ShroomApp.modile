package com.example.shroomapp.data.mapper

import com.example.shroomapp.data.database.models.FarmDbModel
import com.example.shroomapp.data.database.models.NotificationDbModel
import com.example.shroomapp.data.database.models.SensorDbModel
import com.example.shroomapp.domain.Farm
import com.example.shroomapp.domain.Notification
import com.example.shroomapp.domain.NotificationCondition
import com.example.shroomapp.domain.Sensor

class Mapper {

    fun mapFarmDbModelToEntity(model:FarmDbModel):Farm{
        var i = 0
        if(model.id!=null)
            i=model.id
        return Farm(
            id = i,
            name = model.name
        )
    }

    fun mapNotificationDbModelToEntity(model:NotificationDbModel,sensorName:String):Notification{
        return Notification(
            id = model.id,
            sensorId = model.ownerSensorId,
            sensorName = sensorName,
            condition = mapStringToCondition(model.condition),
            threshold = model.threshold
        )
    }

    fun mapSensorDbModelToEntity(model:SensorDbModel):Sensor{
        return Sensor(
            id = model.id,
            name = model.name,
            currentValue = model.value
        )
    }

    private fun mapStringToCondition(condition:String):NotificationCondition{
        return if (condition=="=="){
            NotificationCondition.IS_EQUAL
        } else if (condition == "<"){
            NotificationCondition.IS_LESS
        } else {
            NotificationCondition.IS_GREATER
        }
    }
}
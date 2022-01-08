package com.example.shroomapp.data.workers

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.example.shroomapp.data.database.AppDatabase
import com.example.shroomapp.data.database.models.SensorDbModel
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.sin
import kotlin.random.Random

class RefreshDataWorker(context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {

    private val dao = AppDatabase.getInstance(context).FarmDao()

    override suspend fun doWork(): Result {
        while (true) {
            val count = dao.getAllFarms().value?.size ?: 1
            for (i in 1..count) {
                val sensors = listOf(
                    SensorDbModel(name = "LB-760A", value = Random.nextDouble(70.0,75.0).toString(), ownerFarmId = i),
                    SensorDbModel(name = "LB-762-IO", value = Random.nextDouble(25.0,27.0).toString(), ownerFarmId = i),
                    SensorDbModel(name = "LB-762", value = false.toString(), ownerFarmId = i),
                    SensorDbModel(name = "LB-762A",value = 3.toString(),ownerFarmId = i),
                    SensorDbModel(name = "LB-762L",value = Random.nextDouble(0.08,0.1).toString(),ownerFarmId = i),
//                    SensorDbModel(name = "Saw", value = sawSimulator().toString(), ownerFarmId = i),
//                    SensorDbModel(name = "Sin", value = sinSimulator().toString(), ownerFarmId = i),
//                    SensorDbModel(name = "Time", value = timeSimulator(), ownerFarmId = i),
//                    SensorDbModel(name = "Vibrator",value = vibrSimulator().toString(),ownerFarmId = i),
//                    SensorDbModel(name = "DigitConst",value = digitConstSimulator().toString(),ownerFarmId = i),
//                    SensorDbModel(name = "AnalogConst",value = analogConstSimulator().toString(),ownerFarmId = i),
//                    SensorDbModel(name = "PoolDevice",value = poolDeviceSimulator().toString(),ownerFarmId = i)
                )
                dao.clearSensorsList(farmId = i)
                dao.insertSensorsList(sensorsList = sensors)
            }
            delay(1000)
        }
    }

    companion object {
        const val NAME = "RefreshDataWorker"

        fun makeRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<RefreshDataWorker>().build()
        }
    }

    private var sawCount = -1
    private fun sawSimulator(): Int {
        if (sawCount < 10) {
            sawCount++
            return sawCount
        } else {
            sawCount = 0
            return sawCount
        }
    }

    private var sinMult = -10
    private var angle = 0
    private fun sinSimulator(): Double {
        sinMult += 10 + Random.nextInt(-5, 5)
        return sin(angle / 57.2958)
    }

    private val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
    private fun timeSimulator(): String {
        return sdf.format(Date())
    }

    private var vibrStart = false
    private fun vibrSimulator(): Boolean {
        vibrStart = !vibrStart
        return vibrStart
    }

    private val dConst = false
    private fun digitConstSimulator(): Boolean {
        return dConst
    }

    private val aConst = 0.5
    private fun analogConstSimulator(): Double {
        return aConst
    }

    private val poolD = 0.5
    private fun poolDeviceSimulator(): Double {
        return poolD
    }
}
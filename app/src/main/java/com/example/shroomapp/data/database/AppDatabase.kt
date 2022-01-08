package com.example.shroomapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shroomapp.data.database.models.FarmDbModel
import com.example.shroomapp.data.database.models.NotificationDbModel
import com.example.shroomapp.data.database.models.SensorDbModel
import com.example.shroomapp.data.database.models.SensorHistoricalDataDbModel

@Database(entities =
[FarmDbModel::class,SensorDbModel::class,NotificationDbModel::class,SensorHistoricalDataDbModel::class],
    version = 3, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    companion object{
        private var db: AppDatabase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK){
                //если базы нет, создаем и возвращаем
                db?.let {
                    return it
                }
                val instance = Room
                    .databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                    .fallbackToDestructiveMigration() //старые данные удалятся (иначе нужны миграции!)
                    .build()
                db = instance
                return instance
            }
        }
    }

    abstract fun FarmDao(): Dao
}
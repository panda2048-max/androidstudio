package com.example.promedio.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [modelOdontologia::class], version = 1, exportSchema = false)
abstract class OdontologiaDatabase : RoomDatabase() {

    abstract fun odontologiaDao(): OdontologiaDao

    companion object {
        @Volatile
        private var INSTANCE: OdontologiaDatabase? = null

        fun getDatabase(context: Context): OdontologiaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    OdontologiaDatabase::class.java,
                    "odontologia_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

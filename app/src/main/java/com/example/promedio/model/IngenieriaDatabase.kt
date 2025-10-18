package com.example.promedio.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [modelIngenieria::class], version = 1, exportSchema = false)
abstract class IngenieriaDatabase : RoomDatabase() {

    abstract fun ingenieriaDao(): IngenieriaDao

    companion object {
        @Volatile
        private var INSTANCE: IngenieriaDatabase? = null

        fun getDatabase(context: Context): IngenieriaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    IngenieriaDatabase::class.java,
                    "ingenieria_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

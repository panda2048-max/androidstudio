package com.example.promedio.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


// definimos la base de datos y la conectamos a nuestras clases creadas

@Database(entities = [modelIngenieria::class], version = 1, exportSchema = false)
abstract class IngenieriaDatabase : RoomDatabase() {

    //creamos metodo para que nos reciba los modal de Dao
    abstract fun ingenieriaDao(): IngenieriaDao

    companion object {

        // creamos variable para que almacene nuestros datos
        @Volatile
        private var INSTANCE: IngenieriaDatabase? = null

        // creamos funcion para devolver la instancia
        fun getDatabase(context: Context): IngenieriaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    IngenieriaDatabase::class.java,
                    "ingenieria_db"
                ).build()
                // asiganmos la variable intancia para facilitar futuros llamados
                INSTANCE = instance
                instance
            }
        }
    }
}

package com.example.promedio.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// definimos la base de datos y la conectamos a nuestras clases creadas
@Database(entities = [modelOdontologia::class], version = 1, exportSchema = false)
abstract class OdontologiaDatabase : RoomDatabase() {

    //creamos metodo para que nos reciba los modal de Dao
    abstract fun odontologiaDao(): OdontologiaDao

    companion object {

        // creamos variable para que almacene nuestros datos
        @Volatile
        private var INSTANCE: OdontologiaDatabase? = null

        // creamos funcion para devolver la instancia
        fun getDatabase(context: Context): OdontologiaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    OdontologiaDatabase::class.java,
                    "odontologia_db"
                ).build()

                // asiganmos la variable intancia para facilitar futuros llamados
                INSTANCE = instance
                instance
            }
        }
    }
}


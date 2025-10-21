package com.example.promedio.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// definimos la base de datos y la conectamos a nuestras clases creadas
@Database(
    entities = [Login::class, modelIngenieria::class, modelOdontologia::class],
    version = 1,
    exportSchema = false
)
//creamos metodo para que nos reciba los modal de Dao
abstract class AppDatabase : RoomDatabase() {

    // Se declaran los DAO disponibles en la aplicación.
    abstract fun loginDao(): LoginDao
    abstract fun ingenieriaDao(): IngenieriaDao
    abstract fun odontologiaDao(): OdontologiaDao

    companion object {

        // creamos variable para que almacene nuestros datos
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // creamos funcion para devolver la instancia
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                // Crea la base de datos con nombre 'promedio_db'
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "promedio_db"
                ).build()
                // asiganmos la variable intancia para facilitar futuros llamados
                INSTANCE = instance
                instance
            }
        }
    }
}


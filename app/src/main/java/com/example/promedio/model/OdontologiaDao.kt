package com.example.promedio.model

import androidx.room.*

@Dao
interface OdontologiaDao {
    @Query("SELECT * FROM odontologias")
    suspend fun getAll(): List<modelOdontologia>

    @Insert
    suspend fun insert(notas: modelOdontologia)

    @Update
    suspend fun update(notas: modelOdontologia)

    @Delete
    suspend fun delete(notas: modelOdontologia)
}

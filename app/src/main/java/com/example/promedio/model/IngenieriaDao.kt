package com.example.promedio.model

import androidx.room.*

@Dao
interface IngenieriaDao{
    @Query("SELECT * FROM ingenierias")
    suspend fun  getAll(): List<modelIngenieria>

    @Insert
    suspend fun insert(notas : modelIngenieria)

    @Update
    suspend fun update(notas: modelIngenieria)

    @Delete
    suspend fun delete(notas: modelIngenieria)
}
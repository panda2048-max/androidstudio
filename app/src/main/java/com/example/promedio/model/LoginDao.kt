package com.example.promedio.model

import androidx.room.*

@Dao
interface LoginDao{
    @Query("SELECT * FROM logins LIMIT 3")
    suspend fun  getAll(): List<Login>

    @Insert
    suspend fun insert(usuario : Login)

    @Update
    suspend fun update(usuario: Login)

    @Delete
    suspend fun delete(usuario: Login)
}
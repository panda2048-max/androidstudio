package com.example.promedio.model

import androidx.room.*

// Interface DAO (Data Access Object) para la entidad 'Login'.
// Define las operaciones de acceso a la base de datos relacionadas con la tabla 'login'.
@Dao
interface LoginDao{

    // Consulta todos los registros de la tabla 'logins'.
    @Query("SELECT * FROM logins LIMIT 3")
    suspend fun  getAll(): List<Login>

    // Inserta un nuevo registro en la tabla 'logins'.
    // Si el objeto ya existe (por clave primaria duplicada), Room lanzará una excepción.
    @Insert
    suspend fun insert(usuario : Login)

    // Actualiza un registro existente en la tabla 'logins'.
    // Room identificará qué registro actualizar mediante la clave primaria del objeto.
    @Update
    suspend fun update(usuario: Login)

    // Elimina un registro específico de la tabla 'logins'.
    // También se basa en la clave primaria del objeto para saber cuál eliminar.
    @Delete
    suspend fun delete(usuario: Login)
}
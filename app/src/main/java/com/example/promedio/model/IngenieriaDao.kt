package com.example.promedio.model

import androidx.room.*

// Interface DAO (Data Access Object) para la entidad 'modelIngenieria'.
// Define las operaciones de acceso a la base de datos relacionadas con la tabla 'ingenierias'.
@Dao
interface IngenieriaDao {

    // Consulta todos los registros de la tabla 'ingenierias'.
    @Query("SELECT * FROM ingenierias")
    suspend fun getAll(): List<modelIngenieria>

    // Inserta un nuevo registro en la tabla 'ingenierias'.
    // Si el objeto ya existe (por clave primaria duplicada), Room lanzará una excepción.
    @Insert
    suspend fun insert(notas: modelIngenieria)

    // Actualiza un registro existente en la tabla 'ingenierias'.
    // Room identificará qué registro actualizar mediante la clave primaria del objeto.
    @Update
    suspend fun update(notas: modelIngenieria)

    // Elimina un registro específico de la tabla 'ingenierias'.
    // También se basa en la clave primaria del objeto para saber cuál eliminar.
    @Delete
    suspend fun delete(notas: modelIngenieria)
}

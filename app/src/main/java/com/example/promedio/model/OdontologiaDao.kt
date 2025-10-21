package com.example.promedio.model

import androidx.room.*


//Interface DAO (Data Access Object) para la entidad 'modelOdontologia'.
// Define las operaciones de acceso a la base de datos relacionadas con la tabla 'odontologia'.
@Dao
interface OdontologiaDao {

    // Consulta todos los registros de la tabla 'odontologia'.
    @Query("SELECT * FROM odontologias")
    suspend fun getAll(): List<modelOdontologia>

    // Inserta un nuevo registro en la tabla 'odontologia'.
    // Si el objeto ya existe (por clave primaria duplicada), Room lanzará una excepción.
    @Insert
    suspend fun insert(notas: modelOdontologia)

    // Actualiza un registro existente en la tabla 'odontologia'.
    // Room identificará qué registro actualizar mediante la clave primaria del objeto.

    @Update
    suspend fun update(notas: modelOdontologia)

    // Elimina un registro específico de la tabla 'odontologia'.
    // También se basa en la clave primaria del objeto para saber cuál eliminar.
    @Delete
    suspend fun delete(notas: modelOdontologia)
}

package com.example.promedio.repository

import com.example.promedio.model.modelOdontologia
import com.example.promedio.model.OdontologiaDao

// creamos el repositorio donde nos encargaremos de recibir
// insertar, actualizar y eliminar de la base de datos trabajando en conjunto con Dao
class OdontologiaRepository(private val dao: OdontologiaDao) {

    suspend fun getAll() = dao.getAll()
    suspend fun insert(notas: modelOdontologia) = dao.insert(notas)
    suspend fun update(notas: modelOdontologia) = dao.update(notas)
    suspend fun delete(notas: modelOdontologia) = dao.delete(notas)
}

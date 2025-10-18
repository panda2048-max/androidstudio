package com.example.promedio.repository

import com.example.promedio.model.modelOdontologia
import com.example.promedio.model.OdontologiaDao

class OdontologiaRepository(private val dao: OdontologiaDao) {

    suspend fun getAll() = dao.getAll()
    suspend fun insert(notas: modelOdontologia) = dao.insert(notas)
    suspend fun update(notas: modelOdontologia) = dao.update(notas)
    suspend fun delete(notas: modelOdontologia) = dao.delete(notas)
}

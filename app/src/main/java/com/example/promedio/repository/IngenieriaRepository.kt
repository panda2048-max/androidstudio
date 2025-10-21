package com.example.promedio.repository

import com.example.promedio.model.modelIngenieria
import com.example.promedio.model.IngenieriaDao

// creamos el repositorio donde nos encargaremos de recibir
// insertar, actualizar y eliminar de la base de datos trabajando en conjunto con Dao
class IngenieriaRepository(private val dao: IngenieriaDao) {

    suspend fun getAll() = dao.getAll()
    suspend fun insert(notas: modelIngenieria) = dao.insert(notas)
    suspend fun update(notas: modelIngenieria) = dao.update(notas)
    suspend fun delete(notas: modelIngenieria) = dao.delete(notas)
}
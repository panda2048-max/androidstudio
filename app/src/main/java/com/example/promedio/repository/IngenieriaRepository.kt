package com.example.promedio.repository

import com.example.promedio.model.modelIngenieria
import com.example.promedio.model.IngenieriaDao


class IngenieriaRepository(private val dao: IngenieriaDao) {

    suspend fun getAll() = dao.getAll()
    suspend fun insert(notas: modelIngenieria) = dao.insert(notas)
    suspend fun update(notas: modelIngenieria) = dao.update(notas)
    suspend fun delete(notas: modelIngenieria) = dao.delete(notas)
}
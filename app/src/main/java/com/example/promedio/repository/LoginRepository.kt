package com.example.promedio.repository

import com.example.promedio.model.LoginDao
import com.example.promedio.model.Login
import com.example.promedio.data.api.model.LoginApi

// creamos el repositorio donde nos encargaremos de recibir
// insertar, actualizar y eliminar de la base de datos trabajando en conjunto con Dao
class LoginRepository (private val dao: LoginDao) {


    suspend fun getAll() = dao.getAll()


    suspend fun insert(usuario: Login) = dao.insert(usuario)


    suspend fun update(usuario: Login) = dao.update(usuario)


    suspend fun delete(usuario: Login) = dao.delete(usuario)


}
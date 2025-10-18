package com.example.promedio.repository

import com.example.promedio.model.LoginDao
import com.example.promedio.model.Login

class LoginRepository (private val dao: LoginDao) {
    suspend fun getAll() = dao.getAll()
    suspend fun insert(usuario: Login) = dao.insert(usuario)
    suspend fun update(usuario: Login) = dao.update(usuario)
    suspend fun delete(usuario: Login) = dao.delete(usuario)


}
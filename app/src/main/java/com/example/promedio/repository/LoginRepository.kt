package com.example.promedio.repository

import com.example.promedio.model.Login

class LoginRepository {

    private val login = mutableListOf<Login>()

    fun loginAlumno(alumno: Login): Boolean {

        login.add(alumno)
        return true
    }

    fun getAllLogin(): List<Login> = login
}
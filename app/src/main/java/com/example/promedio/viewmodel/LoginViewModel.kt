package com.example.promedio.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.promedio.model.Login
import com.example.promedio.repository.LoginRepository

class LoginViewModel {

    private val repository = LoginRepository()
    private val login = Login()
    var mensaje by mutableStateOf("")
    var colorMensaje by mutableStateOf(0xFFb00020)

    val carreras = listOf(
        "Seleccione tu carrera",
        "Tecnico en odontologia",
        "Ingenieria en informatica"
    )

    fun iniciarSesion(){
        if (login.nombre.isBlank()|| login.correo.isBlank()|| login.carrera.isBlank()){
            mensaje = "campos vacios"
            colorMensaje=0xFFb00020
        } else{
            val  alumno = Login(login.nombre, login.correo,login.carrera)
            repository.loginAlumno(alumno)
        }
    }
}
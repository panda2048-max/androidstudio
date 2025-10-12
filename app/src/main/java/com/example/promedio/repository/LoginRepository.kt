package com.example.promedio.repository

import com.example.promedio.model.Login
import com.example.promedio.model.MensajeError

class LoginRepository {

    private var login = Login()
    private var errores = MensajeError()

    fun getLogin(): Login = login
    fun getMensajeError(): MensajeError = errores

    fun cambiarNombre(nuevoNombre: String){
        login.nombre = nuevoNombre
    }

    fun validacionNombre(): Boolean{
        if (login.nombre=="")
            return false
        else
            return true
    }

    fun validacionCorreo(): Boolean{
        if (!login.correo.matches(Regex("^[\\w.-]+@[\\w.-]+\\.\\w+$")))
            return false
        else
            return true
    }

    fun validacionTerminos(): Boolean{
        if (!login.terminos)
            return false
        else
            return true
    }
}
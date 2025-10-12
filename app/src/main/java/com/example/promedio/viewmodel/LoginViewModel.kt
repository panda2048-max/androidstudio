package com.example.promedio.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.promedio.model.Login
import com.example.promedio.model.MensajeError
import com.example.promedio.repository.LoginRepository

class LoginViewModel {

    private val repository = LoginRepository()

    var login: Login by mutableStateOf( repository.getLogin())
    var mensajeError: MensajeError by mutableStateOf(repository.getMensajeError())

    fun  verificarLogin(): Boolean{
        return verificarNombre() &&
                verificarCorreo() &&
                verificarTerminos()

    }

    fun verificarNombre(): Boolean{
        if (!repository.validacionNombre()){
            mensajeError.nombre = "el nombre esta vacio"
            return false
        }else{
            mensajeError.nombre = ""
            return true
        }
        return repository.validacionNombre()
    }

    fun verificarCorreo(): Boolean{
        if (!repository.validacionCorreo()){
            mensajeError.correo = "el correo esta vacio"
            return false
        }else{
            mensajeError.correo = ""
            return true
        }
        return repository.validacionCorreo()
    }

    fun verificarTerminos(): Boolean{
        if (!repository.validacionTerminos()){
            mensajeError.terminos = "Debes aceptar los terminos"
            return false
        }else{
            mensajeError.terminos = ""
            return true
        }
        return repository.validacionTerminos()
    }




}
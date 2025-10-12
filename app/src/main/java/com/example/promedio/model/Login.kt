package com.example.promedio.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class Login {
    var nombre by mutableStateOf("")
    var correo by mutableStateOf("")
    var carrera by mutableStateOf("")

    var terminos by mutableStateOf(false)
}
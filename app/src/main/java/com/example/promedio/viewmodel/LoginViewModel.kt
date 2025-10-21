package com.example.promedio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.promedio.model.Login
import com.example.promedio.repository.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

// creacion de viewmodel el principal encargado de manejar los metodos
// de leer,crear,actualizar y eliminar
class LoginViewModel(private val repository: LoginRepository) : ViewModel() {

    val nombre = MutableStateFlow("")
    val correo = MutableStateFlow("")

    val logins = MutableStateFlow<List<Login>>(emptyList())

    init {
        cargarUsuarios()
    }

    private fun cargarUsuarios() {
        viewModelScope.launch {
            logins.value = repository.getAll()
        }
    }

    fun agregarUsuario(login: Login) {
        viewModelScope.launch {
            repository.insert(login)
            cargarUsuarios()
        }
    }

    fun actualizarUsuario(login: Login) {
        viewModelScope.launch {
            repository.update(login)
            cargarUsuarios()
        }
    }

    fun eliminarUsuario(login: Login) {
        viewModelScope.launch {
            repository.delete(login)
            cargarUsuarios()
        }
    }
}

package com.example.promedio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.promedio.data.api.model.LoginApi
import com.example.promedio.model.Login
import com.example.promedio.repository.LoginRepository
import com.example.promedio.data.api.repository.LoginRepositoryApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

// creacion de viewmodel el principal encargado de manejar los metodos
// de leer,crear,actualizar y eliminar
class LoginViewModel(private val repository: LoginRepository, private val repositoryApi: LoginRepositoryApi) : ViewModel() {

    val nombre = MutableStateFlow("")
    val correo = MutableStateFlow("")

    // Datos locales (Room)
    val logins = MutableStateFlow<List<Login>>(emptyList())

    // Datos remotos (API)
    val usuariosRemotos = MutableStateFlow<List<LoginApi>>(emptyList())
    val cargando = MutableStateFlow(false)
    val error = MutableStateFlow<String?>(null)


    init {
        cargarUsuariosLocales()
    }


    // ================================
    //      ROOM (Base de datos local)
    // ================================

    private fun cargarUsuariosLocales() {
        viewModelScope.launch {
            logins.value = repository.getAll()
        }
    }

    fun agregarUsuario(login: Login) {
        viewModelScope.launch {
            repository.insert(login)
            cargarUsuariosLocales()
        }
    }

    fun actualizarUsuario(login: Login) {
        viewModelScope.launch {
            repository.update(login)
            cargarUsuariosLocales()
        }
    }

    fun eliminarUsuario(login: Login) {
        viewModelScope.launch {
            repository.delete(login)
            cargarUsuariosLocales()
        }
    }


    // ================================
    //          RETROFIT (API)
    // ================================

    fun cargarUsuariosRemotos() {
        viewModelScope.launch {
            try {
                cargando.value = true

                val data = repositoryApi.fetchItems()?.toList() ?: emptyList()


                usuariosRemotos.value = data
                error.value = null

            } catch (e: Exception) {
                error.value = "Error al cargar usuarios de la API: ${e.message}"
            } finally {
                cargando.value = false
            }
        }
    }
}



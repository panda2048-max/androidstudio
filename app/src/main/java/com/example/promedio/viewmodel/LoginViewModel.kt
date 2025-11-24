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
// ViewModel que maneja tanto datos locales (Room) como datos remotos (API)
class LoginViewModel(
    private val repository: LoginRepository,          // Repositorio local (Room)
    private val repositoryApi: LoginRepositoryApi     // Repositorio remoto (API)
) : ViewModel() {

    // Estado para almacenar el nombre ingresado por el usuario
    val nombre = MutableStateFlow("")

    // Estado para almacenar el correo ingresado
    val correo = MutableStateFlow("")

    // ================================
    //      ROOM (Base de datos local)
    // ================================

    // Lista de usuarios almacenados en la base de datos local
    val logins = MutableStateFlow<List<Login>>(emptyList())

    // ================================
    //          RETROFIT (API)
    // ================================

    // Lista de usuarios obtenidos desde la API
    val usuariosRemotos = MutableStateFlow<List<LoginApi>>(emptyList())

    // Indica si la app está realizando una llamada a la API
    val cargando = MutableStateFlow(false)

    // Guarda un mensaje de error cuando algo sale mal
    val error = MutableStateFlow<String?>(null)


    // Cuando se crea el ViewModel, se cargan los datos locales
    init {
        cargarUsuariosLocales()
    }


    // ======================================
    //        MÉTODOS PARA ROOM (Local)
    // ======================================

    // Obtiene todos los usuarios desde Room
    private fun cargarUsuariosLocales() {
        viewModelScope.launch {
            logins.value = repository.getAll()
        }
    }

    // Inserta un usuario en la BD y recarga la lista
    fun agregarUsuario(login: Login) {
        viewModelScope.launch {
            repository.insert(login)
            cargarUsuariosLocales()
        }
    }

    // Actualiza un usuario en la BD
    fun actualizarUsuario(login: Login) {
        viewModelScope.launch {
            repository.update(login)
            cargarUsuariosLocales()
        }
    }

    // Elimina un usuario de la BD
    fun eliminarUsuario(login: Login) {
        viewModelScope.launch {
            repository.delete(login)
            cargarUsuariosLocales()
        }
    }


    // ======================================
    //        MÉTODOS PARA RETROFIT (API)
    // ======================================

    // Carga los usuarios desde la API remota
    fun cargarUsuariosRemotos() {
        viewModelScope.launch {
            try {
                cargando.value = true  // Indica que empieza la carga

                // Llama al repositorio remoto
                // Si la API responde null → retorna lista vacía
                val data = repositoryApi.fetchItems()?.toList() ?: emptyList()

                // Guarda los usuarios obtenidos
                usuariosRemotos.value = data

                // Si odo salio bien, se borra cualquier error previo
                error.value = null

            } catch (e: Exception) {
                // Captura cualquier error ocurrido durante la llamada
                error.value = "Error al cargar usuarios de la API: ${e.message}"
            } finally {
                cargando.value = false  // Termina la carga, haya error o no
            }
        }
    }
}




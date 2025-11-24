package com.example.promedio.data.api.repository;

import com.example.promedio.data.api.model.LoginApi;
import com.example.promedio.data.api.RetrofitLogin;
import java.util.List;

// Repositorio encargado de obtener datos desde una API remota usando Retrofit
class LoginRepositoryApi {

    // Función suspendida: se ejecuta dentro de corrutinas y permite hacer llamadas de red sin bloquear el hilo
    suspend fun fetchItems(): List<LoginApi>? {

        // Llama al servicio de Retrofit y obtiene la respuesta HTTP del servidor
        val response = RetrofitLogin.apiService.getStudent()

        // Si la respuesta HTTP fue exitosa (código 200–299)
        return if (response.isSuccessful) {

            // Devuelve el cuerpo de la respuesta, que debería ser una lista de LoginApi
            response.body()

        } else {
            // Si la respuesta NO fue exitosa (404, 500, error del servidor, etc.)
            // se devuelve null indicando que hubo un problema
            null
        }
    }
}



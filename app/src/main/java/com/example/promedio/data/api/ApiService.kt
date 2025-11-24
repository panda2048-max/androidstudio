package com.example.promedio.data.api;

import com.example.promedio.data.api.model.LoginApi;
import java.util.List;
import retrofit2.Response;
import retrofit2.http.GET;

// Interfaz que define los endpoints de la API para Retrofit
interface ApiService {

    // Indica que este metodo realizará una petición HTTP GET al endpoint "student"
    @GET("student")

    // Función suspendida (para usar en corrutinas) que llama al servidor
    // Devuelve un objeto Response que contiene una lista de LoginApi
    suspend fun getStudent(): Response<List<LoginApi>>
}


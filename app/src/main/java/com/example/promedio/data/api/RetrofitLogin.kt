package com.example.promedio.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitLogin {
    // llamamos la url de la api que ocupamos
    private const val BASE_URL = "https://x8ki-letl-twmt.n7.xano.io/api:IIMsUn_q/"


    // Declaramos una variable apiService que se inicializará solo cuando se use por primera vez (lazy)
    val apiService: ApiService by lazy {

        // Se crea una instancia del objeto Retrofit
        Retrofit.Builder()

            // Se define la URL base donde están los endpoints de la API
            .baseUrl(BASE_URL)

            // Se añade un convertidor para transformar JSON ↔ objetos Kotlin usando Gson
            .addConverterFactory(GsonConverterFactory.create())

            // Se construye la instancia de Retrofit
            .build()

            // Se crea una implementación de la interfaz ApiService
            .create(ApiService::class.java)
    }

}
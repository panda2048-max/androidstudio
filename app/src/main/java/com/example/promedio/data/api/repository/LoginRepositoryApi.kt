package com.example.promedio.data.api.repository;

import com.example.promedio.data.api.model.LoginApi;
import com.example.promedio.data.api.RetrofitLogin;

import java.util.List;

class LoginRepositoryApi {

    suspend fun fetchItems(): List<LoginApi>? {
        val response = RetrofitLogin.apiService.getStudent()

        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}


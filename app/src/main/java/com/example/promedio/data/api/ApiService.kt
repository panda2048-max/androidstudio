package com.example.promedio.data.api;

import com.example.promedio.data.api.model.LoginApi;
import java.util.List;
import retrofit2.Response;
import retrofit2.http.GET;

interface ApiService {

    @GET("student")
    suspend fun getStudent(): Response<List<LoginApi>>
}

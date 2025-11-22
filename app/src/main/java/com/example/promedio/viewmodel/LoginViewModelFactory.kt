package com.example.promedio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.promedio.repository.LoginRepository
import com.example.promedio.data.api.repository.LoginRepositoryApi

class LoginViewModelFactory(
    private val repository: LoginRepository,
    private val repositoryApi: LoginRepositoryApi
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(repository, repositoryApi) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


package com.example.promedio.viewmodel

import androidx.lifecycle.ViewModelProvider
import com.example.promedio.repository.LoginRepository
import androidx.lifecycle.ViewModel
class LoginViewModelFactory(
    private val repository: LoginRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

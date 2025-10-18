package com.example.promedio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.promedio.repository.OdontologiaRepository

class OdontologiaViewModelFactory(private val repository: OdontologiaRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OdontologiaViewModel::class.java)) {
            return OdontologiaViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

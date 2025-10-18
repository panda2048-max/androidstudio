package com.example.promedio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.promedio.repository.IngenieriaRepository

class IngenieriaViewModelFactory(private val repository: IngenieriaRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IngenieriaViewModel::class.java)) {
            return IngenieriaViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

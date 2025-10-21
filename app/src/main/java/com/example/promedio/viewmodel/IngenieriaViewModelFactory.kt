package com.example.promedio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.promedio.repository.IngenieriaRepository

// Fábrica (Factory) para crear instancias del ViewModel 'OdontologiaViewModel'.
// permite controlar inyectar el repository con el viewModel de manera mas controlada
class IngenieriaViewModelFactory(private val repository: IngenieriaRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // verifica si la clase solicitada es del tipo requerido
        if (modelClass.isAssignableFrom(IngenieriaViewModel::class.java)) {
            return IngenieriaViewModel(repository) as T
        }
        // si la clase no corresponde, lanza una excepcion
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

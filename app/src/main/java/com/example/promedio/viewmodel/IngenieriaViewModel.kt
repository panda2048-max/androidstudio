package com.example.promedio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.promedio.model.modelIngenieria
import com.example.promedio.repository.IngenieriaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


// creacion de viewmodel el principal encargado de manejar los metodos
// de leer,crear,actualizar y eliminar
class IngenieriaViewModel(val repository: IngenieriaRepository) : ViewModel() {


    val nota_1 = MutableStateFlow("")
    val nota_2 = MutableStateFlow("")
    val nota_3  = MutableStateFlow("")

    val notas = MutableStateFlow<List<modelIngenieria>>(emptyList())

    init{
        cargarNotas()
    }

    private fun cargarNotas() {
        viewModelScope.launch {
            notas.value = repository.getAll()
        }
    }

    fun agregarNotas(nota: modelIngenieria) {
        viewModelScope.launch {
            repository.insert(nota)
            cargarNotas()
        }
    }

    fun actualizarNotas(nota: modelIngenieria) {
        viewModelScope.launch {
            repository.update(nota)
            cargarNotas()
        }
    }

    fun eliminarNota(nota: modelIngenieria) {
        viewModelScope.launch {
            repository.delete(nota)
            cargarNotas()
        }
    }

}
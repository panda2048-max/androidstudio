package com.example.promedio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.promedio.model.modelOdontologia
import com.example.promedio.repository.OdontologiaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class OdontologiaViewModel(val repository: OdontologiaRepository) : ViewModel() {

    val nota_1 = MutableStateFlow("")
    val nota_2 = MutableStateFlow("")
    val nota_3 = MutableStateFlow("")

    val notas = MutableStateFlow<List<modelOdontologia>>(emptyList())

    init {
        cargarNotas()
    }

    private fun cargarNotas() {
        viewModelScope.launch {
            notas.value = repository.getAll()
        }
    }

    fun agregarNotas(nota: modelOdontologia) {
        viewModelScope.launch {
            repository.insert(nota)
            cargarNotas()
        }
    }

    fun actualizarNotas(nota: modelOdontologia) {
        viewModelScope.launch {
            repository.update(nota)
            cargarNotas()
        }
    }

    fun eliminarNota(nota: modelOdontologia) {
        viewModelScope.launch {
            repository.delete(nota)
            cargarNotas()
        }
    }
}

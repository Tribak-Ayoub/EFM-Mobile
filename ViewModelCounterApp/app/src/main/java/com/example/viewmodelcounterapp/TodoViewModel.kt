package com.example.viewmodelcounterapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TodoViewModel : ViewModel() {
    private val _inscrites = MutableStateFlow<List<Inscrite>>(emptyList())
    val inscrites: StateFlow<List<Inscrite>> = _inscrites

    init {
        fetchInscrites()
    }

    private fun fetchInscrites() {
        viewModelScope.launch {
            try {
                val result = RetrofitClient.api.getInscrites()
                _inscrites.value = result
            } catch (e: Exception) {
                // Handle error: you could emit a placeholder item with error info, or handle in UI
                _inscrites.value = listOf(
                    Inscrite(id = -1, nom = "Error: ${e.message}", statut = "Error: ${e.message}", priorite = "Error: ${e.message}")
                )
            }
        }
    }

    fun addInscrite(inscrite: Inscrite) {
        viewModelScope.launch {
            try {
                val newInscrite = RetrofitClient.api.createInscrite(inscrite)
                _inscrites.value = _inscrites.value + newInscrite
            }catch (e: Exception) {
                // Handle error: you could emit a placeholder item with error info, or handle in UI
                _inscrites.value = listOf(
                    Inscrite(id = -1, nom = "Error: ${e.message}", statut = "Error: ${e.message}", priorite = "Error: ${e.message}")
                )
            }
        }
    }

    fun updateInscrite(inscrite: Inscrite) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.api.updateInscrite(inscrite.id, inscrite)
                if (response.isSuccessful) {
                    _inscrites.value = _inscrites.value.map {
                        if (it.id == inscrite.id) inscrite else it
                    }
                } else {
                    // handle API error (e.g., show error message)
                }
            }catch (e: Exception) {
                // Handle error: you could emit a placeholder item with error info, or handle in UI
                _inscrites.value = listOf(
                    Inscrite(id = -1, nom = "Error: ${e.message}", statut = "Error: ${e.message}", priorite = "Error: ${e.message}")
                )
            }
        }
    }

    fun deleteInscrite(id: Int) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.api.deleteInscrite(id)
                if (response.isSuccessful) {
                    _inscrites.value = _inscrites.value.filter { it.id != id }
                }
            } catch (e: Exception) {
                // handle error
            }
        }
    }
}
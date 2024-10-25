package com.dieyteixeira.pontol.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dieyteixeira.pontol.ui.status.TimeValues
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.launch

class TimeViewModel : ViewModel() {

    private val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("timeData")

    // Estado mutável para armazenar os dados de tempo
    var timeValue = mutableStateOf(
        TimeValues(
            dateSet = "",
            initialTimeP1 = "",
            finalTimeP1 = "",
            initialTimeP2 = "",
            finalTimeP2 = "",
            initialTimeP3 = "",
            finalTimeP3 = "",
            initialTimeP4 = "",
            finalTimeP4 = "",
            totalNormal = "",
            totalExtra = "",
            totalTime = ""
        )
    )

    // Função para salvar dados no Firebase
    fun saveTimeValue() {
        viewModelScope.launch {
            val id = database.push().key // Gera um ID único
            if (id != null) {
                database.child(id).setValue(timeValue.value).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Dados salvos com sucesso
                    } else {
                        // Tratamento de erro
                    }
                }
            }
        }
    }

    // Função para atualizar o estado
    fun updateTimeValue(newValue: TimeValues) {
        timeValue.value = newValue
    }
}
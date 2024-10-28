package com.dieyteixeira.pontol.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dieyteixeira.pontol.model.PontoModel
import com.dieyteixeira.pontol.repository.PontoRepository
import com.dieyteixeira.pontol.ui.states.PontoUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PontoViewModel(
    private val repository: PontoRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<PontoUiState> =
        MutableStateFlow(PontoUiState())
    val uiState get() = _uiState

    var dataRegistro by mutableStateOf("")
    var horaInicialP1 by mutableStateOf("")
    var horaFinalP1 by mutableStateOf("")
    var horaInicialP2 by mutableStateOf("")
    var horaFinalP2 by mutableStateOf("")
    var horaInicialP3 by mutableStateOf("")
    var horaFinalP3 by mutableStateOf("")
    var horaInicialP4 by mutableStateOf("")
    var horaFinalP4 by mutableStateOf("")
    var horaNormal by mutableStateOf("")
    var horaExtra by mutableStateOf("")
    var horaTotal by mutableStateOf("")

    init {
        viewModelScope.launch {
            loadRegistros()
        }
    }

    suspend fun loadRegistros() {
        viewModelScope.launch {
            try {
                repository.loadRegistros().collect { ponto ->
                    _uiState.value = _uiState.value.copy(registros = ponto)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun saveRegistro() {
        viewModelScope.launch {
            with(_uiState.value) {
                repository.saveRegistro(
                    PontoModel(
                        dataRegistro = dataRegistro,
                        horaInicialP1 = horaInicialP1,
                        horaFinalP1 = horaFinalP1,
                        horaInicialP2 = horaInicialP2,
                        horaFinalP2 = horaFinalP2,
                        horaInicialP3 = horaInicialP3,
                        horaFinalP3 = horaFinalP3,
                        horaInicialP4 = horaInicialP4,
                        horaFinalP4 = horaFinalP4,
                        horaNormal = horaNormal,
                        horaExtra = horaExtra,
                        horaTotal = horaTotal
                    )
                )
            }
        }
    }

    fun deleteRegistro(ponto: PontoModel) {
        viewModelScope.launch {
            try {
                repository.deleteRegistro(ponto)
                _uiState.value = _uiState.value.copy(
                    registros = _uiState.value.registros.filterNot { it.dataRegistro == ponto.dataRegistro }
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateRegistro(
        dataRegistro: String,
        horaInicialP1: String,
        horaFinalP1: String,
        horaInicialP2: String,
        horaFinalP2: String,
        horaInicialP3: String,
        horaFinalP3: String,
        horaInicialP4: String,
        horaFinalP4: String,
        horaNormal: String,
        horaExtra: String,
        horaTotal: String
    ) {
        this.dataRegistro = dataRegistro
        this.horaInicialP1 = horaInicialP1
        this.horaFinalP1 = horaFinalP1
        this.horaInicialP2 = horaInicialP2
        this.horaFinalP2 = horaFinalP2
        this.horaInicialP3 = horaInicialP3
        this.horaFinalP3 = horaFinalP3
        this.horaInicialP4 = horaInicialP4
        this.horaFinalP4 = horaFinalP4
        this.horaNormal = horaNormal
        this.horaExtra = horaExtra
        this.horaTotal = horaTotal
        saveRegistro()
    }
}
package com.dieyteixeira.pontol.ui.states

import com.dieyteixeira.pontol.model.PontoModel

data class PontoUiState(
    val registros: List<PontoModel> = emptyList(),
)

data class RegistrosUiState(
    val dataRegistro: String = "",
    val horaInicialP1: String = "",
    val horaFinalP1: String = "",
    val horaInicialP2: String = "",
    val horaFinalP2: String = "",
    val horaInicialP3: String = "",
    val horaFinalP3: String = "",
    val horaInicialP4: String = "",
    val horaFinalP4: String = "",
    val horaNormal: String = "",
    val horaExtra: String = "",
    val horaTotal: String = ""
)
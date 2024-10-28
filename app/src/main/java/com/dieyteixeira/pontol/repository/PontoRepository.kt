package com.dieyteixeira.pontol.repository

import com.dieyteixeira.pontol.model.PontoModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class PontoRepository {

    private val db = FirebaseFirestore.getInstance()

    private fun pontoCollection() =
        db.collection("banco_dados")
            .document("pontos")
            .collection("registros")

    fun loadRegistros(): Flow<List<PontoModel>> = flow {
        try {
            val snapshot = pontoCollection()
                .orderBy("data").get().await()
            val registrosList = snapshot.documents.mapNotNull { it.toObject(PontoModel::class.java) }
            emit(registrosList)
        } catch (e: Exception) {
            emit(emptyList())
        }
    }

    suspend fun saveRegistro(ponto: PontoModel) = withContext(Dispatchers.IO) {
        try {
            val pontoDoc = if (ponto.dataRegistro.isBlank()) {
                pontoCollection().document()
            } else {
                pontoCollection().document(ponto.dataRegistro)
            }
            pontoDoc.set(ponto).await()
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun deleteRegistro(ponto: PontoModel) = withContext(Dispatchers.IO) {
        try {
            pontoCollection().document(ponto.dataRegistro).delete().await()
        } catch (e: Exception) {
            throw e
        }
    }

}
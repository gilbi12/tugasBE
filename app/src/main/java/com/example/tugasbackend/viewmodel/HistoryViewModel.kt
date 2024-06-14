package com.example.tugasbackend.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugasbackend.data.Payment
import com.example.tugasbackend.repository.PaymentRepository
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val repository: PaymentRepository= PaymentRepository()
):ViewModel() {
    var historyUiState by mutableStateOf(HistoryUiState())
    fun getAllPayments() = viewModelScope.launch {
        repository.getAllPayments(onSuccess={
            historyUiState = historyUiState.copy(payments = it)
        }, onError = {

        })
    }

}
data class HistoryUiState(
    val payments: List<Payment> = emptyList()
)
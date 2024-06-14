package com.example.tugasbackend.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugasbackend.data.Payment
import com.example.tugasbackend.repository.AuthRepository
import com.example.tugasbackend.repository.PaymentRepository
import kotlinx.coroutines.launch


class CheckOutViewModel(private  val repository: PaymentRepository = PaymentRepository()): ViewModel() {

    var paymentUiState by mutableStateOf(PaymentUiState())

    fun createPayment(nominal:String, address: String,image:String,product_name:String) = viewModelScope.launch {
        repository.createPayment(nominal,address,image,product_name)
        paymentUiState = paymentUiState.copy(isPaymentSuccess = true)
    }
    fun getAllPayments(onSuccess: (List<Payment>) -> Unit, onError: (Exception) -> Unit) = viewModelScope.launch {
        repository.getAllPayments(onSuccess, onError)
        paymentUiState = paymentUiState.copy(isPaymentSuccess = true)
    }
}

data class PaymentUiState(
    val isPaymentSuccess: Boolean = false,
)
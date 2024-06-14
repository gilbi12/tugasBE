package com.example.tugasbackend.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugasbackend.data.Brand
import com.example.tugasbackend.repository.AuthRepository
import com.example.tugasbackend.repository.ProductsRepository
import kotlinx.coroutines.launch

class ProductViewModel(private  val repository: ProductsRepository = ProductsRepository()): ViewModel() {
    var products by mutableStateOf<List<Brand>>(emptyList())
    init {
        getProducts()
    }
    fun getProducts() = viewModelScope.launch {
        repository.getProducts {
            products =it
        }
    }
}

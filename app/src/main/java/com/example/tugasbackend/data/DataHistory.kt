package com.example.tugasbackend.data

data class DataHistory(
    val id: Int,
    val imageRes: Int,
    val transactionStatus: String,
    val itemName: String,
    val itemPrice: String,
    val location: String
)

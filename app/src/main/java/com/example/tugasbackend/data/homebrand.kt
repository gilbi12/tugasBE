package com.example.tugasbackend.data

data class Brand(
    val id: String,
    val name: String,
    val category: String,
    val image: String,
    val price: String,
    val description: String,
    val ukuran: Ukuran,
    val alamat: String
    )
data class Ukuran(
    val ld: String,
    val p:String
)
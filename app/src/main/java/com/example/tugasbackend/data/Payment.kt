package com.example.tugasbackend.data

data class Payment (
    val nominal:Int,
    val address:String,
    val status:String="Pending",
    val date:String,
    val image:String,
    val product_name:String
)
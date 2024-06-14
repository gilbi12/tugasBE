package com.example.tugasbackend.data

data class User (
    val id: String? = null,
    val name: String,
    val email: String,
    val password: String? = null,
    var authId:String? = null,
    val phone: String

)
package com.example.tugasbackend.repository

import com.example.tugasbackend.data.Brand
import com.example.tugasbackend.data.Ukuran
import com.google.firebase.Firebase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.getField
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductsRepository {
    val productCollection: CollectionReference = Firebase.firestore.collection("products")
    suspend fun getProducts(onSuccess:(List<Brand>)->Unit)= withContext(Dispatchers.IO){
        productCollection.get().addOnSuccessListener { result ->
            val brands = result.documents.mapNotNull { document ->
                val ukuran = document.get("ukuran") as Map<*, *>
                Brand(
                    id =document.id,
                    name = document.getString("name")!!,
                    category = document.getString("category")!!,
                    image = document.getString("image")!!,
                    price = document.getString("price")!!,
                    description = document.getString("description")!!,
                    ukuran = Ukuran(
                        ld= ukuran["ld"].toString(),
                        p= ukuran["p"].toString()
                    ),
                    alamat = document.getString("alamat")!!
                )
            }
            onSuccess(brands)

        }
    }
}
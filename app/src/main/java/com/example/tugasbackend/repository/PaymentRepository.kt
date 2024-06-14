package com.example.tugasbackend.repository

import android.annotation.SuppressLint
import android.util.Log
import com.example.tugasbackend.data.Payment
import com.google.firebase.Firebase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date


class PaymentRepository {
    val paymentCollection: CollectionReference = Firebase.firestore.collection("payments")

    @SuppressLint("SimpleDateFormat")
    suspend fun createPayment(nominal:String, address: String,image:String,product_name:String )= withContext(Dispatchers.IO){
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        val payment = Payment(
            nominal = nominal.toInt(),
            address=address,
            status = "Pendding",
            date = currentDate.toString(),
            image = image,
            product_name=product_name)
        paymentCollection.add(payment)
    }
    suspend fun getAllPayments(onSuccess: (List<Payment>) -> Unit, onError: (Exception) -> Unit)= withContext(Dispatchers.IO){
        paymentCollection.get().addOnSuccessListener { querySnapshot ->
            val payments = querySnapshot.mapNotNull { paymentSnapshot ->
                Payment(
                    nominal = paymentSnapshot.get("nominal").toString().toInt(),
                    date = paymentSnapshot.get("date").toString(),
                    status = paymentSnapshot.get("status").toString(),
                    image = paymentSnapshot.get("image").toString(),
                    address = paymentSnapshot.get("address").toString(),
                    product_name = paymentSnapshot.get("product_name").toString()

                        )

            }
            onSuccess(payments)
            Log.d("payments",payments.toString())
        }.addOnFailureListener { exception ->
            onError(exception)
        }
    }
}
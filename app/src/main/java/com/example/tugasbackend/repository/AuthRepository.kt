package com.example.tugasbackend.repository

import android.util.Log
import com.example.tugasbackend.data.User

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AuthRepository{
    suspend fun getCurrentUser(onSuccess: (User?) -> Unit)= withContext(Dispatchers.IO) {
        val user = Firebase.auth.currentUser
        if (user != null)
            userCollection.whereEqualTo("authId", user?.uid).get()
                .addOnSuccessListener { documents ->
                    val result = documents.firstOrNull()?.let { data ->
                        User(
                            id = data.id,
                            name = data.get("name") as String,
                            email = data.get("email") as String,
                            authId = data.get("authId") as String,
                            phone = data.get("phone") as String
                        )
                    }
                    Log.d("CurrentUser", "getCurrentUser: $result")
                    onSuccess.invoke(result)
                }
        }





    val userCollection: CollectionReference = Firebase.firestore.collection("users")
   suspend fun createUser(user: User)= withContext(Dispatchers.IO) {
            if(user.password == null) throw Exception("Password cannot be null")
            Firebase.auth.createUserWithEmailAndPassword(user.email, user.password).addOnSuccessListener {
                user.authId = Firebase.auth.currentUser?.uid.toString()
                userCollection.add(user)
            }
    }
    suspend fun login(email: String, password: String,onError:(Exception)->Unit,onSuccess:(FirebaseUser)->Unit)=withContext(Dispatchers.IO) {
            Firebase.auth.signInWithEmailAndPassword(email,password)
                .addOnFailureListener {
                onError(it)
            }.addOnSuccessListener {
                onSuccess(it.user!!)
                }
    }
    suspend fun signOut() = withContext(Dispatchers.IO){
        Firebase.auth.signOut()
    }
}
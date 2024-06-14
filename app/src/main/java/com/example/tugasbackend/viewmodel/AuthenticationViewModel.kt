package com.example.tugasbackend.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugasbackend.data.User
import com.example.tugasbackend.repository.AuthRepository
import kotlinx.coroutines.launch
import android.util.Log
import android.widget.Toast

class AuthenticationViewModel(private val repository: AuthRepository = AuthRepository()) : ViewModel() {

    var registerUiState by mutableStateOf(UserInput())
    var loginUiState by mutableStateOf(LoginUiState())
    var currentUser by mutableStateOf(UserData())

    fun onEmailChangeLogin(email: String) {
        loginUiState = loginUiState.copy(email = email)
    }

    fun onPasswordChangeLogin(password: String) {
        loginUiState = loginUiState.copy(password = password)
    }

    fun onEmailChangeRegister(email: String) {
        registerUiState = registerUiState.copy(email = email)
    }

    fun onPasswordChangeRegister(password: String) {
        registerUiState = registerUiState.copy(password = password)
    }

    fun onUserNameChangeRegister(name: String) {
        registerUiState = registerUiState.copy(name = name)
    }

    fun onPhoneChangeRegister(phone: String) {
        registerUiState = registerUiState.copy(phone = phone)
    }

    fun getCurrentUser() = viewModelScope.launch {
        try {
            repository.getCurrentUser() { user ->
                Log.d("CurrentUser1", "getCurrentUser: $user")
                currentUser = currentUser.copy(data = user as User)
            }
            Log.d("CurrentUser", "getCurrentUser: $currentUser")
        } catch (e: Exception) {
            Log.d("error", e.message.toString())
        }
    }

    fun loginAction(context: Context) = viewModelScope.launch {
        try {
            repository.login(loginUiState.email, loginUiState.password, onError = {
                loginUiState = loginUiState.copy(errorMessage = it.message)
            }, onSuccess = {
                loginUiState = loginUiState.copy(isSuccess = true)
                Toast.makeText(context, "Login Success", Toast.LENGTH_LONG).show()
            })
        } catch (e: Exception) {
            Log.d("error", e.message.toString())
        }
    }

    fun registerAction(context: Context) = viewModelScope.launch {
        try {
            repository.createUser(
                User(
                    name = registerUiState.name,
                    email = registerUiState.email,
                    password = registerUiState.password,
                    phone = registerUiState.phone
                )
            )
            registerUiState = registerUiState.copy(
                isSuccess = true,
                email = "",
                password = "",
                name = "",
                phone = ""
            )
        } catch (e: Exception) {
            Log.d("error", e.message.toString())
        }
    }
    fun logoutAction()= viewModelScope.launch {
        repository.signOut()
    }
}

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)

data class UserInput(
    val email: String = "",
    val password: String = "",
    val name: String = "",
    val phone: String = "",
    val isSuccess: Boolean = false
)

data class UserData(
    val data: User? = null
)
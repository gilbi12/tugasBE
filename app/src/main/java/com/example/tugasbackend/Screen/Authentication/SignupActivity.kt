package com.example.tugasbackend.Screen.Authentication

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tugasbackend.R
import com.example.tugasbackend.navigation.Screen
import com.example.tugasbackend.viewmodel.AuthenticationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp(modifier: Modifier = Modifier,navController: NavHostController) {
    val registerViewModel:AuthenticationViewModel = viewModel(modelClass=AuthenticationViewModel::class.java)
    val brown = Color(0xFFA52A2A)
    val context = LocalContext.current
    if(registerViewModel.registerUiState.isSuccess){
     navController.navigate(Screen.Signin.route)
    }
    Box(modifier=modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                modifier = Modifier.size(200.dp)
            )


        Column(modifier=Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally, ) {
            OutlinedTextField(
                value = registerViewModel.registerUiState.name.toString(),
                onValueChange = {
                    Log.d("name", it)
                    registerViewModel.onUserNameChangeRegister(it)
                },
                label = { Text("Name") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = brown,
                    unfocusedBorderColor = brown
                ),        singleLine = true,

                modifier = Modifier.fillMaxWidth()
            )


            OutlinedTextField(
                value = registerViewModel.registerUiState.email,
                onValueChange = {registerViewModel.onEmailChangeRegister(it)},
                label = { Text("Email") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = brown,
                    unfocusedBorderColor = brown
                ),        singleLine = true,

                modifier = Modifier.fillMaxWidth()
            )

            var password by remember { mutableStateOf("") }
            var passwordVisibility by remember { mutableStateOf(false) }
            OutlinedTextField(
                value = registerViewModel.registerUiState.password,
                onValueChange = {registerViewModel.onPasswordChangeRegister(it)},
                label = { Text("Password") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = brown,
                    unfocusedBorderColor = brown
                ),        singleLine = true,

                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                        Icon(
                            imageVector = if (passwordVisibility) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                            contentDescription = if (passwordVisibility) "Hide password" else "Show password"
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = registerViewModel.registerUiState.phone,
                onValueChange = {registerViewModel.onPhoneChangeRegister(it)},
                label = { Text("Phone") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = brown,
                    unfocusedBorderColor = brown
                ),        singleLine = true,

                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = { registerViewModel.registerAction(context = context) },
                colors = ButtonDefaults.buttonColors(containerColor = brown, contentColor = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp)
            ) {
                Text(text = "Sign up")
            }

            Row(modifier = modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                Text(text = "Sudah Punya Akun?")
                Text(text = "Masuk Disini", modifier=modifier.padding(start = 4.dp).clickable {  })
            }
        }

    }
    }


}

@Preview(showBackground = true)
@Composable
fun SignupPreview() {
    SignUp(navController = rememberNavController())
}
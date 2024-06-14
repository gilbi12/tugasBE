package com.example.tugasbackend.Screen.Authentication

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
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
fun SignIn(modifier: Modifier = Modifier,navController: NavHostController) {
    val loginViewModel: AuthenticationViewModel = viewModel(modelClass= AuthenticationViewModel::class.java)

    val brown = Color(0xFFA52A2A)
    val context = LocalContext.current
    if(loginViewModel.loginUiState.isSuccess){
        navController.navigate(Screen.Home.route)
    }
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                modifier = Modifier.size(200.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 50.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                if(loginViewModel.loginUiState.errorMessage != null){
                    Text(text = loginViewModel.loginUiState.errorMessage!!)
                }

                OutlinedTextField(
                    value = loginViewModel.loginUiState.email,
                    onValueChange = {loginViewModel.onEmailChangeLogin(it)},
                    label = { Text("Type your Email") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = brown,
                        unfocusedBorderColor = brown
                    ),
                    singleLine = true,

                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = loginViewModel.loginUiState.password,
                    onValueChange = {loginViewModel.onPasswordChangeLogin(it)},
                    label = { Text("Type Your Password") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = brown,
                        unfocusedBorderColor = brown
                    ),singleLine = true,

                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { loginViewModel.loginAction(context) },
                    colors = ButtonDefaults.buttonColors(containerColor = brown, contentColor = Color.White),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp)
                ) {
                    Text(text = "Sign in")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(modifier = modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                    Text(text = "Belum Punya Akun?")
                    Text(text = "Daftar Disini", modifier= modifier
                        .padding(start = 4.dp)
                        .clickable {navController.navigate(Screen.Signup.route) })
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SignIn(navController = rememberNavController())
}
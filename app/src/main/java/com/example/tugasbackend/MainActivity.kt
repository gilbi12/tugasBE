package com.example.tugasbackend

import BottomBar
import BrandDetailScreen
import HistoryScreen
import ProfileScreen
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

import com.example.tugasbackend.Screen.Authentication.SignIn
import com.example.tugasbackend.Screen.Authentication.SignUp
import com.example.tugasbackend.Screen.Checkout.CheckoutScreen

import com.example.tugasbackend.navigation.Screen
import com.example.tugasbackend.ui.theme.tugasbackendTheme
import com.example.tugasbackend.viewmodel.AuthenticationViewModel
import homeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            tugasbackendTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EcothriftApp(email = "Hah")
                }
            }
        }

    }


}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EcothriftApp(
    email: String,
    modifier: Modifier=Modifier,
    navController: NavHostController = rememberNavController()

){
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    val authViewModel: AuthenticationViewModel = viewModel(modelClass= AuthenticationViewModel::class.java)
    val profilViewModel= viewModel(modelClass= AuthenticationViewModel::class.java)
    profilViewModel.getCurrentUser()
    Log.d("Auth",authViewModel.currentUser.data?.authId.toString())
    var defaultRoute = Screen.Signin
    if (authViewModel.currentUser.data?.authId !== null) Screen.Home.route

    Scaffold(
        topBar =  {
            if (currentRoute == Screen.Home.route) {
                TopAppBar(
                    title = { Text(text = "Halo,"+profilViewModel.currentUser.data?.name ) }
                )
            }
        } ,
        bottomBar = {
            if(currentRoute == Screen.Home.route || currentRoute == Screen.Profile.route || currentRoute == Screen.History.route){
                BottomBar(navController )
            }
        },
        modifier = modifier
    )


    { contentPadding ->
        NavHost(
            navController = navController,
            startDestination = defaultRoute.route,
            modifier=Modifier.padding(contentPadding))
        {
            composable(Screen.Signin.route){
                SignIn(navController = navController)
            }
            composable(Screen.Signup.route){
                SignUp(navController = navController)
            }
            composable(Screen.Home.route){
                homeScreen(navController)
            }
            composable(Screen.History.route){
            HistoryScreen(navController = navController, modifier = modifier )            }
            composable(Screen.Profile.route){
                ProfileScreen(navController = navController)
            }
            composable(Screen.DetailBrand.route){
                    backstackEntry ->
                val brandId= backstackEntry.arguments?.getString("brandId").toString()
                BrandDetailScreen( brandId=brandId, navController)
            }
            composable(Screen.Checkout.route){
                    backstackEntry ->
                val brandId= backstackEntry.arguments?.getString("brandId").toString()

                CheckoutScreen(brandId,navController)
            }




        }
    }
}





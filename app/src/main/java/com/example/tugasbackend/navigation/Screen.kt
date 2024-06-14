package com.example.tugasbackend.navigation

sealed class Screen(val route:String) {
     object Home : Screen("Home")
     object History : Screen("History")
     object Profile : Screen("Profile")
     object DetailBrand : Screen ("detail/brand/{brandId}")
     object DetailMentor : Screen ("detail/mentor/{mentorId}")
     object DetailGrid : Screen ("detail/grid/{gridId}")
     object Checkout : Screen("Checkout/{brandId}")
     object Signin : Screen("Signin")
     object Signup : Screen("Signup")
}


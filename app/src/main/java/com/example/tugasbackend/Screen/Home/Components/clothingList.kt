
package com.example.tugasbackend.Screen.Home.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import clothingItem
import com.example.tugasbackend.viewmodel.ProductViewModel

@Composable
fun clothingList(navController: NavHostController, modifier: Modifier=Modifier) {
    val productViewModel= viewModel(modelClass = ProductViewModel::class.java)
        val brands = productViewModel.products



    LazyVerticalGrid(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        columns = GridCells.Fixed(3),
        modifier = modifier.fillMaxSize()
    ) {
        items(brands) {
            clothingItem(brand = it, navController = navController)
        }
    }
}




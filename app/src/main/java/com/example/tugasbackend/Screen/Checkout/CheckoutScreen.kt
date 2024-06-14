package com.example.tugasbackend.Screen.Checkout
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tugasbackend.R
import com.example.tugasbackend.Screen.Authentication.SignIn
import com.example.tugasbackend.navigation.Screen
import com.example.tugasbackend.ui.theme.MapsComponent
import com.example.tugasbackend.ui.theme.OutlinedTextFieldComponent
import com.example.tugasbackend.viewmodel.CheckOutViewModel
import com.example.tugasbackend.viewmodel.LocationViewModel
import com.example.tugasbackend.viewmodel.ProductViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberCameraPositionState

// Sample data for cart items (replace with your actual data)
data class CartItem(val name: String, val price: Double, val quantity: Int)

@Composable
fun CheckoutScreen(brandId: String,navController: NavController) {
    val productViewModel= viewModel(modelClass = ProductViewModel::class.java)
    val brands = productViewModel.products
    val brand = brands.find { it.id == brandId }
Log.d("Brand Id",brandId)
    val viewModel: LocationViewModel = viewModel(modelClass = LocationViewModel::class.java)
    val context = LocalContext.current
    viewModel.requestLocation(context)

    val location = viewModel.locationUiState
    val latLangLocation = LatLng(location.latitude, location.longitude)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition(latLangLocation, 10f, 1f, 1f)
    }

    LaunchedEffect(latLangLocation) {
        cameraPositionState.animate(CameraUpdateFactory.newLatLng(latLangLocation))
    }

    LazyColumn(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            ) {
                Text(
                    text = "Pembayaran",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                OutlinedButtonComponent(
                    OnClick = { /*TODO*/ },
                    modifier = Modifier
                        .padding(0.dp)
                        .width(100.dp),
                    borderColor = Color.White
                ) {}
            }
        }
        item {
            Spacer(modifier = Modifier.height(13.dp))
            OutlinedTextFieldComponent(
                value = location.address,
                label = "Cari Alamat",
                type = "Search"
            ) {}
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))

            MapsComponent(
                locations = listOf(latLangLocation),
                cameraPositionState = cameraPositionState
            )
        }
        item {

        Payment(
            nominal = brand?.price?:"",
            address=location.address,
            image = brand?.image ?:"",
            productName = brand?.name?:"",
            navController = navController
            ,modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp)
        )
    }
}}

@Composable
fun Payment(nominal:String,address:String,image:String,productName:String,modifier: Modifier,navController: NavController) {
    val paymentViewModel = viewModel(modelClass = CheckOutViewModel::class.java)
    if (paymentViewModel.paymentUiState.isPaymentSuccess) {
        navController.navigate(Screen.Home.route)
    }
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "NOMINAL",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center
                ),

                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(nominal, style = TextStyle(fontSize = 18.sp,textAlign = TextAlign.Center))
            Spacer(modifier = modifier.height(3.dp))
            Text("Catatan : Pembayaran Dapata dilakukan selama 24 Jam", style = TextStyle(fontSize = 12.sp))

            Spacer(modifier = modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            ) {
                QRCodeImage(
                    resourceId = R.drawable.qr,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(200.dp)
                )
            }
            Spacer(modifier = modifier.height(16.dp))
            Button(
                onClick = {
                    paymentViewModel.createPayment(nominal,address,image,productName)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
            ) {
                Text("Selesaikan Pembayaran")
            }
        }
    }
}
@Composable
fun QRCodeImage(resourceId: Int, modifier: Modifier = Modifier) {
    Image(

        painter = painterResource(id = resourceId),
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Fit

    )
}



@Composable
fun LocationList(location: String, address: String) {
    Column(Modifier.padding(vertical = 8.dp, horizontal = 16.dp)) {
        Text(
            text = location,
            modifier = Modifier.padding(vertical = 4.dp),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
        )
        Text(text = address)
        Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(top = 8.dp))
    }
}
@Composable
fun OutlinedButtonComponent(
    label:String?=null, OnClick:()->Unit,
    borderColor:Color? = Color.White,
    bgColor: Color? = Color.Transparent,
    modifier:Modifier=Modifier,
    content: @Composable() (RowScope.() -> Unit)? =null,

    ){
    Button(
        onClick =OnClick ,
        colors = ButtonDefaults.buttonColors(containerColor =bgColor ?:Color.White),
        modifier = modifier
            .padding(top = 5.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .border(1.dp, borderColor ?: Color.Transparent, RoundedCornerShape(20.dp)),
    ){

        Row (content= {
            if (content ===null) Text(text = label ?: "")
            else content()
        })
    }

}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
//    CheckoutScreen(navController = rememberNavController())
}


// ... (CartItemRow composable - remains the same)
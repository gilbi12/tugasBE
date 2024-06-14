
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.tugasbackend.data.Brand

import com.example.tugasbackend.navigation.Screen




@Composable
fun clothingItem(brand: Brand, navController: NavController){
    val brown = Color(0xFFA52A2A)

    Box(modifier =Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Card(

            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .size(200.dp)
                .aspectRatio(1f)
               /* .clickable {
                    val route =
                        Screen.DetailBrand.route.replace("{brandId}", brand.id.toString())
                    navController.navigate(route)}*/
                ,
            elevation = CardDefaults.cardElevation(2.dp),
        )

        {



            Column(modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 2.dp), horizontalAlignment = Alignment.CenterHorizontally,) {
                brandImage(brand=brand)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = brand.name, modifier=Modifier.padding(bottom = 8.dp) )
                Button(onClick = { val route =
                Screen.DetailBrand.route.replace("{brandId}", brand.id.toString())
                navController.navigate(route) },
                modifier = Modifier.height(28.dp)
                ) {
                    Text(text = "Pilih")

            }



            }
        }
    }
}



@Composable
fun brandImage(brand: Brand){
    Box(modifier=Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        AsyncImage(model = brand.image, contentDescription = brand.name,modifier=Modifier.size(50.dp) )

    }
}




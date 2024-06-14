
import android.annotation.SuppressLint
import android.graphics.Paint.Style
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.tugasbackend.viewmodel.ProductViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrandDetailScreen(brandId: String, navController: NavHostController){
    val productViewModel= viewModel(modelClass = ProductViewModel::class.java)
    val brands = productViewModel.products

    val brand = brands.find { it.id == brandId }
    val brown = Color(0xFFA52A2A)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {  },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            BottomBarBeli(brandId,navController = navController)
        }
    ) {
        if (brand != null) {
        Box(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())) {
            Column(
                modifier = Modifier.padding(vertical = 70.dp, horizontal = 16.dp)
            ) {
                Box(contentAlignment = Alignment.TopCenter) {
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .padding(8.dp),
                        elevation = CardDefaults.cardElevation(2.dp)

                    ) {
                        AsyncImage(model = brand.image, contentDescription =brand.name,modifier = Modifier
                                .padding(bottom = 16.dp)
                                .clip(
                                    RoundedCornerShape(32.dp)
                                )
                                .fillMaxWidth()
                                .size(300.dp)
                        )
                    }
                }


                Text(text = brand.name, style = typography.titleLarge)




                Text(
                    text = brand.price,
                    fontWeight = FontWeight.Bold,
                    style = typography.headlineMedium,
                    color = brown
                )
                Text(
                    text = brand.category,
                    style = typography.titleMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(text = "Deskripsi Barang", fontWeight = FontWeight.Bold)
                Text(text = brand.description)
                Text(
                    text = "Ukuran",
                    modifier = Modifier.padding(top = 8.dp),
                    fontWeight = FontWeight.Bold
                )
                Text(text = "ld: "+brand.ukuran.ld+ " p: "+brand.ukuran.p)
                Text(
                    text = "Alamat Toko",
                    modifier = Modifier.padding(top = 8.dp),
                    fontWeight = FontWeight.Bold
                )
                Text(text = brand.alamat)


            }


        }
        } else {
            Text(text = "Brand tidak ada")
        }
    }
}
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.infinitetest.ui.theme.PurpleGrey80
import com.example.infinitetest.ui.theme.cokelat
import com.example.tugasbackend.R
import com.example.tugasbackend.navigation.Screen
import com.example.tugasbackend.viewmodel.AuthenticationViewModel

/*
@Composable
fun ProfileScreen(navController: NavHostController){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.fotogue),
                contentDescription = "backprofil",
                modifier = Modifier
                    .size(235.dp)
                    .clip(CircleShape)
            )
        }



        Card(modifier= Modifier
            .padding(8.dp)
            .fillMaxWidth()) {


            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Nama")
                Text(text = "Gilang Bian Asawardhana")
            }
        }
        Card (modifier= Modifier
            .padding(8.dp)
            .fillMaxWidth()){


            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Email")
                Text(text = "gilangbian22@student.ub.ac.id")
            }
        }
        Card(modifier= Modifier
            .padding(8.dp)
            .fillMaxWidth()) {


            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Universitas")
                Text(text = "Universitas Brawijaya")
            }
        }
        Card(modifier= Modifier
            .padding(8.dp)
            .fillMaxWidth()) {


            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Jurusan")
                Text(text = "Sistem Informasi")
            }
        }
    }
}
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(modifier: Modifier = Modifier, navController: NavController) {
    val profilViewModel= viewModel(modelClass= AuthenticationViewModel::class.java)
    profilViewModel.getCurrentUser()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    cokelat
                )
                )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            // Background Image
            Spacer(modifier = Modifier.height(24.dp))
            Image(
                painter = painterResource(R.drawable.img), // Ganti dengan nama file gambar Anda
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit,
                alignment = Alignment.TopCenter
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Profile picture card
                Card(
                    shape = CircleShape,
                    elevation = CardDefaults.cardElevation(),
                    modifier = Modifier.size(150.dp)
                ) {
                    Image(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop,
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = profilViewModel.currentUser.data?.name ?: "",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(16.dp)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                // Profile information card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Column {
                        ProfileRow(
                            icon = Icons.Default.Person,
                            text = profilViewModel.currentUser.data?.name ?: ""
                        )
                        Divider()
                        ProfileRow(
                            icon = Icons.Default.Email,
                            text = profilViewModel.currentUser.data?.email ?: ""
                        )
                        Divider()
                        ProfileRow(
                            icon = Icons.Default.Call,
                            text = profilViewModel.currentUser.data?.phone ?: ""
                        )
                        Divider()
                        Button(
                            onClick = {
                                profilViewModel.logoutAction()
                                navController.navigate(Screen.Signin.route)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .background(Color.Gray) //Set background color to gray
                        ) {
                            Text(
                                "Logout",
                                color = Color.White // Set text color to white
                            )
                        }
                    }


                }
            }
        }
    }
}

@Composable
fun ProfileRow(icon: ImageVector, text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.padding(end = 16.dp)
        )
        Text(
            text = text,
            fontSize = 14.sp,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null
        )
    }
}
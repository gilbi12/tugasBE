import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerDefaults.shape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.infinitetest.ui.theme.cokelat
import com.example.tugasbackend.R
import com.example.tugasbackend.Screen.Home.Components.CarouselCard
import com.example.tugasbackend.Screen.Home.Components.clothingList

@Composable
fun homeScreen(navController: NavHostController) {
    val brown = Color(0xFFA52A2A)



    Box(modifier=Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(), horizontalAlignment = Alignment.Start) {
            Box(contentAlignment = Alignment.TopCenter) {
                Image(
                    painter = painterResource(id = R.drawable.tampilanutama),
                    contentDescription = "tampilanutama",
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(200.dp)
                )
            }
            Row(
                Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 8.dp),
                horizontalArrangement = Arrangement.End,

                ) {

                repeat(4) {
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .clip(CircleShape)
                            .size(8.dp)
                            .background(Color.DarkGray)
                            .clickable {

                            }
                    ) {

                    }
                }

            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Text(text = "New Coming", fontSize = 18.sp)

                OutlinedButton(
                    onClick = { /*TODO*/ },
                    border = BorderStroke(1.dp, cokelat), shape = RoundedCornerShape(50)
                ) {
                    Text(text = "Semua", color = cokelat)
                }
            }

            Column (modifier=Modifier.fillMaxSize()){
                clothingList(navController = navController)
            }

        }
        }

    }

import android.net.Uri
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrowseGallery
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

import com.example.tugasbackend.R
import com.example.tugasbackend.navigation.NavigationItem
import com.example.tugasbackend.navigation.Screen
import androidx.compose.ui.platform.LocalContext
import android.content.Intent
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.End
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.TextButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.example.tugasbackend.data.Brand


@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
)
{
    NavigationBar(modifier=modifier) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(id = R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(id = R.string.menu_history),
                icon = Icons.Default.BrowseGallery,
                screen = Screen.History
            ),
            NavigationItem(
                title = stringResource(id = R.string.menu_profile),
                icon = Icons.Default.Person,
                screen = Screen.Profile
            )
        )

        navigationItems.map {
                item ->
            NavigationBarItem(selected = currentRoute==item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route){
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState=true
                        }
                        restoreState=true
                        launchSingleTop=true
                    }
                },
                icon = { Icon(imageVector = item.icon, contentDescription = item.title)
                },
                label= { Text(text = item.title) }
            )
        }
    }



}


@Composable
fun BottomBarBeli(brandId:String,navController: NavController) {
    val context = LocalContext.current
    BottomAppBar {
        Row (modifier=Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically){


        TextButton(
            onClick = {
                // Intent ke WhatsApp
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("https://api.whatsapp.com/send?phone=+62895396139258")
                context.startActivity(intent)
            },
            shape = RectangleShape,
            modifier = Modifier.fillMaxHeight().width(180.dp).padding(start = 20.dp)
        ) {
            Text("Hubungi Penjual")
        }



        Button(
            onClick = { val route =
                Screen.Checkout.route.replace("{brandId}",brandId)
                navController.navigate(route) },
            modifier = Modifier.height(50.dp).width(180.dp)
        ){
            Text("Beli")
        }}

    }
}
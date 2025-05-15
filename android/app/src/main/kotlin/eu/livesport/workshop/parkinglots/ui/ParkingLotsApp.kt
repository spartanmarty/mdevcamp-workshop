package eu.livesport.workshop.parkinglots.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import eu.livesport.workshop.parkinglots.navigation.Navigation
import eu.livesport.workshop.parkinglots.ui.theme.ParkingLotsTheme

@Composable
fun ParkingLotsApp() {
    ParkingLotsTheme {
        val navController = rememberNavController()

        var bottomBarVisibility by remember { mutableStateOf(true) }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                if (bottomBarVisibility) {
                    BottomNavBar(navController)
                }
            }
        ) { padding ->
            Navigation(
                navController = navController,
                modifier = Modifier.padding(padding)
            ) { bottomBarVisible ->
                bottomBarVisibility = bottomBarVisible
            }
        }
    }
}
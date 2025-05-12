package eu.livesport.workshop.parkinglots.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import eu.livesport.workshop.parkinglots.navigation.destination.Favorites
import eu.livesport.workshop.parkinglots.navigation.destination.ParkingLotDetail
import eu.livesport.workshop.parkinglots.navigation.destination.ParkingLotsList

@Composable
fun Navigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = ParkingLotsList,
    ) {
        parkingLotsList {
            navController.navigate(route = ParkingLotDetail)
        }
        parkingLotDetail()
        favorites()
    }
}

private fun NavGraphBuilder.parkingLotsList(
    navigateParkingLotDetail: () -> Unit,
) {
    composable<ParkingLotsList> {
        Text(text = "hello list")
    }
}

private fun NavGraphBuilder.parkingLotDetail(
    navigateBack: () -> Unit = {},
) {
    composable<ParkingLotDetail> {
        Text(text = "hello detail")
    }
}

private fun NavGraphBuilder.favorites(
    navigateBack: () -> Unit = {},
) {
    composable<Favorites> {
        Text(text = "hello favorites")
    }
}

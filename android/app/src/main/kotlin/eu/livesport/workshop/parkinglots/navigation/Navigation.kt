package eu.livesport.workshop.parkinglots.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import eu.livesport.workshop.parkinglots.data.parkingDetailMock
import eu.livesport.workshop.parkinglots.navigation.destination.Favorites
import eu.livesport.workshop.parkinglots.navigation.destination.ParkingLotDetail
import eu.livesport.workshop.parkinglots.navigation.destination.ParkingLotsList
import eu.livesport.workshop.parkinglots.ui.screens.FavoritesScreen
import eu.livesport.workshop.parkinglots.ui.screens.ParkingLotDetailScreen
import eu.livesport.workshop.parkinglots.ui.screens.ParkingLotsListScreen

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
        favorites {
            navController.navigate(route = ParkingLotDetail)
        }
    }
}

private fun NavGraphBuilder.parkingLotsList(
    navigateParkingLotDetail: () -> Unit,
) {
    composable<ParkingLotsList> {
        ParkingLotsListScreen(navigateParkingLotDetail)
    }
}

private fun NavGraphBuilder.parkingLotDetail(
    navigateBack: () -> Unit = {},
) {
    composable<ParkingLotDetail> {
        ParkingLotDetailScreen(parkingDetailMock)
    }
}

private fun NavGraphBuilder.favorites(
    navigateParkingLotDetail: () -> Unit,
) {
    composable<Favorites> {
        FavoritesScreen(navigateParkingLotDetail)
    }
}

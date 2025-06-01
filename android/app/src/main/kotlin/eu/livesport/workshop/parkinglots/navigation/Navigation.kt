package eu.livesport.workshop.parkinglots.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import eu.livesport.workshop.parkinglots.navigation.destination.Favorites
import eu.livesport.workshop.parkinglots.navigation.destination.ParkingLotDetail
import eu.livesport.workshop.parkinglots.navigation.destination.ParkingLotsList
import eu.livesport.workshop.parkinglots.ui.screens.FavoritesScreen
import eu.livesport.workshop.parkinglots.ui.screens.ParkingLotDetailScreen
import eu.livesport.workshop.parkinglots.ui.screens.ParkingLotsListScreen

@Composable
fun Navigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onBottomBarVisibilityChange: (Boolean) -> Unit = {},
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = ParkingLotsList,
    ) {

        parkingLotsList(
            navigateParkingLotDetail = { id ->
                navController.navigate(route = ParkingLotDetail(id))
            },
            onBottomBarVisibilityChange = onBottomBarVisibilityChange,
        )

        parkingLotDetail(
            onBottomBarVisibilityChange = onBottomBarVisibilityChange,
        )

        favorites(
            navigateParkingLotDetail = { id ->
                navController.navigate(route = ParkingLotDetail(id))
            },
            onBottomBarVisibilityChange = onBottomBarVisibilityChange,
        )
    }
}

private fun NavGraphBuilder.parkingLotsList(
    navigateParkingLotDetail: (id: String) -> Unit,
    onBottomBarVisibilityChange: (Boolean) -> Unit = {},
) {
    composable<ParkingLotsList> { backStackEntry ->
        onBottomBarVisibilityChange(true)
        ParkingLotsListScreen(onItemClick = navigateParkingLotDetail)
    }
}

private fun NavGraphBuilder.parkingLotDetail(
    onBottomBarVisibilityChange: (Boolean) -> Unit = {},
) {
    composable<ParkingLotDetail> { backStackEntry ->
        onBottomBarVisibilityChange(false)

        val parkingLotIdInput = backStackEntry.toRoute<ParkingLotDetail>().parkingLotId
        ParkingLotDetailScreen(parkingLotId = parkingLotIdInput)
    }
}

private fun NavGraphBuilder.favorites(
    navigateParkingLotDetail: (id: String) -> Unit,
    onBottomBarVisibilityChange: (Boolean) -> Unit = {},
) {
    composable<Favorites> {
        onBottomBarVisibilityChange(true)
        FavoritesScreen(navigateParkingLotDetail)
    }
}

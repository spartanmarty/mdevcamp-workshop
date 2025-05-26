package eu.livesport.workshop.parkinglots.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
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
import eu.livesport.workshop.parkinglots.viewmodel.ParkingLotsViewModel
import org.koin.mp.KoinPlatform

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
            navController = navController,
            navigateParkingLotDetail = { id ->
                navController.navigate(route = ParkingLotDetail(id))
            },
            onBottomBarVisibilityChange = onBottomBarVisibilityChange,
        )
        parkingLotDetail(
            navController = navController,
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
    navController: NavHostController,
    navigateParkingLotDetail: (id: String) -> Unit,
    onBottomBarVisibilityChange: (Boolean) -> Unit = {},
) {
    composable<ParkingLotsList> { backStackEntry ->
        val parentEntry = remember(backStackEntry) { navController.getBackStackEntry<ParkingLotsList>() }

        onBottomBarVisibilityChange(true)
        ParkingLotsListScreen(
            viewModel = createViewModel(parentEntry),
            onItemClick = navigateParkingLotDetail,
        )
    }
}

private fun NavGraphBuilder.parkingLotDetail(
    navController: NavHostController,
    onBottomBarVisibilityChange: (Boolean) -> Unit = {},
) {
    composable<ParkingLotDetail> { backStackEntry ->
        onBottomBarVisibilityChange(false)
        val parentEntry = remember(backStackEntry) { navController.getBackStackEntry<ParkingLotsList>() }

        val parkingLotIdInput = backStackEntry.toRoute<ParkingLotDetail>().parkingLotId
        ParkingLotDetailScreen(
            parkingLotIdInput,
            viewModel = createViewModel(parentEntry),
        )
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


@Composable
private inline fun createViewModel(viewModelStoreOwner: ViewModelStoreOwner): ParkingLotsViewModel =
    viewModel<ParkingLotsViewModel>(
        viewModelStoreOwner = viewModelStoreOwner,
        factory = KoinPlatform.getKoin().get<ParkingLotsViewModel.Factory>(),
    )

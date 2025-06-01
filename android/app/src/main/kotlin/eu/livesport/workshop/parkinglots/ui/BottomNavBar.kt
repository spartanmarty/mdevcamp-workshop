package eu.livesport.workshop.parkinglots.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import eu.livesport.workshop.parkinglots.R
import eu.livesport.workshop.parkinglots.navigation.destination.Favorites
import eu.livesport.workshop.parkinglots.navigation.destination.ParkingLotsList

@Composable
fun BottomNavBar(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier,
    ) {
        val navBackStackEntry: NavBackStackEntry? by navController.currentBackStackEntryAsState()
        val currentDestination: NavDestination? = navBackStackEntry?.destination

        navBarItems().forEachIndexed { index, item ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.hasRoute(item.route::class) } == true,
                onClick = { navController.navigate(item.route) },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.label
                    )
                },
                label = {
                    Text(text = item.label)
                }
            )
        }
    }
}

@Composable
private fun navBarItems(): List<NavBarItem> {
    return listOf(
        NavBarItem(
            route = ParkingLotsList,
            icon = R.drawable.icon_parking,
            label = stringResource(R.string.label_parking_lots)
        ),
        NavBarItem(
            route = Favorites,
            icon = R.drawable.icon_favorites,
            label = stringResource(R.string.label_favorites)
        )
    )
}

private data class NavBarItem(
    val route: Any,
    val icon: Int,
    val label: String
)

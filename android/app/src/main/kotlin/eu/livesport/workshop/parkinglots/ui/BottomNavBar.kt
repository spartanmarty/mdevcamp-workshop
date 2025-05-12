package eu.livesport.workshop.parkinglots.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import eu.livesport.workshop.parkinglots.R
import eu.livesport.workshop.parkinglots.navigation.destination.Favorites
import eu.livesport.workshop.parkinglots.navigation.destination.ParkingLotsList
import kotlinx.coroutines.processNextEventInCurrentThread

@Composable
fun BottomNavBar(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val selectedNavigationIndex = rememberSaveable {
        mutableIntStateOf(0)
    }

    NavigationBar(
        modifier = modifier,
    ) {
        navBarItems().onEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selectedNavigationIndex.intValue,
                onClick = {
                    selectedNavigationIndex.intValue = index
                    navController.navigate(item.route)
                },
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
            icon = R.drawable.icon_list,
            label = "Parking Lots"
        ),
        NavBarItem(
            route = Favorites,
            icon = R.drawable.icon_favorites,
            label = "Favorites"
        )
    )
}

private data class NavBarItem(
    val route: Any,
    val icon: Int,
    val label: String
)

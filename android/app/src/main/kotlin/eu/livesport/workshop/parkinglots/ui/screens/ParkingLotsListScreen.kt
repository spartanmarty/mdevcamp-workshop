package eu.livesport.workshop.parkinglots.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import eu.livesport.workshop.parkinglots.data.ParkingItemModel
import eu.livesport.workshop.parkinglots.repository.ParkingRepository
import eu.livesport.workshop.parkinglots.ui.common.ParkingItemsList
import eu.livesport.workshop.parkinglots.ui.components.ParkingTypeFilterBar
import kotlinx.coroutines.launch

@Composable
fun ParkingLotsListScreen(onItemClick: () -> Unit) {
    // FOR TESTING PURPOSES ONLY
    // TODO: Move to ViewModel
    var parkingLots: List<ParkingItemModel> by remember { mutableStateOf(emptyList()) }
    LaunchedEffect(Unit) {
        launch {
            parkingLots =
                ParkingRepository.INSTANCE.getParkingLots()
                    .map {
                        ParkingItemModel(
                            title = it.name,
                            capacity = it.capacity,
                            address = it.address,
                            covered = it.covered,
                            prohibitions = emptyList(),
                        )
                    }
        }
    }

    Column {
        FiltersMock()
        ParkingItemsList(
            items = parkingLots,
            onItemClick = { onItemClick() }
        )
    }
}

@Composable
private fun FiltersMock() {
    val filters = listOf("On Street", "Underground", "Multi Storey", "Field", "Garage")
    var selected by remember { mutableIntStateOf(0) }
    ParkingTypeFilterBar(
        filters = filters,
        selectedIndex = selected,
        onSelected = { selected = it }
    )
}

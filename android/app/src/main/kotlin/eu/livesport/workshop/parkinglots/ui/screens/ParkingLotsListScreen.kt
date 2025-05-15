package eu.livesport.workshop.parkinglots.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import eu.livesport.workshop.parkinglots.data.parkingItemsMock
import eu.livesport.workshop.parkinglots.ui.common.ParkingItemsList
import eu.livesport.workshop.parkinglots.ui.components.ParkingTypeFilterBar

@Composable
fun ParkingLotsListScreen(onItemClick: () -> Unit) {
    Column {
        FiltersMock()
        ParkingItemsList(
            items = parkingItemsMock,
            onItemClick = { onItemClick() }
        )    }

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
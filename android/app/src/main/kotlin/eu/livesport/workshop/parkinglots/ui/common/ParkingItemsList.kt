package eu.livesport.workshop.parkinglots.ui.common

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import eu.livesport.workshop.parkinglots.data.ParkingItemModel
import eu.livesport.workshop.parkinglots.ui.components.ParkingItem

@Composable
fun ParkingItemsList(
    items: List<ParkingItemModel>,
    onItemClick: (ParkingItemModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        items(items) { parkingLot ->
            ParkingItem(model = parkingLot, onItemClick = onItemClick)
        }
    }
}
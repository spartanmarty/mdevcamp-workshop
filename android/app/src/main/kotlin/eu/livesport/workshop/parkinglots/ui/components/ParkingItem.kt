package eu.livesport.workshop.parkinglots.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import eu.livesport.workshop.parkinglots.repository.model.ParkingLot
import eu.livesport.workshop.parkinglots.ui.theme.ParkingLotsTheme

@Composable
fun ParkingItem(
    model: ParkingLot,
    modifier: Modifier = Modifier,
    onItemClick: (ParkingLot) -> Unit = {},
) {
    Text("ParkingItem")
}

@Preview
@Composable
fun ParkingItemPreview() {
    ParkingLotsTheme {
        ParkingItem(
            ParkingLot(
                id = "park_123",
                name = "Parking 1",
                address = "123 Main St",
                capacity = 100,
                covered = true,
                prohibitions = emptyList(),
            )
        )
    }
}

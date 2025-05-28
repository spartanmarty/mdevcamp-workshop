package eu.livesport.workshop.parkinglots.ui.screens

import androidx.compose.runtime.Composable
import eu.livesport.workshop.parkinglots.repository.model.ParkingLot
import eu.livesport.workshop.parkinglots.repository.model.ParkingProhibitions

@Composable
fun ParkingLotDetailScreen(
    parkingLotId: String,
) {
    val dummyParkingLot = ParkingLot(
        id = "123",
        name = "Dummy Parking Lot",
        address = "123 Dummy Street, City, Country",
        capacity = 123,
        covered = false,
        isFavorite = false,
        prohibitions = listOf(ParkingProhibitions.LPG_CNG, ParkingProhibitions.TRUCK),
    )

    // TODO: Implement the UI for displaying parking lot details
    // 1. Parking lot name as a screen title
    // 2. Address
    // 3. Capacity
    // 4. Covered status
    // 5. List of prohibitions using icons
}

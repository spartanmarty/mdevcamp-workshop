package eu.livesport.workshop.parkinglots.internal.repository.datasource

import eu.livesport.workshop.parkinglots.internal.repository.model.ParkingLotApiModel
import kotlinx.coroutines.delay

internal class ParkingLotDataSource {

    suspend fun getParkingLots(): List<ParkingLotApiModel> {
        // Simulate network delay
        delay(1000L)

        return listOf(
            ParkingLotApiModel("Lot A", 100),
            ParkingLotApiModel("Lot B", 200),
            ParkingLotApiModel("Lot C", 150)
        )
    }
}

package eu.livesport.workshop.parkinglots.internal.repository.api

import eu.livesport.workshop.parkinglots.internal.network.NetworkExecutor
import eu.livesport.workshop.parkinglots.internal.network.get
import eu.livesport.workshop.parkinglots.internal.repository.model.ParkingApiModel
import eu.livesport.workshop.parkinglots.internal.repository.model.ParkingLotApiModel

internal class ParkingApi(
    private val networkExecutor: NetworkExecutor,
) {

    suspend fun getParkingLots(): List<ParkingLotApiModel> =
        networkExecutor.get<ParkingApiModel>(URL_PARKING_LOTS)
            .getOrNull()
            ?.parkingLots
            ?: emptyList()
}

private const val URL_PARKING_LOTS: String = "https://api.golemio.cz/v3/parking?limit=10"

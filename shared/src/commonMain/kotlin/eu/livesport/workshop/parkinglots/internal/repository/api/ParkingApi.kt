package eu.livesport.workshop.parkinglots.internal.repository.api

import eu.livesport.workshop.parkinglots.internal.network.NetworkExecutor
import eu.livesport.workshop.parkinglots.internal.network.get
import eu.livesport.workshop.parkinglots.internal.repository.model.ParkingApiModel
import eu.livesport.workshop.parkinglots.internal.repository.model.ParkingLotApiModel
import eu.livesport.workshop.parkinglots.repository.model.ParkingPolicyFilter

internal class ParkingApi(
    private val networkExecutor: NetworkExecutor,
) {

    suspend fun getParkingLots(filter: ParkingPolicyFilter): List<ParkingLotApiModel> =
        networkExecutor.get<ParkingApiModel>(URL_PARKING_LOTS + filter.toQueryString())
            .getOrNull()
            ?.parkingLots
            ?: emptyList()
}

private fun ParkingPolicyFilter.toQueryString(): String {
    if (this == ParkingPolicyFilter.NO_FILTER) return ""
    return "&parkingPolicy=${this.value}"
}

private const val URL_PARKING_LOTS: String = "https://api.golemio.cz/v3/parking?limit=10"

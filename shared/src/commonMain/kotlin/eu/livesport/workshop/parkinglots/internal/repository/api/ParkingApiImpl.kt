package eu.livesport.workshop.parkinglots.internal.repository.api

import eu.livesport.workshop.parkinglots.internal.network.NetworkExecutor
import eu.livesport.workshop.parkinglots.internal.network.get
import eu.livesport.workshop.parkinglots.internal.repository.model.ParkingApiModel
import eu.livesport.workshop.parkinglots.internal.repository.model.ParkingLotApiModel
import eu.livesport.workshop.parkinglots.repository.model.ParkingPolicyFilter

internal class ParkingApiImpl(
    private val networkExecutor: NetworkExecutor,
) : ParkingApi {

    override suspend fun getParkingLots(filter: ParkingPolicyFilter): List<ParkingLotApiModel> =
        networkExecutor.get<ParkingApiModel>(URL_PARKING_LOTS + filter.toQueryString())
            .getOrNull()
            ?.parkingLots
            ?: emptyList()

    override suspend fun getParkingLotDetail(id: String): ParkingLotApiModel? =
        networkExecutor.get<ParkingLotApiModel>("$URL_BASE/$id")
            .getOrNull()
}

private fun ParkingPolicyFilter.toQueryString(): String {
    if (this == ParkingPolicyFilter.NO_FILTER) return ""
    return "$URL_PARAM_POLICY=${this.value}"
}

private const val URL_BASE: String = "https://api.golemio.cz/v3/parking"
private const val URL_PARKING_LOTS: String = "$URL_BASE?limit=10"
private const val URL_PARAM_POLICY: String = "&parkingPolicy"

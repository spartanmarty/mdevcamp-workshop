package eu.livesport.workshop.parkinglots.internal.repository.api

import eu.livesport.workshop.parkinglots.internal.network.NetworkExecutor
import eu.livesport.workshop.parkinglots.internal.network.NetworkExecutorImpl
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

    companion object {
        // TODO: Use DI to inject the repository.
        val INSTANCE: ParkingApi by lazy {
            ParkingApi(networkExecutor = NetworkExecutorImpl.INSTANCE)
        }
    }
}

private const val URL_PARKING_LOTS: String = "https://api.golemio.cz/v3/parking?limit=10"

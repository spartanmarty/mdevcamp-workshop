package eu.livesport.workshop.parkinglots.internal.repository.datasource

import eu.livesport.workshop.parkinglots.internal.repository.api.ParkingApi
import eu.livesport.workshop.parkinglots.internal.repository.model.ParkingLotApiModel

internal class ParkingDataSource(
    private val api: ParkingApi,
) {

    suspend fun getParkingLots(): List<ParkingLotApiModel> = api.getParkingLots()

    companion object {
        // TODO: Use DI to inject the repository.
        val INSTANCE: ParkingDataSource by lazy {
            ParkingDataSource(api = ParkingApi.INSTANCE)
        }
    }
}

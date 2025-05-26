package eu.livesport.workshop.parkinglots.internal.repository.datasource

import eu.livesport.workshop.parkinglots.internal.repository.api.ParkingApi
import eu.livesport.workshop.parkinglots.internal.repository.model.ParkingLotApiModel
import eu.livesport.workshop.parkinglots.repository.model.ParkingPolicyFilter

internal class ParkingDataSource(
    private val api: ParkingApi,
) {

    suspend fun getParkingLots(filter: ParkingPolicyFilter): List<ParkingLotApiModel> = api.getParkingLots(filter)
}

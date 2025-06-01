package eu.livesport.workshop.parkinglots.internal.repository.api

import eu.livesport.workshop.parkinglots.internal.repository.model.ParkingLotApiModel
import eu.livesport.workshop.parkinglots.repository.model.ParkingPolicyFilter

internal class ParkingApiImpl : ParkingApi {

    override suspend fun getParkingLots(filter: ParkingPolicyFilter): List<ParkingLotApiModel> =
        TODO("Implement. Hint: Check the test for 'ParkingApiImpl'.")

    override suspend fun getParkingLotDetail(id: String): ParkingLotApiModel? =
        TODO("Implement.")
}

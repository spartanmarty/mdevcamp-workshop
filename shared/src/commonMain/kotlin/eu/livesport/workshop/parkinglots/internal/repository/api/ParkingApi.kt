package eu.livesport.workshop.parkinglots.internal.repository.api

import eu.livesport.workshop.parkinglots.internal.repository.model.ParkingLotApiModel
import eu.livesport.workshop.parkinglots.repository.model.ParkingPolicyFilter

internal interface ParkingApi {

    suspend fun getParkingLots(filter: ParkingPolicyFilter): List<ParkingLotApiModel>

    suspend fun getParkingLotDetail(id: String): ParkingLotApiModel?
}

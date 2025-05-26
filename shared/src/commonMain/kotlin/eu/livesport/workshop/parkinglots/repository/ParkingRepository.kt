package eu.livesport.workshop.parkinglots.repository

import eu.livesport.workshop.parkinglots.repository.model.ParkingLot
import eu.livesport.workshop.parkinglots.repository.model.ParkingPolicyFilter
import kotlinx.coroutines.flow.Flow

public interface ParkingRepository {

    public suspend fun getParkingLots(filter: ParkingPolicyFilter): Flow<List<ParkingLot>>
}

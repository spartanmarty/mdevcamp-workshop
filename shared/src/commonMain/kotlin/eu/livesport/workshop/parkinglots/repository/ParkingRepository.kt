package eu.livesport.workshop.parkinglots.repository

import eu.livesport.workshop.parkinglots.repository.model.ParkingLot
import kotlinx.coroutines.flow.Flow

public interface ParkingRepository {

    public suspend fun getParkingLots(): Flow<List<ParkingLot>>
}

package eu.livesport.workshop.parkinglots.repository

import eu.livesport.workshop.parkinglots.repository.model.ParkingLot
import kotlinx.coroutines.flow.Flow

public interface FavoriteParkingRepository {

    public fun getFavoriteParkingLots(): Flow<List<ParkingLot>>

    public suspend fun toggle(parkingLot: ParkingLot)
}
package eu.livesport.workshop.parkinglots.repository

import eu.livesport.workshop.parkinglots.repository.model.ParkingLot

public interface ParkingRepository {

    public suspend fun getParkingLots(): List<ParkingLot>
}

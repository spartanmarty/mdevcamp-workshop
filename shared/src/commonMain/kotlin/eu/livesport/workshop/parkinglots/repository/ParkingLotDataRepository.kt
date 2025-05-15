package eu.livesport.workshop.parkinglots.repository

import eu.livesport.workshop.parkinglots.repository.model.ParkingLot

interface ParkingLotDataRepository {

    suspend fun getParkingLots(): List<ParkingLot>
}

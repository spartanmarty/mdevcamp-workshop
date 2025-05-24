package eu.livesport.workshop.parkinglots.repository

import eu.livesport.workshop.parkinglots.repository.model.ParkingLot
import eu.livesport.workshop.parkinglots.repository.model.ParkingPolicyFilter

public interface ParkingRepository {

    public suspend fun getParkingLots(filter: ParkingPolicyFilter): List<ParkingLot>
}

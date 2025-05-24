package eu.livesport.workshop.parkinglots.internal.repository

import eu.livesport.workshop.parkinglots.internal.repository.datasource.ParkingDataSource
import eu.livesport.workshop.parkinglots.internal.repository.model.toParkingLot
import eu.livesport.workshop.parkinglots.repository.ParkingRepository
import eu.livesport.workshop.parkinglots.repository.model.ParkingLot
import eu.livesport.workshop.parkinglots.repository.model.ParkingPolicyFilter

internal class ParkingRepositoryImpl(
    private val dataSource: ParkingDataSource,
) : ParkingRepository {

    override suspend fun getParkingLots(filter: ParkingPolicyFilter): List<ParkingLot> =
        dataSource.getParkingLots(filter).map { it.toParkingLot() }
}

package eu.livesport.workshop.parkinglots.internal.repository

import eu.livesport.workshop.parkinglots.internal.repository.datasource.ParkingDataSource
import eu.livesport.workshop.parkinglots.internal.repository.model.toParkingLot
import eu.livesport.workshop.parkinglots.repository.ParkingRepository
import eu.livesport.workshop.parkinglots.repository.model.ParkingLot

internal class ParkingRepositoryImpl(
    private val dataSource: ParkingDataSource,
) : ParkingRepository {

    override suspend fun getParkingLots(): List<ParkingLot> =
        dataSource.getParkingLots().map { it.toParkingLot() }
}

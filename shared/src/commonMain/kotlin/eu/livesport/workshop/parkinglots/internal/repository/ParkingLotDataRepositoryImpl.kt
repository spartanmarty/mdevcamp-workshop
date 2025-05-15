package eu.livesport.workshop.parkinglots.internal.repository

import eu.livesport.workshop.parkinglots.internal.repository.datasource.ParkingLotDataSource
import eu.livesport.workshop.parkinglots.internal.repository.model.toParkingLot
import eu.livesport.workshop.parkinglots.repository.ParkingLotDataRepository
import eu.livesport.workshop.parkinglots.repository.model.ParkingLot

internal class ParkingLotDataRepositoryImpl(
    private val parkingLotDataSource: ParkingLotDataSource,
) : ParkingLotDataRepository {

    override suspend fun getParkingLots(): List<ParkingLot> =
        parkingLotDataSource.getParkingLots()
            .map { it.toParkingLot() }
}

package eu.livesport.workshop.parkinglots.repository

import eu.livesport.workshop.parkinglots.internal.repository.ParkingDataRepositoryImpl
import eu.livesport.workshop.parkinglots.internal.repository.datasource.ParkingDataSource
import eu.livesport.workshop.parkinglots.repository.model.ParkingLot

interface ParkingLotDataRepository {

    suspend fun getParkingLots(): List<ParkingLot>

    companion object {
        // TODO: Use DI to inject the repository.
        val INSTANCE: ParkingLotDataRepository by lazy {
            ParkingDataRepositoryImpl(dataSource = ParkingDataSource.INSTANCE)
        }
    }
}

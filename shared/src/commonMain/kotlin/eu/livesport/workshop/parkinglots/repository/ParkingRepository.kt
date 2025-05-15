package eu.livesport.workshop.parkinglots.repository

import eu.livesport.workshop.parkinglots.internal.repository.ParkingRepositoryImpl
import eu.livesport.workshop.parkinglots.internal.repository.datasource.ParkingDataSource
import eu.livesport.workshop.parkinglots.repository.model.ParkingLot

interface ParkingRepository {

    suspend fun getParkingLots(): List<ParkingLot>

    companion object {
        // TODO: Use DI to inject the repository.
        val INSTANCE: ParkingRepository by lazy {
            ParkingRepositoryImpl(dataSource = ParkingDataSource.INSTANCE)
        }
    }
}

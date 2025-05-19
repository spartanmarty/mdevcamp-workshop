package eu.livesport.workshop.parkinglots.repository

import eu.livesport.workshop.parkinglots.internal.repository.ParkingRepositoryImpl
import eu.livesport.workshop.parkinglots.internal.repository.datasource.ParkingDataSource
import eu.livesport.workshop.parkinglots.repository.model.ParkingLot

public interface ParkingRepository {

    public suspend fun getParkingLots(): List<ParkingLot>

    public companion object {
        // TODO: Use DI to inject the repository.
        public val INSTANCE: ParkingRepository by lazy {
            ParkingRepositoryImpl(dataSource = ParkingDataSource.INSTANCE)
        }
    }
}

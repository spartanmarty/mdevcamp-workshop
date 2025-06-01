package eu.livesport.workshop.parkinglots.repository

import eu.livesport.workshop.parkinglots.internal.repository.ParkingRepositoryImpl
import eu.livesport.workshop.parkinglots.internal.repository.datasource.ParkingDataSource
import eu.livesport.workshop.parkinglots.repository.model.ParkingLot
import eu.livesport.workshop.parkinglots.repository.model.ParkingPolicyFilter
import kotlinx.coroutines.flow.Flow

public interface ParkingRepository {

    public suspend fun getParkingLots(filter: ParkingPolicyFilter): Flow<List<ParkingLot>>

    public suspend fun getParkingLotDetail(id: String): ParkingLot?

    public companion object {
        public val INSTANCE: ParkingRepository by lazy { ParkingRepositoryImpl(ParkingDataSource.INSTANCE) }
    }
}

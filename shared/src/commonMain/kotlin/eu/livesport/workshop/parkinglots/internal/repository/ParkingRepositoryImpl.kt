package eu.livesport.workshop.parkinglots.internal.repository

import eu.livesport.workshop.parkinglots.internal.database.FavoriteParkingLotsDao
import eu.livesport.workshop.parkinglots.internal.repository.datasource.ParkingDataSource
import eu.livesport.workshop.parkinglots.internal.repository.model.toParkingLot
import eu.livesport.workshop.parkinglots.repository.ParkingRepository
import eu.livesport.workshop.parkinglots.repository.model.ParkingLot
import eu.livesport.workshop.parkinglots.repository.model.ParkingPolicyFilter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

internal class ParkingRepositoryImpl(
    private val dataSource: ParkingDataSource,
    private val favoriteParkingLotsDao: FavoriteParkingLotsDao,
) : ParkingRepository {

    override suspend fun getParkingLots(filter: ParkingPolicyFilter): Flow<List<ParkingLot>> {
        dataSource.getParkingLots(filter).map { it.toParkingLot() }

        val parkingLotsFlow = flow {
            emit(dataSource.getParkingLots(filter).map { it.toParkingLot() })
        }

        val favoritesFlow = favoriteParkingLotsDao.getAll()
            .map { favoriteEntities -> favoriteEntities.map { it.id }.toSet() }

        return parkingLotsFlow.combine(favoritesFlow) { parkingLots, favoriteIds ->
            parkingLots.map { lot ->
                if (lot.id in favoriteIds) {
                    lot.copy(isFavorite = true)
                } else {
                    lot
                }
            }
        }
    }
}

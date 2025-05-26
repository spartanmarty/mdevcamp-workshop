package eu.livesport.workshop.parkinglots.internal.repository

import eu.livesport.workshop.parkinglots.internal.database.FavoriteParkingLotsDao
import eu.livesport.workshop.parkinglots.internal.database.toFavoriteParkingLotEntity
import eu.livesport.workshop.parkinglots.internal.database.toParkingLot
import eu.livesport.workshop.parkinglots.repository.FavoriteParkingRepository
import eu.livesport.workshop.parkinglots.repository.model.ParkingLot
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class FavoriteParkingRepositoryImpl(
    private val favoriteParkingLotsDao: FavoriteParkingLotsDao,
) : FavoriteParkingRepository {

    override fun getFavoriteParkingLots(): Flow<List<ParkingLot>> {
        return favoriteParkingLotsDao.getAll().map { it.map { entity -> entity.toParkingLot() } }
    }

    override suspend fun toggle(parkingLot: ParkingLot) {
        with(parkingLot.toFavoriteParkingLotEntity()) {
            if (parkingLot.isFavorite) {
                favoriteParkingLotsDao.delete(this)
            } else {
                favoriteParkingLotsDao.insert(this)
            }
        }
    }


}
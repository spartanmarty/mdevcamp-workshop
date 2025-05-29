package eu.livesport.workshop.parkinglots.internal.repository

import eu.livesport.workshop.parkinglots.internal.database.FavoriteParkingLotsDao
import eu.livesport.workshop.parkinglots.internal.repository.datasource.ParkingDataSource
import eu.livesport.workshop.parkinglots.internal.repository.model.ParkingLotApiModel
import eu.livesport.workshop.parkinglots.internal.repository.model.toParkingLot
import eu.livesport.workshop.parkinglots.repository.ParkingRepository
import eu.livesport.workshop.parkinglots.repository.model.ParkingLot
import eu.livesport.workshop.parkinglots.repository.model.ParkingPolicyFilter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

internal class ParkingRepositoryImpl(
    private val dataSource: ParkingDataSource,
    private val favoriteParkingLotsDao: FavoriteParkingLotsDao,
) : ParkingRepository {

    private val detailCache: MutableMap<String, ParkingLotApiModel> = mutableMapOf()
    private val detailCacheMutex: Mutex = Mutex()

    override suspend fun getParkingLots(filter: ParkingPolicyFilter): Flow<List<ParkingLot>> =
        flowOf(dataSource.getParkingLots(filter).map { it.toParkingLot() })

    override suspend fun getParkingLotDetail(id: String): ParkingLot? {
        suspend fun result(): ParkingLot {
            val cachedItem = detailCacheMutex.withLock { detailCache[id] }

            return checkNotNull(cachedItem)
                .toParkingLot(isFavorite = favoriteParkingLotsDao.findById(id) != null)
        }

        if (detailCache.contains(id)) {
            return result()
        }

        val apiResult: ParkingLotApiModel =
            dataSource.getParkingLotDetail(id)
                ?: return null

        detailCacheMutex.withLock { detailCache.put(id, apiResult) }

        return result()
    }
}

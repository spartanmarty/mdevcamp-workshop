package eu.livesport.workshop.parkinglots.internal.repository

import eu.livesport.workshop.parkinglots.repository.FavoriteParkingRepository
import eu.livesport.workshop.parkinglots.repository.model.ParkingLot
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal class FavoriteParkingRepositoryImpl : FavoriteParkingRepository {

    override fun getFavoriteParkingLots(): Flow<List<ParkingLot>> = flowOf(emptyList())

    override suspend fun toggle(parkingLot: ParkingLot): Unit = Unit
}

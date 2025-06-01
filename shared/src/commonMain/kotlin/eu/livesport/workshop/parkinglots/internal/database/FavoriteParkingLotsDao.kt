package eu.livesport.workshop.parkinglots.internal.database

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
internal interface FavoriteParkingLotsDao {

    @Query("SELECT * FROM favorite_parking_lots")
    fun getAll(): Flow<List<FavoriteParkingLotEntity>>
}

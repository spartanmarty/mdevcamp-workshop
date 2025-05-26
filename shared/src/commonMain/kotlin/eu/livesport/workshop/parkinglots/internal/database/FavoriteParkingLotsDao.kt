package eu.livesport.workshop.parkinglots.internal.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
internal interface FavoriteParkingLotsDao {

    @Query("SELECT * FROM favorite_parking_lots")
    fun getAll(): Flow<List<FavoriteParkingLotEntity>>

    @Insert(entity = FavoriteParkingLotEntity::class)
    suspend fun insert(parkingLot: FavoriteParkingLotEntity)

    @Delete
    suspend fun delete(parkingLot: FavoriteParkingLotEntity)
}
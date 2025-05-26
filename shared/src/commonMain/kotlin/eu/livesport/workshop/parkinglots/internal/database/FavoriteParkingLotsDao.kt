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

    @Query("SELECT * FROM favorite_parking_lots WHERE id = :id")
    suspend fun findById(id: String): FavoriteParkingLotEntity?

    @Insert(entity = FavoriteParkingLotEntity::class)
    suspend fun insert(parkingLot: FavoriteParkingLotEntity)

    @Delete
    suspend fun delete(parkingLot: FavoriteParkingLotEntity)
}
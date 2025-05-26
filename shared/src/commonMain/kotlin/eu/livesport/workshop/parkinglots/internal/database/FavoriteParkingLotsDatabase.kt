package eu.livesport.workshop.parkinglots.internal.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FavoriteParkingLotEntity::class],
    version = 1,
)
internal abstract class FavoriteParkingLotsDatabase : RoomDatabase() {
    abstract fun favoriteParkingLotsDao(): FavoriteParkingLotsDao
}
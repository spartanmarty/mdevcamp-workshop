package eu.livesport.workshop.parkinglots.internal.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor

@Database(
    entities = [FavoriteParkingLotEntity::class],
    version = 1,
)
@ConstructedBy(FavoriteParkingLotsDatabaseConstructor::class)
internal abstract class FavoriteParkingLotsDatabase : RoomDatabase() {
    internal  abstract fun favoriteParkingLotsDao(): FavoriteParkingLotsDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT", "EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
internal expect object FavoriteParkingLotsDatabaseConstructor : RoomDatabaseConstructor<FavoriteParkingLotsDatabase> {
    override fun initialize(): FavoriteParkingLotsDatabase
}
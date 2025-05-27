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
public abstract class FavoriteParkingLotsDatabase : RoomDatabase() {
    public abstract fun favoriteParkingLotsDao(): FavoriteParkingLotsDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT", "EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
public expect object FavoriteParkingLotsDatabaseConstructor : RoomDatabaseConstructor<FavoriteParkingLotsDatabase> {
    public override fun initialize(): FavoriteParkingLotsDatabase
}
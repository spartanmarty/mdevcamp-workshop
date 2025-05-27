package eu.livesport.workshop.parkinglots.internal.database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import platform.Foundation.NSHomeDirectory

internal fun getFavoriteParkingLotsDatabase(): FavoriteParkingLotsDatabase {
    val dbFile = NSHomeDirectory() + "/favorite_parking_lots.db"
    return Room.databaseBuilder<FavoriteParkingLotsDatabase>(name = dbFile)
        .setDriver(BundledSQLiteDriver())
        .build()
}
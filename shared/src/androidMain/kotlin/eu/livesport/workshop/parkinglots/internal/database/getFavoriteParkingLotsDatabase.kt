package eu.livesport.workshop.parkinglots.internal.database

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver

// TODO visibility

internal fun getFavoriteParkingLotsDatabase(context: Context): FavoriteParkingLotsDatabase {
    val dbFile = context.getDatabasePath("favorite_parking_lots.db")
    return Room.databaseBuilder<FavoriteParkingLotsDatabase>(
        context = context.applicationContext,
        name = dbFile.absolutePath,
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}
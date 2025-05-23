package eu.livesport.workshop.parkinglots.internal.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_parking_lots")
internal data class FavoriteParkingLotEntity(
    @PrimaryKey val id: String,
    val title: String,
    val capacity: Int,
    val address: String,
    val covered: Boolean,
    // val prohibitions: List<String>,
    val description: String,
    val type: String,
    val parkingPolicy: String,
)

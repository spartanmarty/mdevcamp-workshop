package eu.livesport.workshop.parkinglots.internal.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import eu.livesport.workshop.parkinglots.repository.model.ParkingLot

@Entity(tableName = "favorite_parking_lots")
public data class FavoriteParkingLotEntity(
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

public fun FavoriteParkingLotEntity.toParkingLot(): ParkingLot =
    ParkingLot(
        id = id,
        name = title,
        address = address,
        capacity = capacity,
        covered = covered,
        isFavorite = true,
    )

public fun ParkingLot.toFavoriteParkingLotEntity(): FavoriteParkingLotEntity =
    FavoriteParkingLotEntity(
        id = id,
        title = name,
        capacity = capacity,
        address = address ?: "",
        covered = covered ?: false,
        description = "",
        type = "",
        parkingPolicy = "",
    )

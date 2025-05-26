package eu.livesport.workshop.parkinglots.internal.repository.model

import eu.livesport.workshop.parkinglots.repository.model.ParkingLot
import kotlinx.serialization.Serializable

@Serializable
internal data class ParkingLotApiModel(
    val properties: ParkingLotPropertiesApiModel,
)

internal fun ParkingLotApiModel.toParkingLot(isFavorite: Boolean = false): ParkingLot =
    ParkingLot(
        id = properties.id,
        name = properties.name,
        address = properties.address?.formatted,
        capacity = properties.capacity,
        covered = properties.covered,
        isFavorite = isFavorite,
    )

package eu.livesport.workshop.parkinglots.internal.repository.model

import eu.livesport.workshop.parkinglots.repository.model.ParkingLot
import kotlinx.serialization.Serializable

@Serializable
internal data class ParkingLotApiModel(
    val properties: ParkingLotPropertiesApiModel,
)

internal fun ParkingLotApiModel.toParkingLot(): ParkingLot =
    ParkingLot(
        id = properties.id,
        name = properties.name,
        capacity = properties.capacity,
    )

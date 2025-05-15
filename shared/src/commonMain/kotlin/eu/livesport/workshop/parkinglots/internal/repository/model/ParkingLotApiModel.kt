package eu.livesport.workshop.parkinglots.internal.repository.model

import eu.livesport.workshop.parkinglots.repository.model.ParkingLot
import kotlinx.serialization.Serializable

@Serializable
internal data class ParkingLotApiModel(
    val name: String,
    val capacity: Int,
)

internal fun ParkingLotApiModel.toParkingLot(): ParkingLot =
    ParkingLot(
        name = name,
        capacity = capacity,
    )

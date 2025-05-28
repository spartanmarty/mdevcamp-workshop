package eu.livesport.workshop.parkinglots.internal.repository.model

import eu.livesport.workshop.parkinglots.repository.model.ParkingLot
import eu.livesport.workshop.parkinglots.repository.model.ParkingProhibitions
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
        prohibitions = properties.parkingProhibitions?.let {
            buildList {
                if (it.lpgCng == true) add(ParkingProhibitions.LPG_CNG)
                if (it.bus == true) add(ParkingProhibitions.BUS)
                if (it.truck == true) add(ParkingProhibitions.TRUCK)
                if (it.motorcycle == true) add(ParkingProhibitions.MOTORCYCLE)
                if (it.bicycle == true) add(ParkingProhibitions.BICYCLE)
                if (it.trailer == true) add(ParkingProhibitions.TRAILER)
            }
        } ?: emptyList(),
    )

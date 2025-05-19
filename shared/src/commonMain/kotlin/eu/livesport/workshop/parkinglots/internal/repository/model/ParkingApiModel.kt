package eu.livesport.workshop.parkinglots.internal.repository.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ParkingApiModel(
    @SerialName("features")
    val parkingLots: List<ParkingLotApiModel>,
)

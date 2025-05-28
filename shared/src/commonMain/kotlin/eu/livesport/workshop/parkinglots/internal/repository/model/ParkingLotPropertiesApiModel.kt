package eu.livesport.workshop.parkinglots.internal.repository.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ParkingLotPropertiesApiModel(
    val id: String,
    val name: String,
    val address: ParkingLotAddressApiModel?,
    val capacity: Int,
    val covered: Boolean?,
    @SerialName("parking_prohibitions")
    val parkingProhibitions: ParkingProhibitions?,
)
